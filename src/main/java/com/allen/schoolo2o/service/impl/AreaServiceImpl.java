package com.allen.schoolo2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allen.schoolo2o.dao.AreaDao;
import com.allen.schoolo2o.entity.Area;
import com.allen.schoolo2o.service.AreaService;

/** 
* @author 作者 : Allen
* @version 创建时间：2018年5月10日 下午7:20:16 
*/
@Service
public class AreaServiceImpl implements AreaService{

	@Autowired
	private AreaDao areaDao;
	
	@Override
	public List<Area> getAreaList() {
		
		return areaDao.queryArea();
	}

}
 