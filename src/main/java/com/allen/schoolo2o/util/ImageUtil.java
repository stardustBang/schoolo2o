package com.allen.schoolo2o.util; 
/** 
* @author 作者 : Allen
* @version 创建时间：2018年5月23日 上午8:41:46 
*/

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageUtil {
	
	private static String basePath=Thread.currentThread().getContextClassLoader().getResource("").getPath();
	
	private static final SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
	
	private static final Random r=new Random();
	
	public static String generateThumbnail(CommonsMultipartFile thumbnail,String targetAddr) {
		String realFileName=getRandomFileName();
		String extension=getFileExtension(thumbnail);
		makeDirPath(targetAddr);
		String relativeAddr=targetAddr+realFileName+extension;
		File dest=new File(PathUtil.getImgBasePath()+relativeAddr);
		try {
			Thumbnails.of(thumbnail.getInputStream()).size(200,200)
			.watermark(Positions.BOTTOM_RIGHT,ImageIO.read(new File(basePath+"waterpage.png")),0.25f)
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
	private static String getFileExtension(CommonsMultipartFile thumbnail) {
		String originalFileName=thumbnail.getOriginalFilename();
		
		return originalFileName.substring(originalFileName.lastIndexOf("."));
	}

	
	

	//生成随机文件名，当前年月日小时分钟秒+五位随机数
	private static String getRandomFileName() {
		//获取随机的五位数
		int rannum=r.nextInt(89999)+10000;
		String nowTimeString=sDateFormat.format(new Date());
	    
		return nowTimeString+rannum;
	}
	
	
	
	public static void main(String[] args) throws IOException {

		String basePath=Thread.currentThread().getContextClassLoader().getResource("").getPath();
		
	Thumbnails.of(new File("H:/image/ping.jpg")).size(200,200)
	.watermark(Positions.BOTTOM_RIGHT,ImageIO.read(new File(basePath+"/waterpage.png")),0.25f)
	.outputQuality(0.8f).toFile("H:/image/new/pingnew.jpg");
	
	
	}
}
 