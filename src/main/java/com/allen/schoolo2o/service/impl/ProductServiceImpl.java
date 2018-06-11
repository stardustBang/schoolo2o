/**
 * 
 */
package com.allen.schoolo2o.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.allen.schoolo2o.dao.ProductDao;
import com.allen.schoolo2o.dao.ProductImgDao;
import com.allen.schoolo2o.dto.ImageHolder;
import com.allen.schoolo2o.dto.ProductExecution;
import com.allen.schoolo2o.entity.Product;
import com.allen.schoolo2o.entity.ProductImg;
import com.allen.schoolo2o.enums.ProductEnum;
import com.allen.schoolo2o.exception.ProductException;
import com.allen.schoolo2o.service.ProductService;
import com.allen.schoolo2o.util.ImageUtil;
import com.allen.schoolo2o.util.PathUtil;

/**
 * @author 作者 : Allen
 * @version 创建时间：2018年6月11日 下午7:55:57
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ProductImgDao productImgDao;

	/**
	 * 1.处理缩略图，获取缩略图相对路径并赋值给product 2.往tb_product写入商品信息，获取productId
	 * 3.结合productId批量商品详情图 4.将商品详情图列表批量插入到tb_product_img中
	 */
	@Override
	@Transactional
	public ProductExecution addProduct(Product product, ImageHolder imageHolder, List<ImageHolder> imageHolderList) {

		// 空值判断
		if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
			product.setCreateTime(new Date());
			product.setLastEditTime(new Date());

			product.setEnableStatus(1);

			// 添加缩略图
			if (imageHolder != null) {
				addImage(product, imageHolder);
			}
			try {
				int result = productDao.insertProduct(product);
				if (result <= 0) {
					throw new ProductException("创建商品失败");
				}

			} catch (Exception e) {
				throw new ProductException("创建商品失败" + e.getMessage());

			}

			// 若商品详情图不为空，则添加
			if (imageHolderList != null && imageHolderList.size() > 0) {
				addProductImageList(product, imageHolderList);

			}
			return new ProductExecution(ProductEnum.SUCCESS, product);
		} else {
			return new ProductExecution(ProductEnum.EMPTY);
		}

	}

	/**
	 * @Description:添加商品详情图 @param product @param imageHolderList @Return
	 * void @throws
	 */

	private void addProductImageList(Product product, List<ImageHolder> imageHolderList) {
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		List<ProductImg> productImgList = new ArrayList<>();

		for (ImageHolder imageHolder : imageHolderList) {
			String imgAddr = ImageUtil.generateNormalImg(imageHolder, dest);
			ProductImg productImg = new ProductImg();
			productImg.setImgAddr(imgAddr);
			productImg.setProductId(product.getProductId());
			productImg.setCreateTime(new Date());
			productImgList.add(productImg);
		}
		//如果确实有图片需要添加，则执行批量添加操作
		if (productImgList.size() > 0) {
			try {
				int result = productImgDao.batchInsertProductImg(productImgList);
				if (result <=0) {
					throw new ProductException("创建商品详情图片失败");

				}
			} catch (Exception e) {
				throw new ProductException("创建商品详情图片失败" + e.toString());

			}
		}

	}

	/**
	 * @Description: 添加缩略图 @param product @param imageHolder @Return void @throws
	 */

	private void addImage(Product product, ImageHolder imageHolder) {
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		String imageAddr = ImageUtil.generateThumbnail(imageHolder, dest);
		product.setImgAddr(imageAddr);
	}

}
