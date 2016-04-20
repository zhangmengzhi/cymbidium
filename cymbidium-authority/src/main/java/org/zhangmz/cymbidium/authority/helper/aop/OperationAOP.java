package org.zhangmz.cymbidium.authority.helper.aop;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.zhangmz.cymbidium.modules.annotation.OperationIdentifier;
import org.zhangmz.cymbidium.modules.constants.Messages;

@Aspect
@Component
public class OperationAOP {
	private static Logger logger = LoggerFactory.getLogger(OperationAOP.class);
	
	//Controller层切点
    @Pointcut("@annotation(org.zhangmz.cymbidium.modules.annotation.OperationIdentifier)")
    public  void controllerAspect() {}
    
    /**
     * TODO 将操作异常保存数据库
     * @Title: doAfterThrowing 
     * @Description: 操作异常记录
     * @param point
     * @param e
     * @throws 
     * 增加人:张孟志
     * 增加日期:2016年4月20日 下午5:10:00
     * 说明：操作发生异常时记录日志
     */
    @AfterThrowing(pointcut = "controllerAspect()", throwing = "e")  
    public  void doAfterThrowing(JoinPoint point, Throwable e) {  
    	logger.error(e.getMessage());
    }
    
    /**
     * TODO 将操作记录保存数据库
     * @Title: doController 
     * @Description: 用于拦截Controller层记录用户的操作
     * @param point
     * @return
     * @throws 
     * 增加人:张孟志
     * 增加日期:2016年4月20日 下午5:19:15
     * 说明：拦截Controller层记录用户的操作，保存操作记录用于管理查询/审计
     * 		根据TOKEN参数来确认操作用户身份信息
     */
    @Around("controllerAspect()")
    public Object doController(ProceedingJoinPoint point) {
    	Object result = null;
		// 执行方法名
		String methodName = point.getSignature().getName();
		String className = point.getTarget().getClass().getSimpleName();
		
		Map<String, Object> map = null;
		Long start = 0L;
		Long end = 0L;
		
		try {
			map=getControllerMethodDescription(point);
			// 执行方法所消耗的时间
			start = System.currentTimeMillis();
			result = point.proceed();
			end = System.currentTimeMillis();
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
		
        //*========控制台输出=========*//
    	logger.debug("=====通知开始===== start:" + start);
    	logger.debug("请求方法:" + className + "." + methodName + "()");
    	logger.debug("方法描述:" + map);
    	logger.debug("=====通知结束=====  end :" + end);
    	logger.debug("=====通知用时=====  time :" + (end - start));
        return result;
    }
    
    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
     @SuppressWarnings("rawtypes")
	public Map<String, Object> getControllerMethodDescription(JoinPoint joinPoint)  throws Exception {
    	 Map<String, Object> map = new HashMap<String, Object>();
    	 String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
         for (Method method : methods) {
             if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                 if (clazzs.length == arguments.length) {
                	 map.put("module", method.getAnnotation(OperationIdentifier.class).module());
                	 map.put("method", method.getAnnotation(OperationIdentifier.class).method());
                	 String de = method.getAnnotation(OperationIdentifier.class).description();
                	 if(StringUtils.isBlank(de))de=Messages.SUCCESS;
                	 map.put("description", de);
                     break;
                }
            }
        }
         return map;
    }
}
