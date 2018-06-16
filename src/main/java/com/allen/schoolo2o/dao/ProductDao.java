/**
 * 
 */ 
package com.allen.schoolo2o.dao;

import com.allen.schoolo2o.entity.Product;

/** 
* @author 作者 : Allen
* @version 创建时间：2018年6月11日 下午6:38:02 
*/

public interface ProductDao {
	
	int insertProduct(Product product);
	
	Product queryProductByProductId(long productId);
	
	int updateProduct(Product product);
	
	

}
 