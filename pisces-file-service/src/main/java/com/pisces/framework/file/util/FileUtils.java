/**
 * Copyright (c) 2018 Pisces Technology Co., Ltd.
 */
package com.pisces.framework.file.util;

import com.pisces.framework.fastdfs.core.FastDFSFile;
import com.pisces.framework.fastdfs.core.FastDFSTemplate;
import com.pisces.framework.file.pojo.FileVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.ZipOutputStream;

@Component
public class FileUtils {
	public static final String charset_utf8 = "UTF-8";
	public static final String charset_iso_8859_1 = "ISO-8859-1";
	public static final String charset_gb_2312 = "GB2312";
	
	private static FileUtils instance;
	
	@Autowired
	FastDFSTemplate fastDFSTemplate;
	
    @PostConstruct
    public void init() {
    	instance = this;
    }
    
	/**
	 * 对传入的名称，进行指定方式(charset)解码，再返回iso_8859_1编码的字符串
	 * 用途：主要用于浏览器相关的文件下载中文乱码
	 * 如果一定要进行字节编码，则用户要确保encoding的方法就是当初字符串输入时的encoding。
	 * */
	public static String encodeFileName(String name, String charset, HttpServletRequest request) {
		if (StringUtils.isNotBlank(name)) {
			try {
				return new String(name.getBytes(charset!=null ? charset:charset_gb_2312),charset_iso_8859_1);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return name;
			}
		} else {
			return name;
		}
	}
	
	public static void downZipFile(String name, List<FileVO> list, HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		instance.setResponseHeader(name, response, request);
	    instance.downZipFile(list, response.getOutputStream());
	}

	private void setResponseHeader(String name, HttpServletResponse response, HttpServletRequest request)
			throws UnsupportedEncodingException {
		String fileName = (StringUtils.isBlank(name) ? "文档" : name) + ".zip";
		String userAgent = request.getHeader("user-agent").toLowerCase();
		if (userAgent.contains("msie") || userAgent.contains("like gecko")) {
			fileName = URLEncoder.encode(fileName, "UTF-8");
		} else {
			fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
		}

		response.setContentType("APPLICATION/OCTET-STREAM");  
	    response.setHeader("Content-Disposition","attachment; filename=" + fileName);
	    response.setHeader("Content-type", "text/html;charset=UTF-8");
	}
	
	private void downZipFile(List<FileVO> list, OutputStream os) throws Exception {
		if (list != null && list.size() > 0) {  
			try (ZipOutputStream out = new ZipOutputStream(os)) {
				Map<String, FastDFSFile> map = convertIntoFastDFSFileMap(list);
				ZipUtils.doFastDFSCompress(map, out, fastDFSTemplate);
			}
		}
	}
	
	private Map<String, File> convertIntoFileMap(List<FileVO> list, String docLocation) {
		Map<String, File> map = new ConcurrentHashMap<String, File>();
		Map<String, Integer> numMap = new ConcurrentHashMap<String, Integer>();
		String location = docLocation + File.separator;
		
		list.forEach(fv->{
			if (StringUtils.isNotBlank(fv.getUrl())) {
				File file = new File(location + fv.getUrl());
				if (map.containsKey(fv.getFileName())) {
					String duFileName = duplicateFileName(numMap, fv);
					map.put(duFileName, file);
				} else {
					map.put(fv.getFileName(), file);
				}
			}
		});
		
		return map;
	} 
	
	private Map<String, FastDFSFile> convertIntoFastDFSFileMap(List<FileVO> list) {
		Map<String, FastDFSFile> map = new ConcurrentHashMap<String, FastDFSFile>();
		Map<String, Integer> numMap = new ConcurrentHashMap<String, Integer>();
		
		list.forEach(fv->{
			if (StringUtils.isNotBlank(fv.getUrl())) {
				FastDFSFile file = new FastDFSFile(fv.getUrl());
				if (map.containsKey(fv.getFileName())) {
					String duFileName = duplicateFileName(numMap, fv);
					map.put(duFileName, file);
				} else {
					map.put(fv.getFileName(), file);
				}
			}
		});
		
		return map;
	}

	private String duplicateFileName(Map<String, Integer> numMap, FileVO fv) {
		StringBuilder sb = new StringBuilder(fv.getFileName());
		int ext = sb.lastIndexOf(".");
		int num = numMap.containsKey(fv.getFileName()) ? (numMap.get(fv.getFileName()) + 1) : 1;
		if (ext > 0) {
			sb.insert(ext, "(" + num + ")");
		} else {
			sb.append("(" + num + ")");
		}
		numMap.put(fv.getFileName(), num);
		return sb.toString();
	} 
	
	public static void _main(String[] args) {
		List<FileVO> list = new java.util.ArrayList<FileVO>();
		FileVO f1 = new FileVO(); f1.setFileName("abcde0000"); f1.setUrl("f1.jpg"); list.add(f1);
		FileVO f2 = new FileVO(); f2.setFileName("abcde0000"); f2.setUrl("f2.jpg"); list.add(f2);
		FileVO f3 = new FileVO(); f3.setFileName("abcde.jpg"); f3.setUrl("f3.jpg"); list.add(f3);
		FileVO f4 = new FileVO(); f4.setFileName("abcde.jpg"); f4.setUrl("f4.jpg"); list.add(f4);
		FileVO f5 = new FileVO(); f5.setFileName("abcde.jpg"); f5.setUrl("f5.jpg"); list.add(f5);
		FileVO f6 = new FileVO(); f6.setFileName("ddddd.jpg"); f6.setUrl("f6.jpg"); list.add(f6);
		FileVO f7 = new FileVO(); f7.setFileName("zzz.x.jpg"); f7.setUrl("f7.jpg"); list.add(f7);
		FileVO f8 = new FileVO(); f8.setFileName("zzz.x.jpg"); f8.setUrl("f8.jpg"); list.add(f8);
		
		Map<String, File> m = instance.convertIntoFileMap(list, "d:\\temp");
		list.forEach(fl->{
			m.entrySet().forEach(fm->{
				if (fm.getValue().getName().contains(fl.getUrl())) {
					System.err.println(fm.getKey() + ":\t" + fm.getValue().getAbsolutePath());
				}
			});
		});
	}
	
}
