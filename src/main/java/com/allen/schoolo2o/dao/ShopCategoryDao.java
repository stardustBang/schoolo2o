package com.allen.schoolo2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.allen.schoolo2o.entity.ShopCategory;

/** 
* @author 作者 : Allen
* @version 创建时间：2018年5月28日 下午4:23:16 
*/
public interface ShopCategoryDao {
	
	List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition") ShopCategory shopCategory);

}
 