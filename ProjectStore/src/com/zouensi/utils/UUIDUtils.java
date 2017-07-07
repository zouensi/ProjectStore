package com.zouensi.utils;

import java.util.UUID;

public class UUIDUtils {
	/**
	 * ªÒ»°UUID
	 * @return
	 */
	public static String getUUID() {
		 String string = UUID.randomUUID().toString();
		 return string.replace("-", "");
	}
}
