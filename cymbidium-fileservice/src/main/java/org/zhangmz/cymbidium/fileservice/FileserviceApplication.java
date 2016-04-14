package org.zhangmz.cymbidium.fileservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @ClassName:FileserviceApplication
 * @Description:SpringBoot 应用
 * @author:张孟志
 * @date:2016年3月31日 下午4:34:04 
 * @version V1.0
 * 说明：启动SpringBoot 应用入口
 */
@SpringBootApplication
public class FileserviceApplication {
	
	public static void main(String[] args) {
        SpringApplication.run(FileserviceApplication.class, args);
    }

//	/**
//	 * 初始化时创建上传文件目录
//	 */
//	public static String ROOT = "classpath:/static/" ;
//	
//	@Bean
//    CommandLineRunner init() {		
//        return (String[] args) -> {
//            new File(ROOT).mkdir();
//        };
//    }
	
//	/**
//	 * 对上传的文件做一些限制
//	 * @return
//	 */
//	@Bean  
//    public MultipartConfigElement multipartConfigElement() {  
//        MultiPartConfigFactory factory = new MultiPartConfigFactory();  
//        factory.setMaxFileSize("128KB");  
//        factory.setMaxRequestSize("128KB");  
//        return factory.createMultipartConfig();  
//    }
}
