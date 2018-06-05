package com.allen.schoolo2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.allen.schoolo2o.entity.Shop;

/** 
* @author 作者 : Allen
* @version 创建时间：2018年5月7日 下午8:35:35 
*/
public interface ShopDao {
	
    /**
     * 分页查询店铺，可输入的条件有：店铺名（模糊） 店铺状态，店铺类型，区域Id owner
     *rowIndex 从第几行开始查数据
     *pageSize 返回的条数
     * */
	List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition,@Param("rowIndex") int rowIndex,
			@Param("pageSize") int PageSize);
	
	
	int queryShopCount(@Param("shopCondition") Shop shopCondition);
	
	Shop queryByShopId(Long shopId);
	
	int insertShop(Shop shop);
	
	
	int updateShop(Shop shop);

}
 