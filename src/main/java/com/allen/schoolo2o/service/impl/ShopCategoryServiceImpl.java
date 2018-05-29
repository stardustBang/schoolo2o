package com.allen.schoolo2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allen.schoolo2o.dao.ShopCategoryDao;
import com.allen.schoolo2o.entity.ShopCategory;
import com.allen.schoolo2o.service.ShopCategoryService;

/** 
* @author 作者 : Allen
* @version 创建时间：2018年5月28日 下午4:41:44 
*/
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService{
	
	@Autowired
	private ShopCategoryDao shopCategoryDao;

	@Override
	public List<ShopCategory> getShopCategoryList(ShopCategory shopCategory) {
		
		return shopCategoryDao.queryShopCategory(shopCategory);
	}

}
 