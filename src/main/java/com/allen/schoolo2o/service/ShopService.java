package com.allen.schoolo2o.service;
/** 
* @author 作者 : Allen
* @version 创建时间：2018年5月23日 下午8:27:21 
*/

import java.io.InputStream;

import com.allen.schoolo2o.dto.ShopExecution;
import com.allen.schoolo2o.entity.Shop;

public interface ShopService {
	
	ShopExecution getShopList(Shop shopCondition,int pageIndex,int pageSize);
	
	Shop getShopByShopId(long shopId);
	
	ShopExecution modifyShop(Shop shop, InputStream shopImg, String fileName);

	ShopExecution addShop(Shop shop, InputStream shopImg, String fileName);

}
