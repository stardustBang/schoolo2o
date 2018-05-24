package com.allen.schoolo2o.entity;

import java.util.Date;

/** 
* @author 作者 : Allen
* @version 创建时间：2018年5月5日 下午7:33:43 
*/
public class WechatAuth {
	
	private Long wechatAuthId;
	
	private String openId;
	
	private Date createTime;
	
	private PersonInfo personInfo;

	public Long getWechatAuthId() {
		return wechatAuthId;
	}

	public void setWechatAuthId(Long wechatAuthId) {
		this.wechatAuthId = wechatAuthId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public PersonInfo getPersonInfo() {
		return personInfo;
	}

	public void setPersonInfo(PersonInfo personInfo) {
		this.personInfo = personInfo;
	}
	
	
	
	

}
 