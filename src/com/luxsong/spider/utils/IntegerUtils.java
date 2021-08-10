package com.luxsong.spider.utils;

import java.util.List;
import java.util.stream.Collectors;

public class IntegerUtils {
	
	public static int stringToInt(String str){
		int result = 0;
		if(null!=str&&str.length()!=0){
			try{
				result = Integer.valueOf(str).intValue();
			}catch(NumberFormatException e){
				e.printStackTrace();
			}
		}
		return result;
	}

	public static String charsetProcessor(String titleParam,int range){
		List<Character> charactersList = titleParam.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
		int length = charactersList.size();
		String result = titleParam;
		if(length>range){
			result = "";
			int i =0;
			while (i<range){
				int random = obtainRandom(length-1);
				result += charactersList.get(random).toString();
				charactersList.remove(random);
				length = charactersList.size();
				i++;
			}
		}
		return result;
	}
	public static int obtainRandom(int range){
		if(range<1){
			return 0;
		}
		int random = (int)((Math.random()*range+1));
		return random;
	}
}
