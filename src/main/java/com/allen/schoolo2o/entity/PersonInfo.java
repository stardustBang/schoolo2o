package com.allen.schoolo2o.entity;

import java.util.Date;

/** 
* @author ���� : Allen
* @version ����ʱ�䣺2018��5��5�� ����7:22:39 
*/
public class PersonInfo {
	
	private Long userId;
	 
	private String name;
	
	private String email;
	
	private String gender;
	
	//״̬
	//0:不可用   1：可用商城
	private Integer enableStatus;
	
	
	private String profileImg;
    
	
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

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
 