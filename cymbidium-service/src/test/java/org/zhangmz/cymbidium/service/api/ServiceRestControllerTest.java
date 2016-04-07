///**
// * 
// */
//package org.zhangmz.cymbidium.service.api;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//import org.apache.http.NameValuePair;
//import org.apache.http.message.BasicNameValuePair;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.boot.test.WebIntegrationTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.zhangmz.cymbidium.service.ServiceApplication;
//import org.zhangmz.cymbidium.modules.constants.Messages;
//import org.zhangmz.cymbidium.modules.helper.HttpClientHelper;
//import org.zhangmz.cymbidium.modules.vo.SimpleResponse;
//
///**
// * 
// * @ClassName:ServiceRestControllerTest.java
// * @Description:测试服务入口
// * @author:张孟志
// * @date:2016年4月5日下午6:29:58
// * @version V1.0
// * 说明：注意该测试要保证cymbidium-authoriry服务启动
// */
////SpringJUnit支持，由此引入Spring-Test框架支持！ 
//@RunWith(SpringJUnit4ClassRunner.class) 
////指定我们SpringBoot工程的Application启动类
//@SpringApplicationConfiguration(classes = ServiceApplication.class) 
////由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
////使用@WebIntegrationTest注解需要将@WebAppConfiguration注释掉
////@WebAppConfiguration 
//@WebIntegrationTest("server.port:8083")// 使用0表示端口号随机，也可以具体指定如8888这样的固定端口
//public class ServiceRestControllerTest {
//	// 权限管理服务
//	public static String loginUrl = "http://localhost:8081/api/authoriry/login";
//	public static String logoutUrl = "http://localhost:8081/api/authoriry/logout";
//	public static String isLoginUrl = "http://localhost:8081/api/authoriry/islogin";
//
//	// 本应用服务地址
//	public static String serviceUrl = "http://localhost:8083/api/service";
//	
//	@Test
//	public void serviceTest() throws Exception {
//		// 先登陆获得TOKEN
//		SimpleResponse simpleResponse = this.getToken();
//		
//		// 发起终端用户列表查询请求
//        List<NameValuePair> formparams = new ArrayList<NameValuePair>();  
//        formparams.add(new BasicNameValuePair("_channel_", "1"));  
//        formparams.add(new BasicNameValuePair("_version_", "1.8"));
//        formparams.add(new BasicNameValuePair("_token_", (String) simpleResponse.getResult("result")));    
//        formparams.add(new BasicNameValuePair("_code_", "ENDUSER_LIST"));  // 注意这个 code
//        formparams.add(new BasicNameValuePair("_data_", 
//								        				"{\n" +
//								        				"    \"page\": 2,\n" + 
//								        				"    \"rows\": 2\n" + 
//								        				"}"));
//        
//        simpleResponse = HttpClientHelper.doPost(serviceUrl, formparams);
//		Assert.assertTrue(simpleResponse.getCode() == 1);
//        Assert.assertTrue(simpleResponse.getMessage().equals(Messages.SUCCESS));
//        System.out.println("--------------------------------------");
//        System.out.println("result (enduser list): " + simpleResponse.getResult("result"));
//        System.out.println("--------------------------------------");
//        
//        // 打印返回的列表，注意对象转换后的数据类型 List<LinkedHashMap<String, Object>>
//        List list = (List) simpleResponse.getResult("result");
//        // 由于开发中数据库会发生变化，不用Assert
//        // Assert.assertTrue(2 == list.size());
//        for (Object object : list) {
//        	System.out.println("--------------------------------------");  
//        	Map ee = (LinkedHashMap) object;
//        	printMap2(ee);
//        	System.out.println("--------------------------------------");  
//		}
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
//	
//	public static void printMap2(Map<String, Object> parameters){  
//		for (Iterator iterator = parameters.entrySet().iterator(); iterator.hasNext();) {
//			Entry entry = (Entry) iterator.next();
//			System.out.println("Key:"+ entry.getKey().toString());  
//            System.out.println("Value:"+ entry.getValue().toString());  
//		}
//    }
//}
