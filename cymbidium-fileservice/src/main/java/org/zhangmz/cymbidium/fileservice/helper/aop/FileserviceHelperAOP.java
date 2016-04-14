/*******************************************************************************
 * Copyright (c) 2015, 2016 张孟志 104446930@qq.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.zhangmz.cymbidium.fileservice.helper.aop;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zhangmz.cymbidium.modules.vo.SimpleResponse;
import org.zhangmz.cymbidium.fileservice.helper.AuthorityHelper;
import org.zhangmz.cymbidium.modules.constants.Codes;
import org.zhangmz.cymbidium.modules.constants.Messages;

/**
 * Title:AuthorityHelperAOP.java
 * Description:权限管理切面
 * @author:张孟志
 * @date:2016年1月26日 下午3:49:39
 * 说明：
 */
@Aspect
@Component
public class FileserviceHelperAOP {
	private static Logger logger = LoggerFactory.getLogger(FileserviceHelperAOP.class);

    @Autowired
    private AuthorityHelper authorityHelper;
    
    @Pointcut("execution(* org.zhangmz.cymbidium.fileservice.api.FileserviceRestController.upload(..)) ")
    private void serviceActionMethod() {}    
	
    @Around("serviceActionMethod()")
	public Object serviceAroundService(ProceedingJoinPoint joinpoint) throws Throwable {
    	SimpleResponse result = null;
		try{
			 // 计算服务时间 begin
	         // long start = System.currentTimeMillis();
			 logger.debug("----------  FileserviceHelperAOP begin  ----------");
				 
		 	// 获取方法参数值，请求地址包括TOKEN
            Object[] args = joinpoint.getArgs();
            String TOKEN = (String) args[0];
                     	
        	// 目前只支持终端用户判断（不支持控制台用户）
        	if(StringUtils.isNotBlank(TOKEN) 
        		&& authorityHelper.isLogin(TOKEN, 2)){        		
        		result = (SimpleResponse) joinpoint.proceed();
        	} else {
        		// 提示用户登陆
        		result = new SimpleResponse(Codes.FAILURE_FALSE_NUMBER, Messages.MUST_BE_LOGGED);
        	}

			logger.debug("----------  FileserviceHelperAOP  end   ----------");
            // long end = System.currentTimeMillis();
            // System.out.println("end! performance took " + (end-start) + " milliseconds");
            // 计算服务时间 end
        }catch(Throwable e){
        	e.printStackTrace();
        	result = new SimpleResponse(Codes.FAILURE_FALSE_NUMBER, e.getMessage());
        }
		return result;
	}
    
}
