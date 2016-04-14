package org.zhangmz.cymbidium.fileservice.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	@Value("${cymbidium.fileservice.uploadDir}")
	private String uploadDir;
	
	/**
	 * 增加文件访问路径
	 * 相当于虚拟路径
	 */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/")
        									.addResourceLocations("file:" + uploadDir);
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
