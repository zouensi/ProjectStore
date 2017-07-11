package com.zouensi.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
/**
 * 
 * @author zouensi
 * @date 2017年7月9日
 * 描述:Redis工具类
 */
public class RedisUtils {
	private RedisUtils() {
		
	}
	private static JedisPoolConfig poolConfig = new JedisPoolConfig();
	private static JedisPool pool = new JedisPool(poolConfig, "123.206.200.250", 6379);
	private static final ThreadLocal<Jedis> local = new ThreadLocal<Jedis>();
	
	/**
	 * 获取jedis
	 * @return
	 */
	public static Jedis getJedis() {
		Jedis jedis = local.get();
		if(jedis==null) {
			jedis = pool.getResource();
			local.set(jedis);
		}
		return jedis;
	}
	
	/**
	 * 回收jedis
	 */
	public static void close() {
		Jedis jedis = local.get();
		jedis.close();
		local.remove();
	}
}	
