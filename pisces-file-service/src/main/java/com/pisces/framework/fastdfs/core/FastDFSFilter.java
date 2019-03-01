/**
 * Copyright (c) 2018 Pisces Technology Co., Ltd.
 */
package com.pisces.framework.fastdfs.core;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FastDFSFilter implements Filter {
	
	@Value("${fastdfs.default_group:group1}")
	private String defaultGroup;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String serverName = request.getServerName();
		if (StringUtils.isBlank(serverName) || isIPAddress(serverName)) {
			FastDFSContext.setCurrentGroup(defaultGroup);
		} else {
			int idx = serverName.indexOf(".");
			if (idx <= 0 || idx > 16) {
				FastDFSContext.setCurrentGroup(defaultGroup);
			} else {
				FastDFSContext.setCurrentGroup(serverName.substring(0, idx));
			}
		}
		chain.doFilter(request, response);
	}
	
	private boolean isIPAddress(String addr) {
		if (addr.length() < 7 || addr.length() > 15 || "".equals(addr)) {
			return false;
		} else {
			String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
			Pattern pat = Pattern.compile(rexp);
			Matcher mat = pat.matcher(addr);
			return mat.find();
		}
	}
}
