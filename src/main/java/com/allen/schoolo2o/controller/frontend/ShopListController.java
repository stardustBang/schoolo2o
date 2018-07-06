/**
 * 
 */
package com.allen.schoolo2o.controller.frontend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allen.schoolo2o.dto.ShopExecution;
import com.allen.schoolo2o.entity.Area;
import com.allen.schoolo2o.entity.Shop;
import com.allen.schoolo2o.entity.ShopCategory;
import com.allen.schoolo2o.service.AreaService;
import com.allen.schoolo2o.service.ShopCategoryService;
import com.allen.schoolo2o.service.ShopService;
import com.allen.schoolo2o.util.HttpServletRequestUtil;

/**
 * @author 作者 : Allen
 * @version 创建时间：2018年6月21日 下午8:42:10
 */
@Controller
@RequestMapping("/frontend")
public class ShopListController {

	@Autowired
	private ShopService shopService;

	@Autowired
	private ShopCategoryService shopCategoryService;

	@Autowired
	private AreaService areaService;

	@RequestMapping(value = "/listshopspageinfo", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listShopsPageInfo(HttpServletRequest request) {

		Map<String, Object> modelMap = new HashMap<>();
		long parentId = HttpServletRequestUtil.getLong(request, "parentId");
		List<ShopCategory> shopCategoryList = null;
		if (parentId !=-1L) {
			// 如果parentId存在，则取出该一级shopcategory下的二级shopcategory列表
			try {
				ShopCategory shopCategoryCondition = new ShopCategory();
				ShopCategory parent = new ShopCategory();
				parent.setShopCategoryId(parentId);
				shopCategoryCondition.setParent(parent);
				shopCategoryList = shopCategoryService.getShopCategoryList(shopCategoryCondition);

			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}

		} else {
			try {
				// 如果parentid不存在，则取出所有一级shopcategory(用户在首页选择的是全部商店列表)
				shopCategoryList = shopCategoryService.getShopCategoryList(null);
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
		}
		modelMap.put("shopCategoryList", shopCategoryList);
		List<Area> areaList = null;
		try {
			areaList = areaService.getAreaList();
			modelMap.put("areaList", areaList);
			modelMap.put("success", true);

		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}

		return modelMap;

	}

	@RequestMapping(value = "/listshops", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listShops(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		//获取页码
		int pageIndex=HttpServletRequestUtil.getInt(request, "pageIndex");
		
		int pageSize=HttpServletRequestUtil.getInt(request, "pageSize");
		
		if(pageIndex >-1 &&(pageSize >-1)) {
			long parentId=HttpServletRequestUtil.getLong(request, "parentId");
			
			long shopCategoryId=HttpServletRequestUtil.getLong(request, "shopCategoryId");
			
			long areaId=HttpServletRequestUtil.getLong(request, "areaId");
			
			String shopName=HttpServletRequestUtil.getString(request, "shopName");
			
			Shop shopCondition=compactShopCondition4Search(parentId,shopCategoryId,areaId,shopName);
		
			ShopExecution se=shopService.getShopList(shopCondition, pageIndex, pageSize);
		
			modelMap.put("shopList", se.getShopList());
			modelMap.put("count", se.getCount());
			modelMap.put("success", true);
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty pageSize or pageIndex");
		}

		return modelMap;
	}

	/** 
	* @Description:  
	* @param parentId
	* @param shopCategoryId
	* @param areaId
	* @param shopName
	* @return  
	* @Return Shop   
	* @throws 
	*/
	
	 
	private Shop compactShopCondition4Search(long parentId, long shopCategoryId, long areaId, String shopName) {
	    Shop shopCondition =new Shop();
	    if(parentId !=-1L) {
	    	//查询某个一级商品列表下的所有二级商品列表
	    	ShopCategory childCategory =new ShopCategory();
	    	ShopCategory parentCategory=new ShopCategory();
	    	parentCategory.setShopCategoryId(parentId);
	    	childCategory.setParent(parentCategory);
	    	shopCondition.setShopCategory(childCategory);
	    }
	    if(shopCategoryId !=-1L) {
	    	//查询某个二级商品列表下的店铺列表
	    	ShopCategory shopCategory =new ShopCategory();
	    	shopCategory.setShopCategoryId(shopCategoryId);;
	    	shopCondition.setShopCategory(shopCategory);
	    }
	    if(areaId !=-1L) {
	    	Area area=new Area();
	    	area.setAreaId(areaId);
	    	shopCondition.setArea(area);
	    }
	    if(shopName !=null) {
	    	shopCondition.setShopName(shopName);
	    }
	    
	    shopCondition.setEnableStatus(1);
	    
		return shopCondition;
	}

}
