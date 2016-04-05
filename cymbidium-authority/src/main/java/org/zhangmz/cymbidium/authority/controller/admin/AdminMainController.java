/*******************************************************************************
 * Copyright (c) 2015, 2016 张孟志 104446930@qq.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.zhangmz.cymbidium.authority.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.zhangmz.cymbidium.modules.constants.AdminUrl;
import org.zhangmz.cymbidium.authority.helper.AdminMainHelper;

/**
 * Title:AdminMainController.java
 * Description:控制台主页控制器
 * @author:张孟志
 * @date:2016年1月25日 下午7:58:13
 * 说明：
 */
@Controller
@RequestMapping("/admin")
public class AdminMainController {

    @Autowired
    private AdminMainHelper adminMainHelper;
    
    //modify by 张孟志 2016年1月26日 下午4:14:04 使用AOP重构权限管理 begin
    /*
    @Autowired
    private AuthorityHelper authorityHelper;
    
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView home(@RequestParam("TOKEN") String token) {
		ModelAndView result;
		// 需要判断是否为管理员，一个方案是使用AuthorityHelper做硬编码，另一个方案是使用AOP
		if(authorityHelper.isAdmin(token)){
			result = new ModelAndView(AdminUrl.mainPage);
		} else {
			result = new ModelAndView(AdminUrl.loginPage);
	        result.addObject("message", Messages.USER_NOT_ADMIN);
		}
        result.addObject("mainInfo", adminMainHelper.getMainInfo(token));
        result.addObject("TOKEN", token);
		return result;
    }
	*/

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView home(@RequestParam("TOKEN") String token) {
		ModelAndView result = new ModelAndView(AdminUrl.mainPage);
		result.addObject("mainInfo", adminMainHelper.getMainInfo(token));
        result.addObject("TOKEN", token);
		return result;
    }
	
	//modify by 张孟志 2016年1月26日 下午4:14:04 使用AOP重构权限管理 end
	
	@RequestMapping("/blank")
	public ModelAndView blank(@RequestParam("TOKEN") String token) {		
		ModelAndView result = new ModelAndView("admin/blank2");		
		return result;
    }
}
