/**
 * 
 */ 
package com.allen.schoolo2o.controller.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
* @author 作者 : Allen
* @version 创建时间：2018年6月21日 下午5:20:28 
*/
@Controller
@RequestMapping("/frontend")
public class FrontendController {
	
	@RequestMapping("/index")
	public String index() {
		return "frontend/index";
	}
	
	@RequestMapping("/shoplist")
	public String showShoplist() {
		return "frontend/shoplist";
	}
	
	@RequestMapping("/shopdetail")
	public String showShopDetail() {
		return "frontend/shopdetail";
	}


}
 