/**
 * Copyright (c) 2018 Pisces Technology Co., Ltd.
 */
package com.pisces.framework.datasource.aop;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import com.pisces.framework.datasource.service.DynamicDataSourceService;

@Aspect
@Component
public class DynamicDataSourceAspect {
	@Autowired
	DynamicDataSourceService dynamicDataSourceService;
	
	@Pointcut("@annotation(com.pisces.framework.datasource.aop.DynamicDataSourceRouter)")
	public void pointcut(){}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Around("pointcut()")
	public Object routeDataSource(ProceedingJoinPoint joinPoint) throws Throwable {
		Object result = null;
		int tenant = parseTenantId(joinPoint);
		if (tenant < 0) {
			result = joinPoint.proceed();
		} else if (tenant > 0) {
			dynamicDataSourceService.routeDataSource(tenant);
			result = joinPoint.proceed();
		} else {
			List<Integer> tenantList = dynamicDataSourceService.getAllTenants();
			if (tenantList == null || tenantList.size() == 0) {
				result = joinPoint.proceed();
			} else {
				for (int t : tenantList) {
					dynamicDataSourceService.routeDataSource(t);
					Object ret = joinPoint.proceed();
					if(result == null) {
						result = ret;
					} else if (ret instanceof Collection) {
						((Collection)result).addAll((Collection)ret); //合并集合
					}
					//TODO:
					//  其它情况，可考虑在注解上添加参数指示对返回值的处理动作，比如数值型累加
				}
				dynamicDataSourceService.routeDefaultDataSource();
			}
		}
		return result;
	}
	
	private int parseTenantId(ProceedingJoinPoint joinPoint) {
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		
		DynamicDataSourceRouter dsAnnotation = method.getAnnotation(DynamicDataSourceRouter.class);
		String tenantId = dsAnnotation.value();
		
		if (!StringUtils.isEmpty(tenantId)) {
		    ExpressionParser expressionParser = new SpelExpressionParser();
		    Expression expression = expressionParser.parseExpression(tenantId);
		    EvaluationContext context = new StandardEvaluationContext();
		    
		    Parameter[] params = method.getParameters();
		    Object[] args = joinPoint.getArgs();
		    for (int i = 0; i < params.length; i++) {
		    	if (tenantId.contains("#" + params[i].getName())) {
		    		context.setVariable(params[i].getName(), args[i]);
		    	}
		    }
		    
		    /*
		    LocalVariableTableParameterNameDiscoverer paramDiscover = new LocalVariableTableParameterNameDiscoverer();
		    String[] paramNames = paramDiscover.getParameterNames(method);
		    Object[] args = joinPoint.getArgs();
		    for (int i = 0; i < paramNames.length; i++) {
		    	if (tenantId.contains("#" + paramNames[i])) {
		    		context.setVariable(paramNames[i], args[i]);
		    	}
		    }
		    */
		    
		    tenantId = Optional.ofNullable(expression.getValue(context)).orElse("-1").toString();
		    try {
				return Integer.valueOf(tenantId);
			} catch (Exception e) {
				return -1;
			}
		} else {
			return 0;
		}
	}
	
	private Method getSpecificmethod(ProceedingJoinPoint pjp) {
		MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
		Method method = methodSignature.getMethod();
		// The method may be on an interface, but we need attributes from the
		// target class. If the target class is null, the method will be
		// unchanged.
		Class<?> targetClass = AopProxyUtils.ultimateTargetClass(pjp.getTarget());
		if (targetClass == null && pjp.getTarget() != null) {
			targetClass = pjp.getTarget().getClass();
		}
		Method specificMethod = ClassUtils.getMostSpecificMethod(method, targetClass);
		// If we are dealing with method with generic parameters, find the
		// original method.
		specificMethod = BridgeMethodResolver.findBridgedMethod(specificMethod);
		return specificMethod;
	}
}
