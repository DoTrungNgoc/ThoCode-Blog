package com.thocode.blog.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class Helper {
	
	public static boolean deleteFile(String path) {
		boolean rs = false;
		try {
			
			File file = new File(path);
			rs = file.delete();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public static boolean saveFile(InputStream is, String path) {
		boolean rs = false;
		
		try {
			byte b[] = new byte[is.available()];
			
			is.read(b);
			
			FileOutputStream fos = new FileOutputStream(path);
			
			fos.write(b);
			
			fos.flush();
			fos.close();
			rs = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
}
