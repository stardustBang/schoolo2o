package com.allen.schoolo2o.service;

import java.util.List;

import com.allen.schoolo2o.dto.ProductCategoryExecution;
import com.allen.schoolo2o.entity.ProductCategory;

/** 
* @author 作者 : Allen
* @version 创建时间：2018年6月6日 上午10:55:20 
*/
public interface ProductCategoryService {
	 
	List<ProductCategory> getProductCategoryList(long shopId);
	
	ProductCategoryExecution batchAddProductCategory(List<ProductCategory> ProductCategoryList);

	/**
	 * 
	* @Description: 类别下的商品里的类别id设为空，在删除类别 
	* @param productCategoryId
	* @param shopId
	* @return  
	* @Return ProductCategoryExecution   
	* @throws
	 */
	ProductCategoryExecution deleteProductCategory(long productCategoryId,long shopId);
}
 