/**
 * 
 */ 
package com.allen.schoolo2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.allen.schoolo2o.BaseTest;
import com.allen.schoolo2o.entity.Product;

/** 
* @author 作者 : Allen
* @version 创建时间：2018年6月16日 下午3:33:10 
*/
public class ProductDaoTest extends BaseTest{
	
	@Autowired
	private ProductDao dao;
	
	@Test
	@Ignore
	public void testQueryProductListAndCount() {
		Product product=new Product();
		List<Product> productList=dao.queryProductList(product, 0, 2);
		assertEquals(2, productList.size());
		
	
		product.setProductName("3");
		int result=dao.queryProductCount(product);
		assertEquals(1, result);
	}
	
	
	@Test
	public void testUpdateProductCategoryToNull() {
		int result=dao.updateProductCategoryToNull(1L);
		assertEquals(2, result);
	}
	

}
 