/**
 * 
 */
package com.allen.schoolo2o.enums;

/**
 * @author 作者 : Allen
 * @version 创建时间：2018年6月11日 下午7:04:59
 */
public enum ProductEnum {

	OFFLINE(-1, "非法商品"), SUCCESS(1, "操作成功"), PASS(2, "通过认证"), INNER_ERROR(
			-1001, "操作失败"),EMPTY(-1002, "商品为空");
	;

	private int state;

	private String stateInfo;

	private ProductEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	// 依据传入的state ，返回相应的enum值
	public static ProductEnum stateOf(int state) {
		for (ProductEnum productEnum : values()) {
			if (productEnum.getState() == state) {
				return productEnum;
			}
		}
		return null;

	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

}
