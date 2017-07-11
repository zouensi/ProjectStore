package com.zouensi.utils;

/**
 * 
 * @author zouensi
 * @date 2017年7月9日
 * 描述:公共工具类
 */
public class Utils {
	private Utils() {
		
	}
	/**
	 * 判断字符串是否为null
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if(str==null||"".equals(str.trim())) {
			return true;
		}
		return false;
	}
}
