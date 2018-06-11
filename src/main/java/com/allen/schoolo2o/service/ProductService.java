/**
 * 
 */ 
package com.allen.schoolo2o.service;

import java.io.InputStream;
import java.util.List;

import com.allen.schoolo2o.dto.ImageHolder;
import com.allen.schoolo2o.dto.ProductExecution;
import com.allen.schoolo2o.entity.Product;

/** 
* @author 作者 : Allen
* @version 创建时间：2018年6月11日 下午6:52:32 
*/

public interface ProductService {
	
	
	ProductExecution addProduct(Product product,ImageHolder imageHolder,List<ImageHolder> imageHolderList );

}
 