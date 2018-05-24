package com.allen.schoolo2o.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.allen.schoolo2o.BaseTest;
import com.allen.schoolo2o.entity.Area;
import com.allen.schoolo2o.entity.PersonInfo;
import com.allen.schoolo2o.entity.Shop;
import com.allen.schoolo2o.entity.ShopCategory;

/** 
* @author 作者 : Allen
* @version 创建时间：2018年5月23日 下午9:15:28 
*/
public class ShopServiceTest extends BaseTest{
	
	@Autowired
	private ShopService service;
	
	@Test
	public void testAddShopTest() throws FileNotFoundException {
		Shop shop=new Shop();
		PersonInfo owner=new PersonInfo();
		owner.setUserId(8L);
		Area area=new Area();
		area.setAreaId(3L);
		ShopCategory shopcategory=new ShopCategory();
		shopcategory.setShopCategoryId(14L);
		
		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(shopcategory);
		shop.setShopName("冰");
		shop.setShopDesc("冰块");
		shop.setShopAddr("北京");
		shop.setPhone("111111");
		
		shop.setPriority(100);
		shop.setCreateTime(new Date());
		shop.setLastEditTime(new Date());
		shop.setAdvice("OOO");
		shop.setEnableStatus(1);
		File file =new File("H:/image/ping.jpg");
		InputStream is=new FileInputStream(file);
		service.addShop(shop, is,file.getName());
	}
	
	

}
 