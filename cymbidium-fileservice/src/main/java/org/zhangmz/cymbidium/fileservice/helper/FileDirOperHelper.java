/**
 * 
 */
package org.zhangmz.cymbidium.fileservice.helper;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName:FileDirOperHelper.java
 * @Description:
 * @author:张孟志
 * @date:2016年4月14日下午12:31:57
 * @version V1.0
 * 说明：
 */
@Component
public class FileDirOperHelper {

	@Value("${cymbidium.fileservice.linkUrl}")
	private String linkUrl;
	
	@Value("${cymbidium.fileservice.uploadDir}")
	private String uploadDir;

	public String getLinkUrl() {
		return linkUrl;
	}
	
	public String getUploadDir() {
		return uploadDir;
	}
	
	/**
	 * @return 静态目录（日期格式）
	 */
	public String isStaticDirExist() {
		String dateString = getDateString();
		String savePath = uploadDir + dateString;
		
		File file = new File(savePath);
		if(!file.exists()){
			file.mkdirs();
		}
		
		// return dateString;
		return savePath;
	}
	
	
	private String getDateString() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(date);
	}
	
}
