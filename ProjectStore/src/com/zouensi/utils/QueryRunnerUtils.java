package com.zouensi.utils;

import org.apache.commons.dbutils.QueryRunner;
/**
 * 
 * @author zouensi
 * @date 2017年7月9日
 * 描述:QueryRunner工具类
 */
public class QueryRunnerUtils {
	private QueryRunnerUtils() {
		
	}
	private static QueryRunner qrWithDs = new QueryRunner(DataSourceUtils.getDs());
	private static QueryRunner qr = new QueryRunner(DataSourceUtils.getDs());
	/**
	 * 获取带有连接池的queryrunner
	 * @return
	 */
	public static QueryRunner getQueryRunnerWithDs() {
		return qrWithDs;
	}
	/**
	 * 获取不带连接池的queryrunner
	 * @return
	 */
	public static QueryRunner getQueryRunner() {
		return qr;
	}
	
}
