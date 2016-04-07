package org.zhangmz.cymbidium.authority.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zhangmz.cymbidium.authority.orm.model.Enduser;
import org.zhangmz.cymbidium.authority.service.EnduserService;
import org.zhangmz.cymbidium.modules.constants.Codes;
import org.zhangmz.cymbidium.modules.constants.Messages;
import org.zhangmz.cymbidium.modules.convert.JsonMapper;
import org.zhangmz.cymbidium.modules.vo.SimpleResponse;

/** 
 * @ClassName:AuthorityRestController
 * @Description:渠道认证接入口
 * @author:张孟志
 * @date:2016年3月10日下午6:16:27
 * @version V1.0
 * 说明：渠道认证接入口
 * 手机/平板（手持设备）、PC客户端/机器人（APP）、等多种渠道认证入口
 * 登陆入口： /api/authoriry/login?groupCode=nogroup&phone=13000000007&password=password
 * 退出入口：/api/authoriry/logout/e3b6d2afc7d34af5a5ec3ff3253e1ee2
 * 判断是否已登陆：/api/authoriry/islogin/e3b6d2afc7d34af5a5ec3ff3253e1ee2
 */
@RestController
@RequestMapping("/api/authoriry")
public class AuthorityRestController {
	private static Logger logger = LoggerFactory.getLogger(AuthorityRestController.class);

	private static JsonMapper binder = JsonMapper.nonDefaultMapper();
	
	@Autowired
    private EnduserService enduserService;
	
	/**
	 * 
	 * @Title: login 
	 * @Description: 登录请求
	 * @param groupCode   用户组编码
	 * @param phoneEmail  手机号码或Email
	 * @param password    密码
	 * @return
	 * @throws 
	 * 增加人:张孟志
	 * 增加日期:2016年1月25日 下午7:14:05
	 * 说明：根据用户组编码、手机号码或Email、密码登录
	 * 用户组编码可以改为用户组ID，但是会对用户泄露编码方式
	 */
	// @RequestMapping(value = "/login", method = RequestMethod.POST)
	@RequestMapping(value = "/login")
	public SimpleResponse login(@RequestParam("groupCode") String groupCode, 
						@RequestParam("phone") String phone, 
						@RequestParam("password") String password) {
		SimpleResponse sr = null;
		
		try {
			logger.debug("groupCode:"+ groupCode 
					+ ", phone:"+ phone 
					+ ", password:" + password);
			String token = enduserService.login(groupCode, phone, password);
			sr = new SimpleResponse(Codes.SUCCESS_TRUE_NUMBER, Messages.SUCCESS, token);
		} catch (Exception e) {
			e.printStackTrace();
			sr = new SimpleResponse(Codes.FAILURE_FALSE_NUMBER, e.getMessage());
		}
		
		logger.debug(binder.toJson(sr));
		return sr;
    }
	
	/**
	 * 
	 * @Title: logout 
	 * @Description: 退出
	 * @param token
	 * @return
	 * @throws 
	 * 增加人:张孟志
	 * 增加日期:2016年1月25日 下午8:05:22
	 * 说明：清理用户登录信息
	 */
	@RequestMapping(value = "/logout/{token}")
	public SimpleResponse logout(@PathVariable String token) {
		SimpleResponse sr = null;
		
		try {
			enduserService.logout(token);
			sr = new SimpleResponse(Codes.SUCCESS_TRUE_NUMBER, Messages.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			sr = new SimpleResponse(Codes.FAILURE_FALSE_NUMBER, e.getMessage());
		}
		
		logger.debug(binder.toJson(sr));
		return sr;
    }

	/**
	 * 判断TOKEN是否已登陆
	 * @param token
	 * @return
	 */
    @RequestMapping(value = "/islogin/{token}")
	public SimpleResponse isLogin(@PathVariable String token){
    	SimpleResponse sr = null;
		
		try {
			if(enduserService.isLogin(token)){
				sr = new SimpleResponse(Codes.SUCCESS_TRUE_NUMBER, Messages.SUCCESS);
				// add by zhangmz 2016-04-07 返回用户信息  
				// 为了安全重置无效密码，同时防止客户端做对象映射时因null失败
				Enduser enduser = enduserService.getLoginCache(token);
				enduser.setPassword("nothing");
				sr.setResult("loginCache", enduser);
			}else{
				sr = new SimpleResponse(Codes.FAILURE_FALSE_NUMBER, Messages.FAILURE);
			}			
		} catch (Exception e) {
			e.printStackTrace();
			sr = new SimpleResponse(Codes.FAILURE_FALSE_NUMBER, e.getMessage());
		}
		
		logger.debug(binder.toJson(sr));
		return sr;
	}
}
