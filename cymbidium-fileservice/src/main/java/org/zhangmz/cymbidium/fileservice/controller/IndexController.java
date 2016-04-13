/*******************************************************************************
 * Copyright (c) 2015, 2016 张孟志 104446930@qq.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.zhangmz.cymbidium.fileservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Title:IndexController.java
 * Description:主页控制器
 * @author:张孟志
 * @date:2016年1月25日 上午11:08:36
 * 说明：
 */

@Controller
@EnableWebMvc
@RequestMapping("/")
public class IndexController {
	
	@RequestMapping
	String home() {
		return "redirect:/index";
    }
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView result = new ModelAndView("index");
		return result;
    }
}
