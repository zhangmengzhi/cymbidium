//package org.zhangmz.cymbidium.fileservice.api;
//
//import java.util.ArrayList;
//import java.util.List;
//import org.apache.http.NameValuePair;
//import org.apache.http.message.BasicNameValuePair;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.boot.test.WebIntegrationTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.zhangmz.cymbidium.fileservice.FileserviceApplication;
//import org.zhangmz.cymbidium.modules.constants.Messages;
//import org.zhangmz.cymbidium.modules.helper.HttpClientHelper;
//import org.zhangmz.cymbidium.modules.vo.SimpleResponse;
//
///**
// * 
// * @ClassName:FileserviceRestControllerTest.java
// * @Description: 上传文件服务测试
// * @author:张孟志
// * @date:2016年4月14日下午3:29:23
// * @version V1.0
// * 说明：注意该测试要保证cymbidium-authoriry服务启动
// */
////SpringJUnit支持，由此引入Spring-Test框架支持！ 
//@RunWith(SpringJUnit4ClassRunner.class) 
////指定我们SpringBoot工程的Application启动类
//@SpringApplicationConfiguration(classes = FileserviceApplication.class) 
////由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
////使用@WebIntegrationTest注解需要将@WebAppConfiguration注释掉
////@WebAppConfiguration 
//@WebIntegrationTest("server.port:8085")// 使用0表示端口号随机，也可以具体指定如8888这样的固定端口
//public class FileserviceRestControllerTest {
//	// 权限管理服务
//	public static String loginUrl = "http://localhost:8081/api/authoriry/login";
//	public static String logoutUrl = "http://localhost:8081/api/authoriry/logout";
//	public static String isLoginUrl = "http://localhost:8081/api/authoriry/islogin";
//
//	// 本应用服务地址
//	public static String serviceUrl = "http://localhost:8085/api/fileservice";
//	
//	@Test
//	public void serviceTest() throws Exception {
//		// 先登陆获得TOKEN
//		SimpleResponse simpleResponse = this.getToken();
//
//		// 正常场景验证
//		String url = serviceUrl + "/" + (String) simpleResponse.getResult("result"); 
//        simpleResponse = HttpClientHelper.doPost(url, this.getClass().getClassLoader().getResource("cymbidium32.png").getPath());        
//		Assert.assertTrue(simpleResponse.getCode() == 1);
//        Assert.assertTrue(simpleResponse.getMessage().equals(Messages.SUCCESS));
//        System.out.println(simpleResponse.getResult("result"));
//        
//        // 使用错误的TOKEN发送请求
//		String urlErr = serviceUrl + "/err"; 
//		simpleResponse = HttpClientHelper.doPost(urlErr, this.getClass().getClassLoader().getResource("cymbidium32.png").getPath());        
//		Assert.assertTrue(simpleResponse.getCode() == 0);
//        Assert.assertTrue(simpleResponse.getMessage().equals(Messages.MUST_BE_LOGGED));
//	}
//	
//	/**
//	 * 
//	 * @Title: getToken 
//	 * @Description: 登陆获取TOKEN
//	 * @return
//	 * @throws 
//	 * 增加人:张孟志
//	 * 增加日期:2016年3月21日 下午4:37:18
//	 * 说明：登陆获取TOKEN
//	 * 该接口已废弃，仅用于测试
//	 */
//	public static SimpleResponse getToken(){
//		// 创建参数队列    
//        List<NameValuePair> fs = new ArrayList<NameValuePair>();  
//        fs.add(new BasicNameValuePair("groupCode", "nogroup"));  
//        fs.add(new BasicNameValuePair("phone", "13000000007"));
//        fs.add(new BasicNameValuePair("password", "password"));        
//		SimpleResponse simpleResponse = HttpClientHelper.doPost(loginUrl, fs);
//        return simpleResponse;
//	}
//}
