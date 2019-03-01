/**
 * Copyright (c) 2018 Pisces Technology Co., Ltd.
 */
package com.pisces.framework.fastdfs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pisces.framework.fastdfs.core.FastDFSTemplate;
import com.pisces.framework.fastdfs.core.FastDFSTemplateFactory;

@Configuration
public class FastDFSConfig {
	
    @Autowired
    private FastDFSTemplateFactory fastDFSFactory;

    @Bean(initMethod = "init")
    @ConfigurationProperties(prefix = "fastdfs")
    public FastDFSTemplateFactory fastDFSFactory() {
    	return new FastDFSTemplateFactory();
    }

    @Bean
    public FastDFSTemplate fastDFSTemplate() {
        return new FastDFSTemplate(fastDFSFactory);
    }
}