package com.allen.schoolo2o.enums; 
/** 
* @author 作者 : Allen
* @version 创建时间：2018年6月6日 上午11:43:09 
*/
public enum ProductCategoryEnum {
	
	
	
	INNER_ERROR(3,"内部系统错误"),
	
	;
	
	private int code;
	
	private String message;

	private ProductCategoryEnum(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	

	public String getMessage() {
		return message;
	}

	
	
	

}
 