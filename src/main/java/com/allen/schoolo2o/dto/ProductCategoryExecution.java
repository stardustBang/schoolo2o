package com.allen.schoolo2o.dto;

import java.util.List;

import com.allen.schoolo2o.entity.ProductCategory;
import com.allen.schoolo2o.enums.ProductCategoryEnum;

/**
 * @author 作者 : Allen
 * @version 创建时间：2018年6月6日 下午5:18:29
 */
public class ProductCategoryExecution {

	// 结果状态
	private int state;

	// 状态标识
	private String stateInfo;

	private List<ProductCategory> productCategoryList;

	public ProductCategoryExecution() {
	}

	// 操作失败时的构造器
	public ProductCategoryExecution(ProductCategoryEnum stateEnum) {
		this.state = stateEnum.getCode();
		this.stateInfo = stateEnum.getMessage();

	}

	// 操作成功时的构造器
	public ProductCategoryExecution(ProductCategoryEnum stateEnum, List<ProductCategory> productCategoryList) {
		this.state = stateEnum.getCode();
		this.stateInfo = stateEnum.getMessage();
		this.productCategoryList = productCategoryList;

	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

}