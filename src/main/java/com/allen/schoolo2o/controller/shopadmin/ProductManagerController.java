package com.allen.schoolo2o.controller.shopadmin;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allen.schoolo2o.VO.Result;
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
		// to be removed
		Shop shop = new Shop();
		shop.setShopId(1L);
		request.getSession().setAttribute("currentShop", shop);

		Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
		List<ProductCategory> list = null;
		if (currentShop != null && currentShop.getShopId() >0) {
			list = productCategoryService.getProductCategoryList(currentShop.getShopId());
			return new Result<>(true, list);
		} else {
			ProductCategoryEnum ps = ProductCategoryEnum.INNER_ERROR;
			return new Result<>(false, ps.getCode(), ps.getMessage());
		}

	}

}
