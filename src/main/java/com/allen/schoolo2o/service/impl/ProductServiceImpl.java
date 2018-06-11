/**
 * 
 */ 
package com.allen.schoolo2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allen.schoolo2o.dao.ProductDao;
import com.allen.schoolo2o.dao.ProductImgDao;
import com.allen.schoolo2o.dto.ImageHolder;
import com.allen.schoolo2o.dto.ProductExecution;
import com.allen.schoolo2o.entity.Product;
import com.allen.schoolo2o.service.ProductService;

/** 
* @author 作者 : Allen
* @version 创建时间：2018年6月11日 下午7:55:57 
*/
@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private ProductImgDao productImgDao;

	/* (non-Javadoc)
	 * @see com.allen.schoolo2o.service.ProductService#addProduct(com.allen.schoolo2o.entity.Product, com.allen.schoolo2o.dto.ImageHolder, java.util.List)
	 */
	@Override
	public ProductExecution addProduct(Product product, ImageHolder imageHolder, List<ImageHolder> imageHolderList) {
		
		return null;
	}
	
	

	
	

}
 