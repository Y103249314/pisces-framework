/**
 * Copyright (c) 2018 Pisces Technology Co., Ltd.
 */
package com.pisces.framework.datasource.core;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pisces.framework.datasource.service.DynamicDataSourceService;

/**
 * 动态数据源预处理(切换)
 * @author yangxh
 * @date 2018年8月13日 上午11:41:31
 */
@Component
public class DynamicDataSourceFilter implements Filter  {
	@Autowired
	DynamicDataSourceService dynamicDataSourceService;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		dynamicDataSourceService.refreshDataSourcePool();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			int tenant = Integer.valueOf(Optional.ofNullable(request.getParameter("tenant")).orElse("0"));
			dynamicDataSourceService.routeDataSource(tenant);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
	}

}
