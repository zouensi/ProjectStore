package com.zouensi.utils;

import org.apache.commons.dbutils.QueryRunner;

public class QueryRunnerUtils {
	private QueryRunnerUtils() {
		
	}
	private static QueryRunner qrWithDs = new QueryRunner(DataSourceUtils.getDs());
	private static QueryRunner qr = new QueryRunner(DataSourceUtils.getDs());
	/**
	 * ��ȡ�������ӳص�queryrunner
	 * @return
	 */
	public static QueryRunner getQueryRunnerWithDs() {
		return qrWithDs;
	}
	/**
	 * ��ȡ�������ӳص�queryrunner
	 * @return
	 */
	public static QueryRunner getQueryRunner() {
		return qr;
	}
	
}
