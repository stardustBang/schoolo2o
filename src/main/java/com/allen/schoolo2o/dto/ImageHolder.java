/**
 * 
 */ 
package com.allen.schoolo2o.dto;

import java.io.InputStream;

/** 
* @author 作者 : Allen
* @version 创建时间：2018年6月11日 下午7:35:13 
*/
public class ImageHolder {
	
	private String imageName;
	
	private InputStream image;

	public ImageHolder( InputStream image,String imageName) {
		
		this.imageName = imageName;
		this.image = image;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public InputStream getImage() {
		return image;
	}

	public void setImage(InputStream image) {
		this.image = image;
	}
	
	
	
	
	
	
	

}
 