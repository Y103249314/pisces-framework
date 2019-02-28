/**
 * Copyright (c) 2017 Pisces Technology Co., Ltd.
 */
package com.pisces.framework.datasource.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注意：数据源切换要在Transcantional注解外层使用，事务中不支持数据源切换
 * @author yangxh
 * @date 2018年8月28日 下午2:20:31
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface DynamicDataSourceRouter {
	String value() default ""; // spEl表达式，指定租户ID
}