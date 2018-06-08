package com.allen.schoolo2o.controller.shopadmin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.connector.Request;
import org.apache.catalina.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.allen.schoolo2o.dto.ShopExecution;
import com.allen.schoolo2o.entity.Area;
import com.allen.schoolo2o.entity.PersonInfo;
import com.allen.schoolo2o.entity.Shop;
import com.allen.schoolo2o.entity.ShopCategory;
import com.allen.schoolo2o.enums.ShopStateEnum;
import com.allen.schoolo2o.exception.ShopOperationException;
import com.allen.schoolo2o.service.AreaService;
import com.allen.schoolo2o.service.ShopCategoryService;
import com.allen.schoolo2o.service.ShopService;
import com.allen.schoolo2o.util.CodeUtil;
import com.allen.schoolo2o.util.HttpServletRequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author 作者 : Allen
 * @version 创建时间：2018年5月23日 下午9:21:16
 */

@Controller
@RequestMapping("/shop")
public class ShopManagerController {

	@Autowired
	private ShopService shopService;

	@Autowired
	private ShopCategoryService shopCategoryService;

	@Autowired
	private AreaService areaSerive;

	@RequestMapping(value = "/modifyshop", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> modifyShop(HttpServletRequest request) throws IOException {
		Map<String, Object> modelMap = new HashMap<String, Object>();

		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "输入了错误的验证码");
			return modelMap;
		}
		// 1.接受并转化相应的参数，包括店铺信息以及图片信息
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		try {
			shop = mapper.readValue(shopStr, Shop.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		CommonsMultipartFile shopImg = null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;

			shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
		}

		// 2.修改店铺
		if (shop != null && shop.getShopId() != null) {
			ShopExecution se;
			try {
				if (shopImg == null) {
					se = shopService.modifyShop(shop, null, null);
				} else {
					se = shopService.modifyShop(shop, shopImg.getInputStream(), shopImg.getOriginalFilename());
				}
				if (se.getState() == ShopStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);

				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", se.getStateInfo());
				}

			} catch (ShopOperationException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());

			}
			return modelMap;

		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入店铺ID");
			return modelMap;
		}

	}

	@RequestMapping(value = "/getshopbyid", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getShopById(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		if (shopId > -1) {
			try {

				Shop shop = shopService.getShopByShopId(shopId);
				List<Area> areaList = areaSerive.getAreaList();
				modelMap.put("shop", shop);
				modelMap.put("areaList", areaList);
				modelMap.put("success", true);
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty shopId");
		}
		return modelMap;

	}

	@RequestMapping(value = "/getshopinitinfo", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getShopInitInfo() {

		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
		List<Area> areaList = new ArrayList<Area>();

		try {
			shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
			areaList = areaSerive.getAreaList();
			modelMap.put("shopCategoryList", shopCategoryList);
			modelMap.put("areaList", areaList);
			modelMap.put("success", true);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}

		return modelMap;

	}

	@RequestMapping(value = "/registershop", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> registerShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();

		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "输入了错误的验证码");
			return modelMap;
		}
		// 1.接受并转化相应的参数，包括店铺信息以及图片信息
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		try {
			shop = mapper.readValue(shopStr, Shop.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		CommonsMultipartFile shopImg = null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;

			shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "上传图片不能为空");
			return modelMap;
		}

		// 2.注册店铺
		if (shop != null && shopImg != null) {
			PersonInfo owner = (PersonInfo) request.getSession().getAttribute("user");
			/*
			 * PersonInfo owner = new PersonInfo(); owner.setUserId(1L);
			 */
			shop.setOwner(owner);
			ShopExecution se;
			try {
				se = shopService.addShop(shop, shopImg.getInputStream(), shopImg.getOriginalFilename());
				if (se.getState() == ShopStateEnum.CHECK.getState()) {
					modelMap.put("success", true);
					// 该用户可以操作的店铺列表
					@SuppressWarnings("unchecked")
					List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
					if (shopList == null || shopList.size() == 0) {
						shopList = new ArrayList<Shop>();
					}
					shopList.add(se.getShop());
					request.getSession().setAttribute("shopList", shopList);

				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", se.getStateInfo());
				}

			} catch (IOException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());

			}

			return modelMap;
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入店铺信息");
			return modelMap;
		}

	}

	@RequestMapping(value = "/getshoplist", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getShopList(HttpServletRequest rquest) {

		Map<String, Object> modelMap = new HashMap<String, Object>();
		PersonInfo user = new PersonInfo();
		//TO be removed TODO
		user.setUserId(1L);
		user.setName("test");
		rquest.getSession().setAttribute("user", user);
		
		
		
		user = (PersonInfo) rquest.getSession().getAttribute("user");
		try {
			Shop shop = new Shop();
			shop.setOwner(user);
			ShopExecution se = shopService.getShopList(shop, 0, 100);

			modelMap.put("shopList", se.getShopList());
			modelMap.put("user", user);
			modelMap.put("success", true);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;

	}

	@RequestMapping(value = "/getshopmanagementinfo", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getShopManagementInfo(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		if (shopId <=0) {
			Object currentObj = request.getSession().getAttribute("currentShop");
			if (currentObj == null) {
				modelMap.put("redirect", true);
				modelMap.put("url", "/schoolo2o/shopadmin/shoplist");
			} else {
				Shop currentShop = (Shop) currentObj;
				modelMap.put("redirect", false);
				modelMap.put("shopId", currentShop.getShopId());
			}
		} else {
			Shop currentShop = new Shop();
			currentShop.setShopId(shopId);
			request.getSession().setAttribute("currentShop", currentShop);
			modelMap.put("redirect", false);
		}

		return modelMap;

	}

}
