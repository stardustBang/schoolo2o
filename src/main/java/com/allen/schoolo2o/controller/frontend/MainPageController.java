/**
 * 
 */ 
package com.allen.schoolo2o.controller.frontend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allen.schoolo2o.entity.HeadLine;
import com.allen.schoolo2o.entity.ShopCategory;
import com.allen.schoolo2o.service.HeadLineService;
import com.allen.schoolo2o.service.ShopCategoryService;

/** 
* @author 作者 : Allen
* @version 创建时间：2018年6月18日 下午6:04:02 
*/
@Controller
@RequestMapping("/frontend")
public class MainPageController {
	
	@Autowired
	private ShopCategoryService shopCategoroyService;
	
	@Autowired
	private HeadLineService headLineService;
	
	@RequestMapping(value="/listmainpageinfo",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> listMainPageInfo(){
		
		Map<String,Object> modelMap = new HashMap<String, Object>();
		List<ShopCategory> shopCategoryList=new ArrayList<>();
		try {
			shopCategoryList=shopCategoroyService.getShopCategoryList(null);
			modelMap.put("shopCategoryList", shopCategoryList);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		List<HeadLine> headLineList=new ArrayList<HeadLine>();
		try {
			HeadLine headLineCondition=new HeadLine();
			headLineCondition.setEnableStatus(1);
			headLineList=headLineService.getHeadLineList(headLineCondition);
			modelMap.put("headLineList", headLineList);

		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		modelMap.put("success",true);
		return modelMap;
	}

}
 