package org.zhangmz.cymbidium.service.api;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zhangmz.cymbidium.service.helper.ChannelHelper;
import org.zhangmz.cymbidium.modules.service.channel.IChannelService;
import org.zhangmz.cymbidium.service.helper.AuthorityHelper;
import org.zhangmz.cymbidium.modules.vo.SimpleRequest;
import org.zhangmz.cymbidium.modules.constants.Codes;
import org.zhangmz.cymbidium.modules.constants.Messages;
import org.zhangmz.cymbidium.modules.convert.JsonMapper;
import org.zhangmz.cymbidium.modules.vo.SimpleResponse;

/**
 * 
 * @ClassName:ServiceRestController.java
 * @Description: 服务接入口
 * @author:张孟志
 * @date:2016年4月5日下午6:46:28
 * @version V1.0
 * 说明： 服务接入口
 * 手机/平板（手持设备）、PC客户端/机器人（APP）、等多种渠道数据入口
 * 
	接口请求地址: http://xxx.xxx.xxx/api/service
	接口请求方式: HTTP POST
	接口报文编码: UTF-8
	请求报文格式:
		请求报文有且仅有如下的字段，各字段分别解释如下：
		_channel_		表示请求来源区分，手机/平板（手持设备）、PC客户端/机器人（APP）等
		_version_ 		接口版本
		_token_			认证码，登陆成功后系统会返回一个认证码
		废弃_enduserid_		终端用户Id
		废弃_groupid_		用户组Id
		_code_			接口服务代码，唯一标示一个接口
		_data_			base64编码后的业务数据
	为方便说明，我们将_data_外的所有字段称为"基础字段"。 
	base64编码前的_data_格式是如下的json(所有的json的键都要加双引号，值不一定。暂时使用明文)
	{
		"datatime" : "2014-09-23 09:11:11",
		"address" : "xxx"
	}
	
	返回报文格式：
	返回报文是json格式，包含3个字段，code、message、resultMap，
	code表示状态（1表示成功，0表示失败），message表示提示信息，resultMap是返回的业务数据（或错误提示信息）。
	成功报文示例：
	{
		"code":"1",
		"message":"成功",
		"resultMap": {
			"customservice": "(010)62693990",
			"dataversion": 131
		}
	}
	
	失败报文示例：	
	{
		"code":"0",
		"message":"失败",
		"resultMap": {
			"result":"e3b6d2afc7d34af5a5ec3ff3253e1ee2"
		}
	}
	
/api/service?_channel_=1&_version_=1.1&_token_=3d190529f12546288dd4141e8e3ce113&_code_=ENDUSER_LIST&_data_=
 * 
 * _code_ 说明：
 * REGIST_ENDUSER  终端用户注册          groupCode, phone, password
 * LOGIN_ENDUSER   终端用户登陆          groupCode, phone, password
 * LOGOUT_ENDUSER  终端用户退出
 * ENDUSER_LIST    查询终端用户列表   page, rows
 */
@RestController
@RequestMapping("/api/service")
public class ServiceRestController {
	private static Logger logger = LoggerFactory.getLogger(ServiceRestController.class);
	private static JsonMapper binder = JsonMapper.nonDefaultMapper();

    @Autowired
    private AuthorityHelper authorityHelper;
    
	@RequestMapping
	public SimpleResponse index(HttpServletRequest httpRequest) {
		IChannelService channelService = null;
		SimpleResponse sr = null;
		
		// 封装参数/检查参数是否符合通信协议
		SimpleRequest request = ChannelHelper.packageParameters(httpRequest);
		logger.debug(binder.toJson(request));
		
		// 判断终端用户是否有权限访问（判断是否登陆）
		// authorityHelper.isLogin(request.get_token_(), 2);
		try {
			// 注册/登陆/退出（？）不需要做权限校验
			if(!"REGIST_ENDUSER".equals(request.get_code_()) 
					&&  !"LOGIN_ENDUSER".equals(request.get_code_()) 
					&&  !"LOGOUT_ENDUSER".equals(request.get_code_())){
				if(StringUtils.isBlank(request.get_token_()) 
					// 现在只支持终端用户
					|| !authorityHelper.isLogin(request.get_token_(), 2)){
					return new SimpleResponse(Codes.FAILURE_FALSE_NUMBER, 
												Messages.MUST_BE_LOGGED);
				}				
			}			
		} catch (Exception e) {
			e.printStackTrace();
			// return new SimpleResponse(Codes.FAILURE_FALSE_NUMBER, e.getMessage());
			return new SimpleResponse(Codes.FAILURE_FALSE_NUMBER, Messages.MUST_BE_LOGGED);
		}
		
		// 根据_code_来获取服务类
		logger.debug(request.get_code_());
		channelService = ChannelHelper.localizingResources(request.get_code_());
		
		// 服务处理
		try {
			sr = channelService.doService(request);
		} catch (Exception e) {
			e.printStackTrace();
			// sr = new SimpleResponse(Codes.FAILURE_FALSE_NUMBER, e.getMessage());
			sr = new SimpleResponse(Codes.FAILURE_FALSE_NUMBER, Messages.SYSTEM_BUSY);
		}
				
		return sr;
	}
}
