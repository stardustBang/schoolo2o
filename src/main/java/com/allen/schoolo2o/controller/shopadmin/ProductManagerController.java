package com.allen.schoolo2o.controller.shopadmin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allen.schoolo2o.VO.Result;
import com.allen.schoolo2o.dto.ProductCategoryExecution;
import com.allen.schoolo2o.entity.ProductCategory;
import com.allen.schoolo2o.entity.Shop;
import com.allen.schoolo2o.enums.ProductCategoryEnum;
import com.allen.schoolo2o.service.ProductCategoryService;

/**
 * @author 作者 : Allen
 * @version 创建时间：2018年6月6日 上午11:18:47
 */

@Controller
@RequestMapping("/shop")
public class ProductManagerController {

	@Autowired
	private ProductCategoryService productCategoryService;

	@RequestMapping(value = "/getproductcategorylist", method = RequestMethod.GET)
	@ResponseBody
	public Result<List<ProductCategory>> getProductCategoryList(HttpServletRequest request) {
		// to be removed TODO
		Shop shop = new Shop();
		shop.setShopId(1L);
		request.getSession().setAttribute("currentShop", shop);

		Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
		List<ProductCategory> list = null;
		if (currentShop != null && currentShop.getShopId() > 0) {
			list = productCategoryService.getProductCategoryList(currentShop.getShopId());
			return new Result<>(true, list);
		} else {
			ProductCategoryEnum ps = ProductCategoryEnum.INNER_ERROR;
			return new Result<>(false, ps.getCode(), ps.getMessage());
		}

	}

	@RequestMapping(value = "/addproductcategorys", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> batchInsertProductCategory(@RequestBody List<ProductCategory> productCategoryList,
			HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
		for (ProductCategory productCategory : productCategoryList) {
			productCategory.setShopId(currentShop.getShopId());
		}
		if (productCategoryList != null && productCategoryList.size() > 0) {
			try {
			ProductCategoryExecution productCategoryExecution =productCategoryService.batchAddProductCategory(productCategoryList);
			if(productCategoryExecution.getState() == ProductCategoryEnum.SUCCESS.getCode()) {
				modelMap.put("success", true);
			}else {
				modelMap.put("success", false);
				modelMap.put("errMsg", productCategoryExecution.getStateInfo());
			}}catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
				return modelMap;
			}
			

		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg","请至少输入一个商品类别");
		}

		return modelMap;
	}
}
