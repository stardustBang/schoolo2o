/**
 * 
 */
package com.allen.schoolo2o.controller.shopadmin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.allen.schoolo2o.dto.ImageHolder;
import com.allen.schoolo2o.dto.ProductExecution;
import com.allen.schoolo2o.entity.Product;
import com.allen.schoolo2o.entity.ProductCategory;
import com.allen.schoolo2o.entity.ProductImg;
import com.allen.schoolo2o.entity.Shop;
import com.allen.schoolo2o.enums.ProductEnum;
import com.allen.schoolo2o.service.ProductCategoryService;
import com.allen.schoolo2o.service.ProductService;
import com.allen.schoolo2o.util.CodeUtil;
import com.allen.schoolo2o.util.HttpServletRequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author 作者 : Allen
 * @version 创建时间：2018年6月12日 下午2:46:07
 */
@Controller
@RequestMapping("/shop")
public class ProductManagerController {

	@Autowired
	private ProductService productService;
	@Autowired
	private ProductCategoryService productCategoryService;

	// 支持上传商品详情图的最大数量
	private static final int IMAGEMAXCOUNT = 6;

	@RequestMapping("/addproduct")
	@ResponseBody
	public Map<String, Object> addProduct(HttpServletRequest request) {

		Map<String, Object> modelMap = new HashMap<>();
		// 验证码校验
		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "输入了错误的验证码");
			return modelMap;
		}
		// 接受前端的参数的变量的初始化，包括商品，缩略图，详情图列表实体类
		ObjectMapper mapper = new ObjectMapper();
		Product product = null;
		String productStr = HttpServletRequestUtil.getString(request, "productStr");

		ImageHolder thumbnail = null;
		List<ImageHolder> productImgList = new ArrayList<>();
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());

		try {
			// 若请求中存在文件流，则取出相关的文件（包括缩略图和详情图）
			if (multipartResolver.isMultipart(request)) {
				thumbnail = handleImage(request, thumbnail, productImgList);

			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", "上传图片不能为空");
				return modelMap;
			}

		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
			return modelMap;
		}
		try {
			// 尝试获取前端传过来的表单string 流并将其转换成product实体类
			product = mapper.readValue(productStr, Product.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
			return modelMap;
		}
		// 若Product信息，缩略图以及详情图列表为非空，则开始进行商品添加
		if (product != null && thumbnail != null && productImgList.size() > 0) {
			try {
				// 从session 中获取当前店铺的Id并赋值给productId,减少对前端数据依赖
				Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");

				product.setShop(currentShop);
				// 执行添加操作
				ProductExecution pe = productService.addProduct(product, thumbnail, productImgList);
				if (pe.getState() == ProductEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", pe.getStateInfo());
				}

			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
				return modelMap;
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入商品信息");
		}
		return modelMap;
	}

	/**
	 * @Description: @param request @param thumbnail @param
	 *               productImgList @return @throws IOException @Return
	 *               ImageHolder @throws
	 */

	private ImageHolder handleImage(HttpServletRequest request, ImageHolder thumbnail, List<ImageHolder> productImgList)
			throws IOException {
		MultipartHttpServletRequest multipartRequest = null;
		multipartRequest = (MultipartHttpServletRequest) request;
		// 取出缩略图并构建ImageHolder对象
		CommonsMultipartFile thumbnailFile = (CommonsMultipartFile) multipartRequest.getFile("thumbnail");
		if (thumbnailFile != null) {
			thumbnail = new ImageHolder(thumbnailFile.getInputStream(), thumbnailFile.getOriginalFilename());
		}

		// 取出详情图列表并构建List<ImageHolder>列表对象，最多支持六张图片上传
		for (int i = 0; i < IMAGEMAXCOUNT; i++) {
			CommonsMultipartFile productImgFile = (CommonsMultipartFile) multipartRequest.getFile("productImg" + i);

			if (productImgFile != null) {
				// 若取出的第i个详情图片文件流不为空，则将奇加入详情图列表
				ImageHolder productImg = new ImageHolder(productImgFile.getInputStream(),
						productImgFile.getOriginalFilename());
				productImgList.add(productImg);
			} else {
				// 若取出的第i个详情图片文件流为空，则终止循环
				break;
			}

		}
		return thumbnail;
	}

	@RequestMapping("/getproductbyid")
	@ResponseBody

	public Map<String, Object> getProductById(@RequestParam long productId) {
		Map<String, Object> modelMap = new HashMap<>();

		if (productId > -1) {
			Product product = productService.getProductById(productId);

			List<ProductCategory> productCategoryList = productCategoryService
					.getProductCategoryList(product.getShop().getShopId());
			modelMap.put("product", product);
			modelMap.put("productCategoryList", productCategoryList);
			modelMap.put("success", true);

		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "productId为空");
		}

		return modelMap;
	}

	@RequestMapping(value = "/modifyproduct", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> modifyProduct(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		// 是商品编辑时调用，还是商品上下架时调用
		// 若为前者进行验证码判断，或者跑过验证码判断
		boolean statusChange = HttpServletRequestUtil.getBoolean(request, "statusChange");

		if (!statusChange && !CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "输入了错误的验证码");
			return modelMap;
		}
		// 接受前端参数的变量的初始化，包括商品，缩略图，详情列表实体类
		ObjectMapper mapper = new ObjectMapper();
		Product product = null;
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		ImageHolder thumbnail = null;
		List<ImageHolder> productImgList = new ArrayList<>();
		try {
			// 若请求中存在文件流，则取出相关的文件（包括缩略图和详情图）
			if (multipartResolver.isMultipart(request)) {
				thumbnail=handleImage(request, thumbnail, productImgList);
			}
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
			return modelMap;
		}

		try {
			String productStr = HttpServletRequestUtil.getString(request, "productStr");
			product = mapper.readValue(productStr, Product.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
			return modelMap;
		}
		if (product != null) {
			try {
				// 从session 中获取当前店铺的Id并赋值给productId,减少对前端数据依赖
				Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
				product.setShop(currentShop);

				// 开始进行商品信息变更操作
				ProductExecution pe = productService.updateProduct(product, thumbnail, productImgList);
				if (ProductEnum.SUCCESS.getState() == pe.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", "商品信息更新失败");
				}
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
				return modelMap;
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入商品信息");
		}
		return modelMap;

	}

	@RequestMapping(value = "/getproductlistbyshop", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getProductListByShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
		Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
		if ((pageIndex > -1) && (pageSize > -1) && (currentShop != null) && (currentShop.getShopId() != null)) {

			// 获取传入的需要检索的条件，包括是否需要从某个商品类别以及模糊查找商品名去筛选某个店铺下的商品列表
			// 筛选的条件可以进行排列组合
			long productCategoryId = HttpServletRequestUtil.getLong(request, "productCategoryId");
			String productName = HttpServletRequestUtil.getString(request, "productName");

			Product productCondition = compactProductCondition(currentShop.getShopId(), productCategoryId, productName);

			// 传入查询条件以及分页信息进行查询，返回相应商品列表以及总数
			ProductExecution pe = productService.getProductList(productCondition, pageIndex, pageSize);
			modelMap.put("productList", pe.getProductList());
			modelMap.put("count", pe.getCount());
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty pageSize or pageindex or shopid");
		}
		return modelMap;

	}

	private Product compactProductCondition(long shopId, long productCategoryId, String productName) {
		Product product = new Product();
		Shop shop = new Shop();
		shop.setShopId(shopId);
		product.setShop(shop);
		// 若有指定类别的要求则添加进去
		if (productCategoryId != -1L) {
			ProductCategory productCategory = new ProductCategory();
			productCategory.setProductCategoryId(productCategoryId);
			product.setProductCategory(productCategory);
		}

		// 若有商品名模糊查询的要求则添加进去
		if (productName != null) {
			product.setProductName(productName);
		}
		return product;
	}
}
