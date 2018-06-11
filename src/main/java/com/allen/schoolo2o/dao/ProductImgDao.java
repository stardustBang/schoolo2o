/**
 * 
 */ 
package com.allen.schoolo2o.dao;

import java.util.List;

import com.allen.schoolo2o.entity.ProductImg;

/** 
* @author 作者 : Allen
* @version 创建时间：2018年6月11日 下午6:38:16 
*/

public interface ProductImgDao {
	
	/**
	 * 批量 插入图片
	* @Description:  
	* @param productImg
	* @return   
	* @Return int   
	* @throws
	 */
	int  batchInsertProductImg(List<ProductImg> productImgList);

}
 