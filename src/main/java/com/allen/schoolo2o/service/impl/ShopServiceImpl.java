package com.allen.schoolo2o.service.impl;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.allen.schoolo2o.dao.ShopDao;
import com.allen.schoolo2o.dto.ImageHolder;
import com.allen.schoolo2o.dto.ShopExecution;
import com.allen.schoolo2o.entity.Shop;
import com.allen.schoolo2o.enums.ShopStateEnum;
import com.allen.schoolo2o.exception.ShopOperationException;
import com.allen.schoolo2o.service.ShopService;
import com.allen.schoolo2o.util.ImageUtil;
import com.allen.schoolo2o.util.PageCalculator;
import com.allen.schoolo2o.util.PathUtil;

/**
 * @author 作者 : Allen
 * @version 创建时间：2018年5月23日 下午8:29:22
 */

@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopDao shopDao;

	@Override
	@Transactional
	public ShopExecution addShop(Shop shop, ImageHolder imageHolder) {
		if (shop == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP_INFO);
		}
		try {
			shop.setEnableStatus(0);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			int effectedNum = shopDao.insertShop(shop);
			if (effectedNum <= 0) {
				throw new ShopOperationException("店鋪创建失败");
			} else {
				if (imageHolder.getImage() != null) {
					// 图片存储
					try {
						addShopImg(shop, imageHolder);
					} catch (Exception e) {
						throw new ShopOperationException("addShopImg error: " + e.getMessage());

					}
					// 更新店铺的图片地址
					effectedNum = shopDao.updateShop(shop);
					if (effectedNum <= 0) {
						throw new ShopOperationException("更新图片地址失败");

					}
				}
			}

		} catch (Exception e) {
			throw new ShopOperationException("addShop error:" + e.getMessage());
		}

		return new ShopExecution(ShopStateEnum.CHECK, shop);
	}

	private void addShopImg(Shop shop, ImageHolder imageHolder) {
		// 获取shop图片目录的相对值路径
		String dest = PathUtil.getShopImagePath(shop.getShopId());
		String shopImgAddr = ImageUtil.generateThumbnail(dest, imageHolder);
		shop.setShopImg(shopImgAddr);
	}

	@Override
	public Shop getShopByShopId(long shopId) {

		return shopDao.queryByShopId(shopId);
	}

	@Override
	public ShopExecution modifyShop(Shop shop, ImageHolder imageHolder) {
		if (shop == null || shop.getShopId() == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP_INFO);
		} else {
			try {
				// 判断是否需要处理图片
				if (imageHolder.getImage() != null && imageHolder.getImageName() != null && !"".equals(imageHolder.getImageName())) {
					Shop tempShop = shopDao.queryByShopId(shop.getShopId());
					if (tempShop.getShopImg() != null) {

						ImageUtil.deleteFileOrPath(tempShop.getShopImg());
					}
					addShopImg(shop, imageHolder);
				}
				// 更新店铺信息
				shop.setLastEditTime(new Date());
				int effectedNum = shopDao.updateShop(shop);
				if (effectedNum <= 0) {
					return new ShopExecution(ShopStateEnum.INNER_ERROR);
				} else {
					shop = shopDao.queryByShopId(shop.getShopId());
					return new ShopExecution(ShopStateEnum.SUCCESS, shop);
				}
			} catch (Exception e) {
				throw new ShopOperationException("modifyShop error " + e.getMessage());

			}
		}
	}

	@Override
	public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
		int rowIndex = PageCalculator.calculatorRowIndex(pageIndex, pageSize);
		List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIndex, pageSize);
		int count = shopDao.queryShopCount(shopCondition);
		ShopExecution se = new ShopExecution();
		if (shopList != null) {
			se.setShopList(shopList);
			se.setCount(count);
		} else {
			se.setState(ShopStateEnum.INNER_ERROR.getState());
		}

		return se;
	}

}
