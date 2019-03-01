/**
 * Copyright (c) 2018 Pisces Technology Co., Ltd.
 */
package com.pisces.framework.fastdfs.deploy.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pisces.framework.global.keys.SysGlobalKeys;
import com.pisces.framework.global.pojo.SysGlobalDO;
import com.pisces.framework.global.service.SysGlobalServiceImpl;
import com.pisces.framework.fastdfs.deploy.pojo.TenantDO;

@Service
public class TenantStorageDeployServiceImpl implements TenantStorageDeployService {

	private static final Logger log = LoggerFactory.getLogger(TenantStorageDeployServiceImpl.class);
	
	@Value("${deploy.base_path:/data/fastdfs/pisces/storage}")
	private String basePath;
	
	@Value("${deploy.ssh_passwd:123456}")
	private String sshPasswd;
	
	@Value("${deploy.tracker_server1:192.168.1.5:22122}")
	private String trackerServer1;
	
	@Value("${deploy.tracker_server2:192.168.1.6:22122}")
	private String trackerServer2;
	
	@Value("${deploy.storage_host1:192.168.1.7}")
	private String storageHost1;
	
	@Value("${deploy.storage_host2:192.168.1.8}")
	private String storageHost2;
	
	private String localWorkingDirectory = "/tmp/fdfs/";
	
	@Autowired
	SysGlobalServiceImpl sysGlobalServiceImpl;
	
	@Override
	public synchronized void deployStorage(TenantDO tenantDO) {
		if (isLinuxOS()) {
			String domain = tenantDO.getDomain().split("\\.")[0];
			if (StringUtils.isBlank(domain)) {
				log.error("DeployStorage: Invalid Domain");
				return;
			}
			
			Integer port = 0;
			SysGlobalDO gdo = sysGlobalServiceImpl.findByKey(SysGlobalKeys.FDFS_STORAGE_NEXT_PORT);
			if (gdo != null && StringUtils.isNotBlank(gdo.getValue())) {
				port = sysGlobalServiceImpl.getValue(gdo);
				gdo.setValue(String.valueOf(port+1));
				sysGlobalServiceImpl.update(gdo);
			}
			
			new File(localWorkingDirectory).mkdirs();
			extractFile("fdfs-storage-deploy.tar.gz");

			deploy(storageHost1, domain, port);
			deploy(storageHost2, domain, port);
		}
	}
	
	private boolean isLinuxOS() {
		String osName = System.getProperty("os.name");
		return osName != null && osName.toLowerCase().indexOf("linux") >= 0;
	}
	
	private void deploy(String storageHost, String domain, Integer port) {
		Thread th = new Thread(new Runnable() {
			@Override
			public void run() {
				doDeploy(storageHost, domain, port);
			}
		});
		th.start();
	}
	
	private void doDeploy(String storageHost, String domain, Integer port) {
		//1，准备工作: 将部署包upload到目标机器上
		String prepareSh = extractFile("prepare.sh");
		if (prepareSh != null) {
			String prepare_cmd = new StringBuilder(128)
					.append(prepareSh)
					.append(" ").append(storageHost)
					.append(" ").append(sshPasswd)
					.toString();
			runBashShell("chmod +x " + prepareSh);
			runBashShell(prepare_cmd);
		}
		
		//2，执行部署: 执行目标机器上的deploy脚本
		String remote_cmd = new StringBuilder(128)
				.append("'")
				.append("cd fdfs-storage-deploy && /bin/bash ./deploy.single.sh")
				.append(" --basepath=").append(basePath)
				.append(" --domain=").append(domain)
				.append(" --port=").append(port)
				.append(" --tracker1=").append(trackerServer1)
				.append(" --tracker2=").append(trackerServer2)
				.append("'")
				.toString();
		String deploy_cmd = new StringBuilder(256)
				.append("sshpass -p").append(sshPasswd)
				.append(" ").append("ssh -o StrictHostKeyChecking=no -o ConnectTimeout=5 root@").append(storageHost)
				.append(" ").append(remote_cmd)
				.toString();
		runBashShell(deploy_cmd);
	}
	
	private String extractFile(String file) {
		File destFile = new File(localWorkingDirectory + file);
		if (!destFile.isFile()) {
			try(FileOutputStream fos = new FileOutputStream(destFile)) {
		        InputStream is = this.getClass().getResourceAsStream("/deploy/fastdfs/" + file);
		        byte[] buf = new byte[1024];
		        int len = 0;
				while ((len = is.read(buf)) > 0) {
					fos.write(buf, 0, len);
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} 
		}
		return destFile.getAbsolutePath();
	}
	
	private boolean runBashShell(String cmd) {
        try {
        	ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", cmd);
        	pb.directory(new File(localWorkingDirectory));
        	pb.redirectErrorStream(true);
        	Process ps = pb.start();
			//int exitCode = ps.waitFor();
			//logStdOutErr(ps);
			//return exitCode == 0;
			return ps.waitFor(1, TimeUnit.MINUTES);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}  
	}
	
	private void logStdOutErr(Process ps) {
		try {
			String line;
			BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));  
			while ((line = br.readLine()) != null) {  
				log.info(line);
			}  
		} catch (Exception e) {
		}
	}
}
