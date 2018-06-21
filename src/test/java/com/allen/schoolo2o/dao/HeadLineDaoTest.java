/**
 * 
 */ 
package com.allen.schoolo2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.allen.schoolo2o.BaseTest;
import com.allen.schoolo2o.entity.HeadLine;

/** 
* @author 作者 : Allen
* @version 创建时间：2018年6月18日 下午4:26:01 
*/
public class HeadLineDaoTest extends BaseTest{
	
	@Autowired
	private HeadLineDao dao;
	
	@Test
	public void testqueryHeadLine() {
		HeadLine headline=new HeadLine();
		List<HeadLine> list=dao.queryHeadLine(headline);
		assertEquals(2, list.size());
		
	}

}
 