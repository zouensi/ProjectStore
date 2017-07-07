package com.zouensi.threadhandler;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 * 
 * @author zouensi
 * @date 2017��7��7��
 * ����:����
 */
public class EmaliThreadPool {
	private static final int CORE_POOL_SIZE = 100;
	private static final int MAXIMUM_POOL_SIZE = 100;
	private static final long KEEP_ALIVE_TIME = 0;
	private static final TimeUnit UNIT = TimeUnit.MILLISECONDS;
	private static final LinkedBlockingQueue<Runnable> WORKQUEUE = new LinkedBlockingQueue();
	
	private static final ThreadPoolExecutor pool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, UNIT, WORKQUEUE);
	private EmaliThreadPool() {
		
	}
	/**
	 * Ϊ�����ṩ����
	 * @return
	 */
	public static ThreadPoolExecutor getPool() {
		return pool;
	}
}
