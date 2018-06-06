package com.allen.schoolo2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allen.schoolo2o.dao.ProductCategoryDao;
import com.allen.schoolo2o.entity.ProductCategory;
import com.allen.schoolo2o.service.ProductCategoryService;

/** 
* @author 作者 : Allen
* @version 创建时间：2018年6月6日 上午10:56:29 
*/

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService{
	
	@Autowired
	private ProductCategoryDao productCategoryDao;

	@Override
	public List<ProductCategory> getProductCategoryList(long shopId) {
		
		return productCategoryDao.queryProductCategoryList(shopId);
	}

	
}
 