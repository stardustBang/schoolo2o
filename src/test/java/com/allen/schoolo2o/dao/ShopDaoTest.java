package com.allen.schoolo2o.dao;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
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
	public void testQueryListAndCount() {
		Shop shop=new Shop();
		PersonInfo owner=new PersonInfo();
		owner.setUserId(1L);
		shop.setOwner(owner);
	/*	List<Shop> shopList=dao.queryShopList(shop, 1, 3);
		System.out.println(shopList.size());*/
		int count=dao.queryShopCount(shop);
		System.out.println(count);
		
		ShopCategory shopCategory =new ShopCategory();
		shopCategory.setShopCategoryId(3L);
		shop.setShopCategory(shopCategory);
		
		List<Shop> shopList=dao.queryShopList(shop, 0, 3);
		System.out.println(shopList.size());
		
	}
	
	@Test
	@Ignore
	public void testInsertShop() {
		Shop shop=new Shop();
		PersonInfo owner=new PersonInfo();
		owner.setUserId(1L);
		Area area=new Area();
		area.setAreaId(2L);
		ShopCategory shopcategory=new ShopCategory();
		shopcategory.setShopCategoryId(3L);
		
		//shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(shopcategory);
		shop.setOwner(owner);
		shop.setShopName("一小块冰");
		shop.setShopDesc("冰块");
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
	@Ignore
	public void testUpdateShop() {
		Shop shop=new Shop();
		shop.setShopId(2L);
		shop.setShopDesc("ice");
		shop.setShopAddr("shanghai");
		
		shop.setLastEditTime(new Date());
		
		dao.updateShop(shop);

	}

}
 