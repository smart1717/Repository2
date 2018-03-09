package com.taotao.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="page")
public class PageController {

	@RequestMapping(value="{pageName}",method=RequestMethod.GET)
	public String toPage(@PathVariable("pageName")String pageName){
		return pageName;
	}
	
}
