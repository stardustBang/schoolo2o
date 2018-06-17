/**
 * 
 */ 
package com.allen.schoolo2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.allen.schoolo2o.entity.Product;

/** 
* @author 作者 : Allen
* @version 创建时间：2018年6月11日 下午6:38:02 
*/

public interface ProductDao {
	
	/**
     * 分页查询商品，可输入的条件有：商品名（模糊） 商品状态，商品类型，店铺ID
     *rowIndex 从第几行开始查数据
     *pageSize 返回的条数
     * */
	List<Product>queryProductList(@Param("productCondition") Product productCondition,
			@Param("rowIndex")int rowIndex,@Param("pageSize") int pageSize);
	
	/**
	 * 
	* @Description:  查询对应的商品总数
	* @param productCondition
	* @return  
	* @Return List<Product>   
	* @throws
	 */
	
	 int queryProductCount(@Param("productCondition") Product productCondition);
	
	
	int insertProduct(Product product);
	
	Product queryProductByProductId(long productId);
	
	int updateProduct(Product product);
	
	

}
 