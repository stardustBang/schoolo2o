package com.allen.schoolo2o.enums;

import com.allen.schoolo2o.dto.ShopExecution;

/** 
* @author 作者 : Allen
* @version 创建时间：2018年5月23日 下午8:07:48 
*/
public enum ShopStateEnum {
	
	CHECK(0,"审核中"),
	OFFLINE(-1,"非法店铺"),
	SUCCESS(1,"操作成功"),
	PASS(2,"通过认证"),
	INNER_ERROR(3,"内部系统错误"),
	NULL_SHOPID(-1002, "ShopId为空"), 
	NULL_SHOP_INFO(-1003, "传入了空的信息"),
	
	
	;
	
	private int state;
	
	private String stateInfo;

	private ShopStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}
	
	//依据传入的state ，返回相应的enum值
	public static ShopStateEnum stateOf(int state) {
		for(ShopStateEnum shopStateEnum:values()) {
			if(shopStateEnum.getState() ==state) {
				return shopStateEnum;
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
 