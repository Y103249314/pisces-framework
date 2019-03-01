/**
 * Copyright (c) 2018 Pisces Technology Co., Ltd.
 */
package com.pisces.framework.fastdfs.core;

/**
 * FastDFS 文件描述
 */
public class FastDFSFile implements java.io.Serializable {

	public static final String DEFAULT_GROUP = "group1";
	private static final long serialVersionUID = 5356749849453964923L;
	
	private String group;
    private String path;
    private String absolutePath;

    /**
     * "group|path"形式参数的构造函数
     * @param groupPath 若包含'|'，则拆分成group和path两部分，否则作为path且group取缺省值
     */
    public FastDFSFile(String groupPath) {
    	String[] p = groupPath.split("\\|");
    	if (p.length >= 2) {
    		this.group = p[0];
    		this.path = groupPath.substring(p[0].length() + 1);
    	} else {
    		this.group = DEFAULT_GROUP;
    		this.path = groupPath;
    	}
    }
    
    public FastDFSFile(String group, String path) {
        this.group = group;
        this.path = path;
    }
    
    /**
     * 获取"group|path"形式的路径
     * @return
     */
    public String getGroupPath() {
    	return new String(group + "|" + path);
    }
    
    /**
     * 获取文件名
     * @return
     */
    public String getName() {
    	return path.substring(path.lastIndexOf("/") + 1);
    }

    @Override
    public String toString() {
        return "FastDFSFile{" + "group='" + group + '\'' +
               ", path='" + path + '\'' +
               '}';
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

}
