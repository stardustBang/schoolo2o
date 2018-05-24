package com.allen.schoolo2o.dao;

import java.util.List;



import com.allen.schoolo2o.entity.Area;

/** 
* @author 作者 : Allen
* @version 创建时间：2018年5月7日 下午8:23:07 
*/


public interface AreaDao {
	
	List<Area> queryArea();
	
	int updateArea(Area area);
	
	int addArea(Area area);
	
	int deleteArea(Long areaId);
	

}
 