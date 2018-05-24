package com.allen.schoolo2o.dao;

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
* @version 创建时间：2018年5月7日 下午9:12:11 
*/
public class ShopDaoTest extends BaseTest{
	
	@Autowired
	private ShopDao dao;
	
	@Test
	public void testInsertShop() {
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
		shop.setShopName("一小块冰");
		shop.setShopDesc("冰块先生");
		shop.setShopAddr("北京");
		shop.setPhone("121123");
		shop.setShopImg("http://xxxx.png");
		shop.setPriority(100);
		shop.setCreateTime(new Date());
		shop.setLastEditTime(new Date());
		shop.setAdvice("OOO");
		shop.setEnableStatus(1);
		
		dao.insertShop(shop);
		
	}
	
	@Test
	public void testUpdateShop() {
		Shop shop=new Shop();
		shop.setShopId(33L);
		shop.setShopDesc("Mr.ice");
		shop.setShopAddr("shanghai");
		
		shop.setLastEditTime(new Date());
		
		dao.updateShop(shop);

	}

}
 