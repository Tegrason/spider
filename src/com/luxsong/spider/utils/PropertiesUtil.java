package com.luxsong.spider.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	private static  Properties prop = new Properties();
	private final static String path = "spider.properties";
	static{
		InputStream inputStream =  null;
	try {
		   
		inputStream = new FileInputStream(path);
		prop.load(inputStream);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	finally{
		try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}

	public static String getProperty(String key){
		return prop.getProperty(key);
	}
	public static void setProper(String key,String value){
		
		FileOutputStream oFile =null;
		try {
			prop.setProperty(key, value);
			oFile = new FileOutputStream(path);
			prop.store(oFile, null);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(oFile!=null)
				oFile.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}

		
		
}
