package com.allen.schoolo2o.util; 
/** 
* @author 作者 : Allen
* @version 创建时间：2018年5月23日 上午10:37:21 
*/
public class PathUtil {
	
	private static String seperator=System.getProperty("file.separator");
	
	public static String getImgBasePath() {
		String os=System.getProperty("os.name");
		String basePath="";
		if(os.toLowerCase().startsWith("win")) {
			basePath="H:/image/";
		}else {
			basePath ="/home/o2o/image/";
		}
		basePath=basePath.replace("/", seperator);
		
		return basePath;
	}
	
	public static String getShopImagePath(long shopId) {
		
		String imagePath="upload/item/shop/"+shopId+"/";
		return imagePath.replace("/", seperator);
	}

}
 