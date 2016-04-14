package org.zhangmz.cymbidium.fileservice.api;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.zhangmz.cymbidium.modules.utils.Ids;
import org.zhangmz.cymbidium.fileservice.helper.FileDirOperHelper;
import org.zhangmz.cymbidium.modules.constants.Codes;
import org.zhangmz.cymbidium.modules.constants.Messages;
import org.zhangmz.cymbidium.modules.convert.JsonMapper;
import org.zhangmz.cymbidium.modules.vo.SimpleResponse;

/**
 * 
 * @ClassName:FileserviceRestController
 * @Description: 文件服务接口
 * @author:张孟志
 * @date:2016年3月10日下午6:11:02
 * @version V1.0
 * 说明：提供上传文件服务  返回文件链接地址
 */
@RestController
@RequestMapping("/api")
public class FileserviceRestController {
	private static Logger logger = LoggerFactory.getLogger(FileserviceRestController.class);
	private static JsonMapper binder = JsonMapper.nonDefaultMapper();
	
	@Autowired
	private FileDirOperHelper fileDirOperHelper;
	
	@RequestMapping
	public String index() {			
		return "Hello, Welcome to use file service. try localhost:8085/images/cymbidium32.png";
	}
	
	/**
	 * 上传一个文件，返回文件HTTP连接
	 * @param TOKEN
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/fileservice/{TOKEN}", method = RequestMethod.POST)
	public SimpleResponse upload(@PathVariable String TOKEN, 
									@RequestParam("file") MultipartFile file){
//		logger.debug(TOKEN);
//		SimpleResponse simpleResponse = null;		
//		
//		if (!file.isEmpty()) {
//			// 确认上传文件保存目录存在（不存在则创建）
//			String staticDir = fileDirOperHelper.isStaticDirExist();
//			logger.debug("staticDir: " + staticDir);
//			
//			// 上传文件按照日期（目录名yyyyMMdd）保存，获得这个日期字符串用于URL链接
//			String dateString = staticDir.substring(staticDir.lastIndexOf(System.getProperty("file.separator")) + 1 );
//			
//			// 产生一个新文件名，并增加原文件名的后缀
//			String oldFileName = file.getOriginalFilename();
//			String newFileName = Ids.randomBase62(16) + oldFileName.substring(oldFileName.lastIndexOf('.'));			
//			
//			try {
//				BufferedOutputStream stream = new BufferedOutputStream(
//						new FileOutputStream(new File(staticDir + System.getProperty("file.separator") + newFileName)));
//                FileCopyUtils.copy(file.getInputStream(), stream);
//				stream.close();
//				
//				simpleResponse = new SimpleResponse(Codes.SUCCESS_TRUE_NUMBER, 
//													Messages.SUCCESS, 
//													fileDirOperHelper.getLinkUrl() + dateString + "/" + newFileName);
//			} catch (Exception e) {
//				e.printStackTrace();
//				simpleResponse = new SimpleResponse(Codes.FAILURE_FALSE_NUMBER, e.getMessage());
//			}
//		}
//		else {
//			simpleResponse = new SimpleResponse(Codes.FAILURE_FALSE_NUMBER, Messages.UNKNOW_PARAMETER);
//		}
//		
//		logger.debug(binder.toJson(simpleResponse));
//		return simpleResponse;
		
		return saveFile(file);
	}
	
	/**
	 * 上传多个文件，返回成功或失败
	 * @param TOKEN
	 * @param file
	 * @return
	 * TODO 单元测试
	 */
	@RequestMapping(value = "/fileservices/{TOKEN}", method = RequestMethod.POST)
	public SimpleResponse uploads(@PathVariable String TOKEN, 
									HttpServletRequest request){
		logger.debug(TOKEN);
		SimpleResponse simpleResponse = new SimpleResponse(Codes.SUCCESS_TRUE_NUMBER, Messages.SUCCESS);	
		
		List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("files");  
		for (int i =0; i< files.size(); ++i) {
			MultipartFile file = files.get(i);
			simpleResponse = this.saveFile(file);
			if(Codes.FAILURE_FALSE_NUMBER == simpleResponse.getCode()){
				break;
			}
		}	
		
		logger.debug(binder.toJson(simpleResponse));
		return simpleResponse;
	}
	
	/**
	 * 保存文件
	 * @param file
	 * @return
	 */
	private SimpleResponse saveFile(MultipartFile file){
		SimpleResponse simpleResponse = null;	
		
		if (!file.isEmpty()) {
			// 确认上传文件保存目录存在（不存在则创建）
			String staticDir = fileDirOperHelper.isStaticDirExist();
			// logger.debug("staticDir: " + staticDir);
			
			// 上传文件按照日期（目录名yyyyMMdd）保存，获得这个日期字符串用于URL链接
			String dateString = staticDir.substring(staticDir.lastIndexOf(System.getProperty("file.separator")) + 1 );
			
			// 产生一个新文件名，并增加原文件名的后缀
			String oldFileName = file.getOriginalFilename();
			String newFileName = Ids.randomBase62(16) + oldFileName.substring(oldFileName.lastIndexOf('.'));			
			
			try {
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(staticDir + System.getProperty("file.separator") + newFileName)));
                FileCopyUtils.copy(file.getInputStream(), stream);
				stream.close();
				
				simpleResponse = new SimpleResponse(Codes.SUCCESS_TRUE_NUMBER, 
													Messages.SUCCESS, 
													fileDirOperHelper.getLinkUrl() + dateString + "/" + newFileName);
			} catch (Exception e) {
				e.printStackTrace();
				simpleResponse = new SimpleResponse(Codes.FAILURE_FALSE_NUMBER, e.getMessage());
			}
		}
		else {
			simpleResponse = new SimpleResponse(Codes.FAILURE_FALSE_NUMBER, Messages.UNKNOW_PARAMETER);
		}
		
		return simpleResponse;
	}
}
