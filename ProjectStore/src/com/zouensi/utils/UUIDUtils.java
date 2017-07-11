package com.zouensi.utils;

import java.util.UUID;
/**
 * 
 * @author zouensi
 * @date 2017年7月9日
 * 描述:获取主键工具类
 */
public class UUIDUtils {
	/**
	 * 获取UUID
	 * @return
	 */
	public static String getUUID() {
		 String string = UUID.randomUUID().toString();
		 return string.replace("-", "");
	}
}
