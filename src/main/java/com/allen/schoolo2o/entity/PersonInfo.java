package com.allen.schoolo2o.entity;

import java.util.Date;

/** 
* @author 作者 : Allen
* @version 创建时间：2018年5月5日 下午7:22:39 
*/
public class PersonInfo {
	
	private Long userId;
	 
	private String username;
	
	private String email;
	
	private String gender;
	
	//状态
	//0:禁止使用本商城   1：允许使用本商城
	private Integer enableStatus;
	
	//头像
	private String profileImg;
    
	//用户的身份标识
	//1.顾客   2.店家 3.超级管理员
	private Integer userType;
	
	private Date createTime;
	
	private Date lastEditTime;

	

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastEditTime() {
		return lastEditTime;
	}

	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getEnableStatus() {
		return enableStatus;
	}

	public void setEnableStatus(Integer enableStatus) {
		this.enableStatus = enableStatus;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	
	
	
	
}
 