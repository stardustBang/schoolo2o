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

import com.allen.schoolo2o.entity.Area;
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

	@RequestMapping(value = "/listshoppageinfo", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listShopPageInfo(HttpServletRequest request) {

		Map<String, Object> modelMap = new HashMap<>();
		long parentId = HttpServletRequestUtil.getLong(request, "parentId");
		List<ShopCategory> shopCategoryList = null;
		if (parentId != -1) {
			// 如果parentId存在，则取出该一级shopcategory下的二级shopcategory列表
			try {
				ShopCategory shopCategoryCondition = new ShopCategory();
				ShopCategory parent = new ShopCategory();
				parent.setParentId(parentId);
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
		// TODO

		return null;

	}

}
