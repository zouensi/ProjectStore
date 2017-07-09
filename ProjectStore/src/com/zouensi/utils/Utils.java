package com.zouensi.utils;

public class Utils {
	private Utils() {
		
	}
	
	public static boolean isEmpty(String str) {
		if(str==null||"".equals(str.trim())) {
			return true;
		}
		return false;
	}
}
