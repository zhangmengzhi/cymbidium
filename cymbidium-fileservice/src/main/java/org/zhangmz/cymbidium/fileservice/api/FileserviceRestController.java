package org.zhangmz.cymbidium.fileservice.api;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zhangmz.cymbidium.modules.service.channel.IChannelService;
import org.zhangmz.cymbidium.modules.convert.JsonMapper;
import org.zhangmz.cymbidium.modules.vo.SimpleResponse;

/**
 * 
 * @ClassName:FileserviceRestController
 * @Description: 渠道数据接入口
 * @author:张孟志
 * @date:2016年3月10日下午6:11:02
 * @version V1.0
 * 说明：
 */
@RestController
@RequestMapping("/api/fileservice")
public class FileserviceRestController {
	private static Logger logger = LoggerFactory.getLogger(FileserviceRestController.class);
	private static JsonMapper binder = JsonMapper.nonDefaultMapper();
    
	@RequestMapping(value = "/{TOKEN}", method = RequestMethod.POST)
	public SimpleResponse index(@PathVariable String TOKEN, HttpServletRequest httpRequest) {
		IChannelService channelService = null;
		SimpleResponse sr = null;
		
				
		return sr;
	}
}
