/**
 * 
 */ 
package com.allen.schoolo2o.service;

import java.util.List;

import com.allen.schoolo2o.entity.HeadLine;

/** 
* @author 作者 : Allen
* @version 创建时间：2018年6月18日 下午5:20:45 
*/
public interface HeadLineService {
	
	
	List<HeadLine> getHeadLineList(HeadLine headLineCondition);

}
 