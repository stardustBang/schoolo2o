package com.allen.schoolo2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.allen.schoolo2o.BaseTest;
import com.allen.schoolo2o.entity.ProductCategory;

/** 
* @author 作者 : Allen
* @version 创建时间：2018年6月6日 下午5:08:27 
*/
public class ProductDaoTest extends BaseTest{
	
	@Autowired
	private ProductCategoryDao dao;
	
	@Test
    @Ignore
	public void testBatchInsertProductCategory() {
		ProductCategory pc1=new ProductCategory();
		pc1.setProductCategoryName("类别一");
		pc1.setPriority(97);
		pc1.setCreateTime(new Date());
		pc1.setShopId(1L);
		
		ProductCategory pc2=new ProductCategory();
		pc2.setProductCategoryName("类别二");
		pc2.setPriority(96);
		pc2.setCreateTime(new Date());
		pc2.setShopId(1L);
		
		
		List<ProductCategory> productCategoryList=new ArrayList<>();
		productCategoryList.add(pc1);
		productCategoryList.add(pc2);
		
		int result=dao.batchInsertProductCategory(productCategoryList);
		
		assertEquals(2, result);
		
	}
	
	@Test
	public void testDeleteProductCategory() {	
		dao.deleteProductCategory(4,1);
		dao.deleteProductCategory(5,1);
		
	}

}
 