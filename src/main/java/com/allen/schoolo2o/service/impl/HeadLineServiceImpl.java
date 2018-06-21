/**
 * 
 */ 
package com.allen.schoolo2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allen.schoolo2o.dao.HeadLineDao;
import com.allen.schoolo2o.entity.HeadLine;
import com.allen.schoolo2o.service.HeadLineService;

/** 
* @author 作者 : Allen
* @version 创建时间：2018年6月18日 下午5:23:36 
*/
@Service
public class HeadLineServiceImpl implements HeadLineService{
	
	@Autowired
	private HeadLineDao headLineDao;

	@Override
	public List<HeadLine> getHeadLineList(HeadLine headLineCondition) {
		
		return headLineDao.queryHeadLine(headLineCondition);
	}

}
 