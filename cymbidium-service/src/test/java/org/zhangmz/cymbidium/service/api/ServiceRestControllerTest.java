/**
 * 
 */
package org.zhangmz.cymbidium.service.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zhangmz.cymbidium.service.ServiceApplication;

/**
 * 
 * @ClassName:ServiceRestControllerTest.java
 * @Description:测试服务入口
 * @author:张孟志
 * @date:2016年4月5日下午6:29:58
 * @version V1.0
 * 说明：
 */
//SpringJUnit支持，由此引入Spring-Test框架支持！ 
@RunWith(SpringJUnit4ClassRunner.class) 
//指定我们SpringBoot工程的Application启动类
@SpringApplicationConfiguration(classes = ServiceApplication.class) 
//由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
//使用@WebIntegrationTest注解需要将@WebAppConfiguration注释掉
//@WebAppConfiguration 
@WebIntegrationTest("server.port:8083")// 使用0表示端口号随机，也可以具体指定如8888这样的固定端口
public class ServiceRestControllerTest {
	// 权限管理服务
	public static String loginUrl = "http://localhost:8081/api/authoriry/login";
	public static String logoutUrl = "http://localhost:8081/api/authoriry/logout";
	public static String isLoginUrl = "http://localhost:8081/api/authoriry/islogin";

	// 本应用服务地址
	public static String serviceUrl = "http://localhost:8083/api/service";
	
	@Test
	public void serviceTest() throws Exception {
		
	}
}
