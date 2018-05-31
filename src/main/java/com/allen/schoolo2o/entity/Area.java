package com.allen.schoolo2o.entity;
/** 
* @author ���� : Allen
* @version ����ʱ�䣺2018��5��5�� ����7:09:48 
*/

import java.util.Date;

public class Area {
	
	private Long areaId;

	private String areaName;

	//权重
	private Integer priority;

	private Date createTime;

	private Date lastEditTime;

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
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

}
