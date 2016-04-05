/*******************************************************************************
 * Copyright (c) 2015, 2016 张孟志 104446930@qq.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.zhangmz.cymbidium.service.helper.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.zhangmz.cymbidium.modules.vo.SimpleResponse;

/**
 * Title:AuthorityHelperAOP.java
 * Description:权限管理切面
 * Company: DigitalChina 2016
 * @author:张孟志
 * @date:2016年1月26日 下午3:49:39
 * 说明：
 */
@Aspect
@Component
public class ServiceHelperAOP {

    @Pointcut("execution(* org.zhangmz.cymbidium.service.api.ServiceRestController.*(..)) ")
    private void serviceActionMethod() {}    
	
    @Around("serviceActionMethod()")
	public Object serviceAroundService(ProceedingJoinPoint joinpoint) throws Throwable {
    	SimpleResponse result = null;
		 try{
			 	// 计算服务时间 begin
	            // long start = System.currentTimeMillis();
	            
			 	System.out.println("----------  ServiceHelperAOP  ----------");
	            
	            // long end = System.currentTimeMillis();
	            // System.out.println("end! performance took " + (end-start) + " milliseconds");
	            // 计算服务时间 end
	        }catch(Throwable e){
	        	e.printStackTrace();    			
	        }
		 
		 return result;
	}
}
