package com.allen.schoolo2o.service; 
/** 
* @author 作者 : Allen
* @version 创建时间：2018年5月23日 下午8:27:21 
*/

import java.io.File;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.allen.schoolo2o.dto.ShopExecution;
import com.allen.schoolo2o.entity.Shop;

public interface ShopService {
	
	ShopExecution addShop(Shop shop,CommonsMultipartFile shopImg);

}
 