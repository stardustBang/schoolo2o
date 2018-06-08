package com.allen.schoolo2o.enums;

/**
 * @author 作者 : Allen
 * @version 创建时间：2018年6月6日 上午11:43:09
 */
public enum ProductCategoryEnum {

	SUCCESS(1, "创建成功"), 
	INNER_ERROR(-1001, "操作失败"),
	EMPTYP_LIST(-1002,"商品组为空")

	;

	private int code;

	private String message;

	private ProductCategoryEnum(int code, String message) {
		this.code = code;
		this.message = message;
	}

	// 依据传入的state ，返回相应的enum值
	public static ProductCategoryEnum stateOf(int state) {
		for (ProductCategoryEnum productCategoryEnum : values()) {
			if (productCategoryEnum.getCode() == state) {
				return productCategoryEnum;
			}
		}
		return null;

	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
