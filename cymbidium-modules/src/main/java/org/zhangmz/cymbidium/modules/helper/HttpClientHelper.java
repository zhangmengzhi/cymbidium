package org.zhangmz.cymbidium.modules.helper;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zhangmz.cymbidium.modules.constants.Codes;
import org.zhangmz.cymbidium.modules.convert.JsonMapper;
import org.zhangmz.cymbidium.modules.vo.SimpleResponse;

/**
 * @ClassName:HttpClientHelper.java
 * @Description:
 * @author:张孟志
 * @date:2016年3月11日下午8:32:36
 * @version V1.0
 * 说明：封装一般的HTTP请求
 * 
 * 修改人：张孟志
 * 修改日期：2016-04-14
 * 修改说明：增加文件上传方法 SimpleResponse doPost(String url, String filePathName)
 *         重构通用请求方法
 */
public class HttpClientHelper {
	private static Logger logger = LoggerFactory.getLogger(HttpClientHelper.class);
	static JsonMapper binder = JsonMapper.nonDefaultMapper();

	public static SimpleResponse doGet(String uri) {
		SimpleResponse simpleResponse = null;

        // 定义HttpClient  
    	CloseableHttpClient httpclient = HttpClients.createDefault();  
        // 实例化HTTP方法  
        HttpGet httpGet = new HttpGet();  

        try {
        	httpGet.setURI(new URI(uri));  
		} catch (Exception e) {
			e.printStackTrace();
			simpleResponse = new SimpleResponse(Codes.FAILURE_FALSE_NUMBER, e.getMessage());
			return simpleResponse;
		}
        
        logger.debug("executing request " + httpGet.getURI());  
        
        simpleResponse = doRequest(httpclient, httpGet);
        
        return simpleResponse;
	}
	
	public static SimpleResponse doPost(String url, List<NameValuePair> formparams) {
		SimpleResponse simpleResponse = null;
		
		// 创建默认的httpClient实例.    
        CloseableHttpClient httpclient = HttpClients.createDefault();  
        // 创建httppost ?groupCode=nogroup&phone=13000000007&password=password
        HttpPost httppost = new HttpPost(url);
        UrlEncodedFormEntity uefEntity;  
        try {
        	uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");  
            httppost.setEntity(uefEntity);  
		} catch (Exception e) {
			e.printStackTrace();
			simpleResponse = new SimpleResponse(Codes.FAILURE_FALSE_NUMBER, e.getMessage());
			return simpleResponse;
		}
        
        logger.debug("executing request " + httppost.getURI());  
        
        simpleResponse = doRequest(httpclient, httppost);
        
        return simpleResponse;
	};
	
	/**
	 * 文件上传方法
	 * @param url           请求地址
	 * @param filePathName  上传文件全路径文件名  /usr/file/myfile.jpg
	 * @return
	 */
	public static SimpleResponse doPost(String url, String filePathName) {
		SimpleResponse simpleResponse = null;
		
		// 创建默认的httpClient实例.    
        CloseableHttpClient httpclient = HttpClients.createDefault();
        
        HttpPost httppost = new HttpPost(url);

        FileBody fileBody = new FileBody(new File(filePathName));
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();   
        multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);        
        multipartEntityBuilder.addPart("file", fileBody);
        multipartEntityBuilder.addTextBody("SerialNumber", "0123456789");
        
        // HttpEntity httpEntity = multipartEntityBuilder.build();
        // httppost.setEntity(httpEntity);  
        httppost.setEntity(multipartEntityBuilder.build());  
        logger.debug("executing request " + httppost.getURI());  
        
        simpleResponse = doRequest(httpclient, httppost);
        
        return simpleResponse;
	}
	
	/**
	 * 通用请求方法
	 * @param httpclient  CloseableHttpClient
	 * @param httppost    HttpPost
	 * @return
	 */
	private static SimpleResponse doRequest(CloseableHttpClient httpclient, HttpRequestBase httpRequest) {
		SimpleResponse simpleResponse = null;
		
		try {   
            CloseableHttpResponse response = httpclient.execute(httpRequest);  
            try {  
                HttpEntity entity = response.getEntity();  
                if (entity != null) {  
                    logger.debug("--------------------------------------");  
                    String rtnString = EntityUtils.toString(entity, "UTF-8");
                    logger.debug("Response content: " + rtnString);  
                    logger.debug("--------------------------------------");
                    simpleResponse = binder.fromJson(rtnString, SimpleResponse.class);                    
                }  
            } finally {  
                response.close();  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
            simpleResponse = new SimpleResponse(Codes.FAILURE_FALSE_NUMBER, e.getMessage());
        } finally {  
            // 关闭连接,释放资源    
            try {  
                httpclient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }
		
        return simpleResponse;  
	}
}
