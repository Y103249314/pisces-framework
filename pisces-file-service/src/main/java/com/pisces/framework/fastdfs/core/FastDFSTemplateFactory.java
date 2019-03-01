/**
 * Copyright (c) 2018 Pisces Technology Co., Ltd.
 */
package com.pisces.framework.fastdfs.core;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.TrackerGroup;

import com.pisces.framework.fastdfs.pool.PoolConfig;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.List;

/**
 * FastDFS 初始化
 */
public class FastDFSTemplateFactory {

    //connect_timeout
    private int connect_timeout;
    //network_timeout
    private int network_timeout;
    //charset
    private String charset;
    
    //http.tracker_http_port
    private int http_tracker_http_port;
    //http.anti_steal_token
    private boolean http_anti_steal_token;
    //http.secret_key
    private String http_secret_key;

    private List<String> tracker_servers;
    private List<String> nginx_address;

    private TrackerGroup tracker_group;

    private PoolConfig poolConfig = new PoolConfig();

    private String protocol = "http://";
    private String sepapator = "/";

    public void init() throws Exception {

        if (connect_timeout <= 0) {
            connect_timeout = ClientGlobal.DEFAULT_CONNECT_TIMEOUT;
        }

        if (network_timeout <= 0) {
            network_timeout = ClientGlobal.DEFAULT_NETWORK_TIMEOUT;
        }
        connect_timeout *= 1000; //millisecond
        network_timeout *= 1000; //millisecond

        if (charset == null || charset.length() == 0) {
            charset = "UTF-8";
        }

        if (http_tracker_http_port <= 0) {
            http_tracker_http_port = 80;
        }

        if (tracker_servers == null || tracker_servers.isEmpty()) {
            throw new FastDFSException("item \"tracker_server\"  not found", -1);
        }

        InetSocketAddress[] tracker_servers_socket = new InetSocketAddress[tracker_servers.size()];
        for (int i = 0; i < tracker_servers.size(); i++) {
            String str = tracker_servers.get(i);
            String[] parts = str.split("\\:", 2);
            if (parts.length != 2) {
                throw new FastDFSException(
                        "the value of item \"tracker_server\" is invalid, the correct format is host:port", -2);
            }

            tracker_servers_socket[i] = new InetSocketAddress(parts[0].trim(), Integer.parseInt(parts[1].trim()));
        }
        tracker_group = new TrackerGroup(tracker_servers_socket);

        if (http_anti_steal_token) {
            if (http_secret_key == null || "".equals(http_secret_key)) {
                throw new FastDFSException("item \"secret_key\"  not found", -2);
            }
        }
        setToGlobal();
    }

    private void setToGlobal() {
        ClientGlobal.setG_connect_timeout(this.connect_timeout);
        ClientGlobal.setG_network_timeout(this.network_timeout);
        ClientGlobal.setG_charset(this.charset);
        ClientGlobal.setG_tracker_http_port(this.http_tracker_http_port);
        ClientGlobal.setG_anti_steal_token(this.http_anti_steal_token);
        ClientGlobal.setG_secret_key(this.http_secret_key);
        ClientGlobal.setG_tracker_group(this.tracker_group);
    }

    public PoolConfig getPoolConfig() {
        if (poolConfig == null) {
            return new PoolConfig();
        }
        return poolConfig;
    }

    public void setPoolConfig(PoolConfig poolConfig) {
        this.poolConfig = poolConfig;
    }


    public List<String> getTracker_servers() {
        return tracker_servers;
    }

    public void setTracker_servers(String tracker_servers) {
        this.tracker_servers = Arrays.asList(tracker_servers.split(","));
    }

    public List<String> getNginx_address() {
        return nginx_address;
    }

    public void setNginx_address(String nginx_address) {
        this.nginx_address = Arrays.asList(nginx_address.split(","));
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getSepapator() {
        return sepapator;
    }

    public void setSepapator(String sepapator) {
        this.sepapator = sepapator;
    }

	public int getConnect_timeout() {
		return connect_timeout;
	}

	public void setConnect_timeout(int connect_timeout) {
		this.connect_timeout = connect_timeout;
	}

	public int getNetwork_timeout() {
		return network_timeout;
	}

	public void setNetwork_timeout(int network_timeout) {
		this.network_timeout = network_timeout;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public int getHttp_tracker_http_port() {
		return http_tracker_http_port;
	}

	public void setHttp_tracker_http_port(int http_tracker_http_port) {
		this.http_tracker_http_port = http_tracker_http_port;
	}

	public boolean isHttp_anti_steal_token() {
		return http_anti_steal_token;
	}

	public void setHttp_anti_steal_token(boolean http_anti_steal_token) {
		this.http_anti_steal_token = http_anti_steal_token;
	}

	public String getHttp_secret_key() {
		return http_secret_key;
	}

	public void setHttp_secret_key(String http_secret_key) {
		this.http_secret_key = http_secret_key;
	}

	public TrackerGroup getTracker_group() {
		return tracker_group;
	}

	public void setTracker_group(TrackerGroup tracker_group) {
		this.tracker_group = tracker_group;
	}

	public void setTracker_servers(List<String> tracker_servers) {
		this.tracker_servers = tracker_servers;
	}

	public void setNginx_address(List<String> nginx_address) {
		this.nginx_address = nginx_address;
	}
}
