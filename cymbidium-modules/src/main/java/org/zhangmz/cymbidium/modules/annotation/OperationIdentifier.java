package org.zhangmz.cymbidium.modules.annotation;

import java.lang.annotation.*;  

/**
 * 
 * @ClassName:OperationIdentifier
 * @Description:自定义注解 操作标识
 * @author:张孟志
 * @date:2016年4月20日 下午4:52:16 
 * @version V1.0
 * 说明：出于操作记录、审计等要求，拦截Controller的方法记录日志
 *     需要结合AOP（作为@Pointcut）
 *     使用方式，注解方法。凡需要处理业务逻辑的.都需要记录操作日志
 *     @OperationIdentifier(module="系统管理-用户管理",method="新增用户-addEntity",description="新增用户操作")
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)  
@Documented  
public  @interface OperationIdentifier {  
  
	String module()  default "";       // 模块名称 系统管理-用户管理－列表页面
	String method()  default "";       // 新增用户
    String description()  default "";  // 操作描述
}  