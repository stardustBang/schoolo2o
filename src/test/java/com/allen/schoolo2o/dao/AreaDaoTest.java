package com.allen.schoolo2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.allen.schoolo2o.BaseTest;
import com.allen.schoolo2o.entity.Area;

/** 
* @author 作者 : Allen
* @version 创建时间：2018年5月7日 下午8:46:09 
*/
public class AreaDaoTest extends BaseTest{
	
	@Autowired
	private AreaDao dao;
	
	@Test
	public void queryAreaTest() {
		List<Area> arealist=dao.queryArea();
		assertEquals(2, arealist.size());
		
	}

}
 