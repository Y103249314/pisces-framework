/**
 * Copyright (c) 2018 Pisces Technology Co., Ltd.
 */
package com.pisces.framework.core.context;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Function: PiscesContext 保存当前线程中用户的公共信息，eg：租户id. <br/>
 */
public class PiscesContext extends ConcurrentHashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	
    protected static Class<? extends PiscesContext> contextClass = PiscesContext.class;

    protected static final ThreadLocal<? extends PiscesContext> threadLocal = new ThreadLocal<PiscesContext>() {
        @Override
        protected PiscesContext initialValue() {
            try {
                return contextClass.newInstance();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    };
    
    public static PiscesContext getCurrentContext() {
        return threadLocal.get();
    }
    
    /**
     * Function: 获取当前访问的租户id. <br/>
     */
    public String getTenantId() {
        return null;
    }
    
    /**
     * Function: 获取当前登陆用户id. <br/>
     */
    public String getLoginUserId(){
    	return null;
    }
    
    /**
     * Function: 在当前线程中保存当前登陆用户. <br/>
     */
    public void setLoginUser(/*User loginUser*/) {
    	//put("loginUser", loginUser);
    }
}
