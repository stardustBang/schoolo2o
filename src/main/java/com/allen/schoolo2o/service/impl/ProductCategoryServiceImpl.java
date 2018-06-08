package com.allen.schoolo2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.allen.schoolo2o.dao.ProductCategoryDao;
import com.allen.schoolo2o.dto.ProductCategoryExecution;
import com.allen.schoolo2o.entity.ProductCategory;
import com.allen.schoolo2o.enums.ProductCategoryEnum;
import com.allen.schoolo2o.exception.ProductCategoryException;
import com.allen.schoolo2o.service.ProductCategoryService;

/**
 * @author 作者 : Allen
 * @version 创建时间：2018年6月6日 上午10:56:29
 */

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

	@Autowired
	private ProductCategoryDao productCategoryDao;

	@Override
	public List<ProductCategory> getProductCategoryList(long shopId) {

		return productCategoryDao.queryProductCategoryList(shopId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.allen.schoolo2o.service.ProductCategoryService#batchAddProductCategory(
	 * java.util.List)
	 */
	@Override
	@Transactional
	public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) {

		if (productCategoryList != null && productCategoryList.size() > 0) {

			try {
				int result = productCategoryDao.batchInsertProductCategory(productCategoryList);

				if (result > 0) {
					return new ProductCategoryExecution(ProductCategoryEnum.SUCCESS, productCategoryList);
				} else {
					throw new ProductCategoryException("店铺类别创建失败");
				}
			} catch (Exception e) {
				throw new ProductCategoryException("batchAddProductCategory error:" + e.getMessage());

			}

		} else {
			return new ProductCategoryExecution(ProductCategoryEnum.EMPTYP_LIST);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.allen.schoolo2o.service.ProductCategoryService#deleteProductCategory(
	 * long, long)
	 */
	@Override
	@Transactional
	public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId) {
		// TODO 将此商品类别下的商品类别Id设为空
		try {
			int result = productCategoryDao.deleteProductCategory(productCategoryId, shopId);
			if (result < 0) {
				throw new ProductCategoryException("商品类别删除失败");

			} else {
				return new ProductCategoryExecution(ProductCategoryEnum.SUCCESS);
			}
		} catch (Exception e) {
			throw new ProductCategoryException("deleteProductCategory error:" + e.getMessage());
		}

	}

}
