package com.allen.schoolo2o.controller.shopadmin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/** 
* @author 作者 : Allen
* @version 创建时间：2018年5月23日 下午9:21:16 
*/

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {

	@RequestMapping(value="/registerShop",method=RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> registerShop(HttpServletRequest request){
		//1.接受并转化相应的参数，包括店铺信息以及图片信息
		//2.注册店铺
		//3.返回结果
		
		
		
		
		
		return null;
		
	}
}
 