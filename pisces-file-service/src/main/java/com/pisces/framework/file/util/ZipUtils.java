/**
 * Copyright (c) 2018 Pisces Technology Co., Ltd.
 */
package com.pisces.framework.file.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang3.StringUtils;

import com.pisces.framework.fastdfs.core.FastDFSFile;
import com.pisces.framework.fastdfs.core.FastDFSTemplate;
import com.pisces.framework.fastdfs.core.FastDFSException;

/**
 * 文件压缩工具类
 */
public class ZipUtils {

	private ZipUtils() {
	}

	public static void doCompress(String srcFile, String zipFile)
			throws Exception {
		doCompress(new File(srcFile), new File(zipFile));
	}

	public static void doCompress(File srcFile, File zipFile) throws Exception {
		try (ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile))) {
			doCompress(srcFile, out);
		}
	}

	public static void doCompress(String filelName, ZipOutputStream out)
			throws IOException {
		doCompress(new File(filelName), out);
	}

	public static void doCompress(File file, ZipOutputStream out)
			throws IOException {
		doCompress(file, out, null);
	}

	public static void doCompress(File inFile, ZipOutputStream out, String dir)
			throws IOException {
		if (inFile.isDirectory()) {
			File[] files = inFile.listFiles();
			if (files != null && files.length > 0) {
				for (File file : files) {
					ZipUtils.doCompress(file, out, dir); // recursive
				}
			}
		} else {
			ZipUtils.doZip(inFile, out, dir);
		}
	}

	public static void doCompress(List<File> files, ZipOutputStream out) throws IOException {
		if (files != null && files.size() > 0) {
			for (File file : files) {
				try {
					ZipUtils.doCompress(file, out, null);
				} catch (Exception e) { // try other files
					e.printStackTrace();
				}
			}
		}
	}

	public static void doCompress(Map<String, File> map, ZipOutputStream out) throws IOException {
		for (Entry<String, File> f : map.entrySet()) {
			if (StringUtils.isNotBlank(f.getKey())) {
				try {
					ZipUtils.doZip(f.getKey(), f.getValue(), out);
				} catch (Exception e) { // try other files
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void doZip(File inFile, ZipOutputStream out, String dir)
			throws IOException {
		String entryName = StringUtils.isNotBlank(dir) ? (dir + "/" + inFile.getName()) : null;
		doZip(entryName, inFile, out);
	}

	public static void doZip(String newName, File inFile, ZipOutputStream out)
			throws IOException {
		String entryName = StringUtils.isNotBlank(newName) ? newName : inFile.getName();
		ZipEntry entry = new ZipEntry(entryName);
		out.putNextEntry(entry);

		int len = 0;
		byte[] buffer = new byte[1024];
		try (FileInputStream fis = new FileInputStream(inFile)) {
			while ((len = fis.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
		} finally {
			out.closeEntry();
		}
	}
	
	public static void doFastDFSCompress(Map<String, FastDFSFile> map, ZipOutputStream out, FastDFSTemplate fastDFSTemplate) 
			throws IOException, FastDFSException {
		for (Entry<String, FastDFSFile> f : map.entrySet()) {
			if (StringUtils.isNotBlank(f.getKey())) {
				try {
					ZipUtils.doFastDFSZip(f.getKey(), f.getValue(), out, fastDFSTemplate);
				} catch (Exception e) { // try other files
					e.printStackTrace(); 
				}
			}
		}
	}
	
	public static void doFastDFSZip(String newName, FastDFSFile inFile, ZipOutputStream out, FastDFSTemplate fastDFSTemplate) 
			throws IOException, FastDFSException {
		String entryName = StringUtils.isNotBlank(newName) ? newName : inFile.getName();
		ZipEntry entry = new ZipEntry(entryName);
		out.putNextEntry(entry);

		try {
			byte[] buffer = fastDFSTemplate.download(inFile);
			out.write(buffer, 0, buffer.length);
		} finally {
			out.closeEntry();
		}
	}
}