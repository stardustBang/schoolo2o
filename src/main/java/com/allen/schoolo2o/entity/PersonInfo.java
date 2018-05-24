package com.allen.schoolo2o.entity;

import java.util.Date;

/** 
* @author ���� : Allen
* @version ����ʱ�䣺2018��5��5�� ����7:22:39 
*/
public class PersonInfo {
	
	private Long userId;
	 
	private String username;
	
	private String email;
	
	private String gender;
	
	//״̬
	//0:��ֹʹ�ñ��̳�   1������ʹ�ñ��̳�
	private Integer enableStatus;
	
	//ͷ��
	private String profileImg;
    
	//�û�����ݱ�ʶ
	//1.�˿�   2.��� 3.��������Ա
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
 