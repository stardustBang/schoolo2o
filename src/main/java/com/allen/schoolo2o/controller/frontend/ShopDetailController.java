/**
 * 
 */
package com.allen.schoolo2o.controller.frontend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allen.schoolo2o.dto.ProductExecution;
import com.allen.schoolo2o.entity.Product;
import com.allen.schoolo2o.entity.ProductCategory;
import com.allen.schoolo2o.entity.Shop;
import com.allen.schoolo2o.service.ProductCategoryService;
import com.allen.schoolo2o.service.ProductService;
import com.allen.schoolo2o.service.ShopService;
import com.allen.schoolo2o.util.HttpServletRequestUtil;

/**
 * @author 作者 : Allen
 * @version 创建时间：2018年7月5日 下午9:09:33
 */
@Controller
@RequestMapping("/frontend")
public class ShopDetailController {

	@Autowired
	private ShopService shopService;

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductCategoryService productCategoryService;

	@RequestMapping(value = "/listshopdetailpageinfo", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listShopDetailPageInfo(HttpServletRequest request) {

		Map<String, Object> modelMap = new HashMap<>();
		long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		Shop shop = null;
		List<ProductCategory> productCategoryList = null;
		if (shopId != -1L) {
			shop = shopService.getShopByShopId(shopId);
			productCategoryList = productCategoryService.getProductCategoryList(shopId);
			modelMap.put("shop", shop);
			modelMap.put("productCategoryList", productCategoryList);
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty shopId");
		}
		return modelMap;

	}

	// 依据查询条件分页列出该店铺下面的所有商品
	@RequestMapping(value = "/listproductsbyshop", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listProductsByShop(HttpServletRequest request) {

		Map<String, Object> modelMap = new HashMap<>();

		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");

		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");

		long shopId = HttpServletRequestUtil.getLong(request, "shopId");

		if ((pageIndex > -1) && (pageSize > -1) && (shopId > -1)) {

			long productCategoryId = HttpServletRequestUtil.getLong(request, "productCategoryId");

			String productName = HttpServletRequestUtil.getString(request, "productName");

			Product productCondition = compactProductCondition4Search(shopId, productCategoryId, productName);

			ProductExecution pe = productService.getProductList(productCondition, pageIndex, pageSize);
			modelMap.put("productList", pe.getProductList());
			modelMap.put("count", pe.getCount());
			modelMap.put("success", true);
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty pageSize or pageIndex or shopId");
		}

		return modelMap;

	}

	/** 
	* @Description:  
	* @param shopId
	* @param productCategoryId
	* @param productName
	* @return  
	* @Return Product   
	* @throws 
	*/
	
	 
	private Product compactProductCondition4Search(long shopId, long productCategoryId, String productName) {
	    Product productCondition =new Product();
	    Shop shop=new Shop();
	    shop.setShopId(shopId);
	    productCondition.setShop(shop);
	    if(productCategoryId !=-1L) {
	    	ProductCategory productCategory=new ProductCategory();
	    	productCategory.setProductCategoryId(productCategoryId);;
	    	productCondition.setProductCategory(productCategory);
	    }
	    if(productName !=null) {
	    	productCondition.setProductName(productName);
	    }
	    productCondition.setEnableStatus(1);
		return productCondition;
	}
}
