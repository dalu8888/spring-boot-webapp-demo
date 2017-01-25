package com.digitalchina.common.utils;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * 获取图片类型
 *
 */
public class ImageTypeUtil {

	public static final String JPEG = "jpeg";
	public static final String BMP = "bmp";
	public static final String PNG = "png";

	/**
     * 判断是否是图片类型
	 *
	 * JPG、PNG、JPEG、BMP
	 *
	 * @param file
     * @return
     */
	public static boolean isImage(File file){
		return isImage(getImageType(file));
	}

	/**
     * 判断是否是图片类型
	 *
	 * JPG、PNG、JPEG、BMP
	 *
	 * @param in
     * @return
     */
	public static boolean isImage(InputStream in){
		return isImage(getImageType(in));
	}

	private static boolean isImage(String imageType){
		return JPEG.equals(imageType) ||
				BMP.equals(imageType) ||
				PNG.equals(imageType);
	}

	/**
	 * 获取文件类型
	 * 
	 * @param file
	 * @return
	 */
	public static String getImageType(File file) {
		try{
			return getImageType(ImageIO.createImageInputStream(file));
		} catch (Throwable e){
			return null;
		}
	}
	
	/**
	 * 获取文件类型
	 * 
	 * @param in
	 * @return
	 */
	public static String getImageType(InputStream in) {
		try{
			return getImageType(ImageIO.createImageInputStream(in));
		} catch (Throwable e){
			return null;
		}
	}

	private static String getImageType(ImageInputStream iis) throws IOException {
		Iterator<ImageReader> imageReaders = ImageIO.getImageReaders(iis);
		while (imageReaders.hasNext()) {
			ImageReader reader = imageReaders.next();
			return reader.getFormatName().toLowerCase();
		}
		return null;
	}

}
