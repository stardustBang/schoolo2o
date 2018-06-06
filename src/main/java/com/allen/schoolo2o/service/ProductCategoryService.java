package com.allen.schoolo2o.service;

import java.util.List;

import com.allen.schoolo2o.entity.ProductCategory;

/** 
* @author 作者 : Allen
* @version 创建时间：2018年6月6日 上午10:55:20 
*/
public interface ProductCategoryService {
	 
	List<ProductCategory> getProductCategoryList(long shopId);

}
 