/**
 * 
 */ 
package com.allen.schoolo2o.dao;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.allen.schoolo2o.entity.HeadLine;

/** 
* @author 作者 : Allen
* @version 创建时间：2018年6月18日 下午3:55:15 
*/
public interface HeadLineDao {
	/**
	 * 根据传入的查询条件（头条名查询头条）
	* @Description:  
	* @param headLineCondition
	* @return  
	* @Return List<HeadLine>   
	* @throws
	 */
	List<HeadLine> queryHeadLine(@Param("headLineCondition") HeadLine headLineCondition );
	

}
 