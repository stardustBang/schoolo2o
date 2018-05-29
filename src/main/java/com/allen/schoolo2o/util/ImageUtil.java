package com.allen.schoolo2o.util; 
/** 
* @author 作者 : Allen
* @version 创建时间：2018年5月23日 上午8:41:46 
*/

import java.io.File;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;



import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageUtil {
	
	private static String basePath=Thread.currentThread().getContextClassLoader().getResource("").getPath();
	
	private static final SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
	
	private static final Random r=new Random();
	
	public static String generateThumbnail(InputStream thumbnail,String targetAddr,String fileName) {
		String realFileName=getRandomFileName();
		String extension=getFileExtension(fileName);
		makeDirPath(targetAddr);
		String relativeAddr=targetAddr+realFileName+extension;
		File dest=new File(PathUtil.getImgBasePath()+relativeAddr);
		try {
			Thumbnails.of(thumbnail).size(200,200)
			.watermark(Positions.BOTTOM_LEFT,ImageIO.read(new File(basePath+"waterpage.png")),0.25f)
			.outputQuality(0.8f).toFile(dest);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return relativeAddr;
	}

	
    //创建目标路径所涉及到的目录，即/home/work/image/xxx.jpg
	//那么 home wor image三个文件夹都得自动创建
	private static void makeDirPath(String targetAddr) {
		String realFileParentPath = PathUtil.getImgBasePath()+targetAddr;
		File dirPath=new File(realFileParentPath);
		if(!dirPath.exists()) {
			dirPath.mkdirs();
		}
		
	}

	//获取输入文件流的扩展名
	private static String getFileExtension(String fileName) {
		
		return fileName.substring(fileName.lastIndexOf("."));
	}

	
	

	//生成随机文件名，当前年月日小时分钟秒+五位随机数
	private static String getRandomFileName() {
		//获取随机的五位数
		int rannum=r.nextInt(89999)+10000;
		String nowTimeString=sDateFormat.format(new Date());
	    
		return nowTimeString+rannum;
	}
	
	
	public static void deleteFileOrPath(String storePath) {
		File fileOrPath=new File(PathUtil.getImgBasePath()+storePath);
		if(fileOrPath.exists()) {
			File files[] =fileOrPath.listFiles();
			for(int i=0;i< files.length;i++) {
				files[0].delete();
			}
			fileOrPath.delete();
		}
		
		
	}
	
	
	/*
	public static void main(String[] args) throws IOException {

		String basePath=Thread.currentThread().getContextClassLoader().getResource("").getPath();
		
	Thumbnails.of(new File("H:/image/ping.jpg")).size(200,200)
	.watermark(Positions.BOTTOM_RIGHT,ImageIO.read(new File(basePath+"/waterpage.png")),0.25f)
	.outputQuality(0.8f).toFile("H:/image/new/pingnew.jpg");
	
	
	}*/
}
 