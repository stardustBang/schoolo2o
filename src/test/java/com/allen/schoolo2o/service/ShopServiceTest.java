package com.allen.schoolo2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.allen.schoolo2o.BaseTest;
import com.allen.schoolo2o.dto.ShopExecution;
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
	public void testGetShopList() {
		Shop shop=new Shop();
		ShopCategory shopCategory =new ShopCategory();
		shopCategory.setShopCategoryId(3L);
		shop.setShopCategory(shopCategory);
		
		ShopExecution se=service.getShopList(shop, 1, 3);
		System.out.println(se.getShopList().size());
		System.out.println(se.getCount());
	}
	
	@Test
	@Ignore
	public void testUpdateShop() throws FileNotFoundException {
		Shop shop=new Shop();
		
		shop.setShopId(2L);
		
		shop.setShopName("正式的店铺");
				
		File file =new File("H:/image/pong.png");
		InputStream is=new FileInputStream(file);
		service.modifyShop(shop,is,file.getName());
		
	}
	 
	@Test
	@Ignore
	public void testAddShop() throws FileNotFoundException {
		Shop shop=new Shop();
		PersonInfo owner=new PersonInfo();
		owner.setUserId(8L);
		Area area=new Area();
		area.setAreaId(3L);
		ShopCategory shopcategory=new ShopCategory();
		shopcategory.setShopCategoryId(14L);
		
		//shop.setOwner(owner);
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
 