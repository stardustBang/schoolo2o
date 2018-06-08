package com.allen.schoolo2o.controller.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/** 
* @author 作者 : Allen
* @version 创建时间：2018年5月28日 上午11:20:10 
*/

@Controller
@RequestMapping(value="/shopadmin" , method=RequestMethod.GET)
public class ShopAdminController {
	
	@RequestMapping("/shopoperation")
	public String shopOperation() {
		return "shop/shopoperation";
		
	}
	
	@RequestMapping("/shoplist")
	public String shopList() {
		return "shop/shoplist";
	}
	
	@RequestMapping("/shopmanagement")
	public String shopManagement() {
		return "shop/shopmanagement";
	}
	
	
	@RequestMapping("/productcategorymanagement")
	public String productCategoryManagement() {
		return "shop/productcategorymanagement";
	}



}
 