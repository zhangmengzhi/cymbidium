/*******************************************************************************
 * Copyright (c) 2015, 2016 张孟志 104446930@qq.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.zhangmz.cymbidium.service.helper.aop;

import javax.servlet.http.HttpServletRequest;

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
import org.zhangmz.cymbidium.service.helper.ChannelHelper;
import org.zhangmz.cymbidium.service.helper.AuthorityHelper;
import org.zhangmz.cymbidium.modules.constants.Codes;
import org.zhangmz.cymbidium.modules.constants.Messages;
import org.zhangmz.cymbidium.modules.convert.JsonMapper;
import org.zhangmz.cymbidium.modules.vo.SimpleRequest;

/**
 * Title:AuthorityHelperAOP.java
 * Description:权限管理切面
 * @author:张孟志
 * @date:2016年1月26日 下午3:49:39
 * 说明：
 */
@Aspect
@Component
public class ServiceHelperAOP {
	private static Logger logger = LoggerFactory.getLogger(ServiceHelperAOP.class);
	private static JsonMapper binder = JsonMapper.nonDefaultMapper();

    @Autowired
    private AuthorityHelper authorityHelper;
    
    @Pointcut("execution(* org.zhangmz.cymbidium.service.api.ServiceRestController.*(..)) ")
    private void serviceActionMethod() {}    
	
    @Around("serviceActionMethod()")
	public Object serviceAroundService(ProceedingJoinPoint joinpoint) throws Throwable {
    	SimpleResponse result = null;
		try{
			 // 计算服务时间 begin
	         // long start = System.currentTimeMillis();
			 logger.debug("----------  ServiceHelperAOP  ----------");
				 
		 	// 获取方法参数值，第一个参数HttpServletRequest，且必定包含_token_
            Object[] args = joinpoint.getArgs();
            HttpServletRequest httpRequest = (HttpServletRequest) args[0];
    		
            if(null != httpRequest){
                // 封装参数/检查参数是否符合通信协议
        		SimpleRequest request = ChannelHelper.packageParameters(httpRequest);
            	String TOKEN = request.get_token_();
            	
            	// 目前只支持终端用户判断（不支持控制台用户）
            	if(StringUtils.isNotBlank(TOKEN) 
            		&& authorityHelper.isLogin(TOKEN, 2)){
            		result = (SimpleResponse) joinpoint.proceed();
            	} else {
            		// 提示用户登陆
            		result = new SimpleResponse(Codes.FAILURE_FALSE_NUMBER, Messages.MUST_BE_LOGGED);
            	}
            }
	            
            // long end = System.currentTimeMillis();
            // System.out.println("end! performance took " + (end-start) + " milliseconds");
            // 计算服务时间 end
        }catch(Throwable e){
        	e.printStackTrace();
        	result = new SimpleResponse(Codes.FAILURE_FALSE_NUMBER, e.getMessage());
        }

		logger.debug(binder.toJson(result));
		return result;
	}
}
