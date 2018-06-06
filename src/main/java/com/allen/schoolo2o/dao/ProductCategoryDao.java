package com.allen.schoolo2o.dao;

import java.util.List;

import com.allen.schoolo2o.entity.ProductCategory;
import com.allen.schoolo2o.entity.Shop;

/** 
* @author 作者 : Allen
* @version 创建时间：2018年6月6日 上午10:44:44 
*/
public interface ProductCategoryDao {
	
	List<ProductCategory> queryProductCategoryList(long shopId);

}
 