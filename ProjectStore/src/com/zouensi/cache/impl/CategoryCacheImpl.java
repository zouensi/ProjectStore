package com.zouensi.cache.impl;


import redis.clients.jedis.Jedis;

import com.zouensi.cache.CategoryCache;
import com.zouensi.utils.RedisUtils;

public class CategoryCacheImpl implements CategoryCache {
	/**
	 * 从缓存中获取类型
	 */
	@Override
	public String getCategory() {
		String info = "";
		try(Jedis jedis = RedisUtils.getJedis();) {
			info = jedis.get("categorys");
		}catch (Exception e) {
		}
		
		return info;
	}
	
	/**
	 * 设置category信息数据
	 */
	@Override
	public void setCategory(String info) {
		try(Jedis jedis = RedisUtils.getJedis();) {
			jedis.set("categorys",info);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
