package com.happy.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.happy.redis.RedisUtil.RedisHash;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;

/**
 * Redis连接池
 * 
 * @author AK
 * 
 */
public final class RedisUtil {
	// Redis服务器IP
	private static String IP = "127.0.0.1";
	// Redis的端口号
	private static int PORT = 6379;
	// 访问密码
	private static String AUTH = "Ytw-2015#Ehsan";
	// 可用连接实例的最大数目，默认值为8；
	// 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
	private static int MAX_ACTIVE = 1024;
	// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
	private static int MAX_IDLE = 200;
	// 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
	private static long MAX_WAIT = 10000;
	// 最大延迟时间
	private static int TIMEOUT = 10000;
	// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
	private static boolean TEST_ON_BORROW = true;

	private static JedisPool jedisPool = null;

	/**
	 * 初始化Redis连接池
	 */
	static {
		try {
			JedisPoolConfig config = new JedisPoolConfig();
			// 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
			// 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
			config.setMaxActive(500);
			// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
			config.setMinIdle(2);
			config.setMaxIdle(10);
			// 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
			config.setMaxWait(1000 * 10);
			// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
			config.setTestOnBorrow(true);
			jedisPool = new JedisPool(config, IP, PORT);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取Jedis实例
	 * 
	 * @return
	 */
	public synchronized static Jedis getJedis() {
		try {
			if (jedisPool != null) {
				Jedis resource = jedisPool.getResource();
				return resource;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 释放jedis资源
	 * 
	 * @param jedis
	 */
	public static void returnResource(final Jedis jedis) {
		if (jedis != null) {
			jedisPool.returnResource(jedis);
		}
	}

	/**
	 * 设置数据
	 * 
	 * @param key
	 * @return
	 */
	public static String set(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.set(key, value);
		} catch (Exception e) {
			// 释放redis对象
			jedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			// 返还到连接池
			returnResource(jedis);
		}

		return value;
	}
	
	public static String setBySeconds(String key, int seconds, String value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.setex(key, seconds, value);
		} catch (Exception e) {
			// 释放redis对象
			jedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			// 返还到连接池
			returnResource(jedis);
		}

		return value;
	}

	/**
	 * 删除数据
	 * 
	 * @param key
	 * @return
	 */
	public boolean del(String key) {
		Jedis jedis = null;

		try {
			jedis = jedisPool.getResource();
			return jedis.del(key) > 0 ? true : false;
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			returnResource(jedis);
		}

		return false;
	}

	public String get(String key) {
		Jedis jedis = null;

		try {
			jedis = jedisPool.getResource();

			return jedis.get(key);
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			returnResource(jedis);
		}

		return null;
	}

	/**
	 * 存储到redis队列中，插入到表头
	 * 
	 * @param key
	 * @param value
	 */
	public void lpush(byte[] key, byte[] value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.lpush(key, value);
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			returnResource(jedis);
		}
	}

	/**
	 * 存储到redis队列中，插入到表尾
	 * 
	 * @param key
	 * @param value
	 */
	public void rpush(byte[] key, byte[] value) {
		Jedis jedis = null;

		try {
			jedis = jedisPool.getResource();
			jedis.rpush(key, value);
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			returnResource(jedis);
		}
	}

	public byte[] lpop(byte[] key) {

		Jedis jedis = null;

		try {
			jedis = jedisPool.getResource();
			return jedis.lpop(key);
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			returnResource(jedis);
		}

		return null;
	}

	public byte[] rpop(byte[] key) {

		Jedis jedis = null;

		try {
			jedis = jedisPool.getResource();
			return jedis.rpop(key);
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			returnResource(jedis);
		}

		return null;
	}

	public Long getLen(byte[] key) {
		Jedis jedis = null;

		try {
			jedis = jedisPool.getResource();
			return jedis.llen(key);
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			returnResource(jedis);
		}

		return 0L;
	}

	public List<byte[]> getRedisList(byte[] key) {
		Jedis jedis = null;

		try {
			jedis = jedisPool.getResource();
			return jedis.lrange(key, 0, -1);
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			returnResource(jedis);
		}

		return null;
	}

	public boolean isExistUpdate(final String... param) {

		Jedis jedis = null;

		try {

			jedis = jedisPool.getResource();

			String key = param[0];
			int expire = 20;
			if (param.length > 1) {
				expire = Integer.parseInt(param[1]);
			}
			long status = jedis.setnx("redis_lock_" + key, "true");
			// if (status > 0) {
			jedis.expire("redis_lock_" + key, expire);
			// }

			return status <= 0 ? true : false;
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			returnResource(jedis);
		}

		return false;

	}

	public static boolean acquireLock(String lock, String expired) {
		// 1. 通过SETNX试图获取一个lock
		boolean success = false;
		Jedis jedis = null;
		try {

			jedis = jedisPool.getResource();

			long acquired = jedis.setnx(lock, String.valueOf(expired));
			// SETNX成功，则成功获取一个锁
			if (acquired == 1) {
				success = true;
			}
			// SETNX失败，说明锁仍然被其他对象保持，检查其是否已经超时
			else {
				success = false;
			}
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			returnResource(jedis);
		}
		return success;
	}

	public Long unLockRedisKey(final String key) {

		Jedis jedis = null;

		try {
			jedis = jedisPool.getResource();
			return jedis.del("redis_lock_" + key);
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			returnResource(jedis);
		}

		return -1L;
	}

	/**
	 * Redis 哈希
	 */
	public class RedisHash {

		private String key;

		public RedisHash(String key) {
			this.key = key;
		}

		/**
		 * 获取指定属性值
		 * 
		 * @param field
		 *            属性名
		 * 
		 * @return 属性值
		 */
		public String get(final String field) {

			Jedis jedis = null;

			try {
				jedis = jedisPool.getResource();
				return jedis.hget(key, field);
			} catch (Throwable e) {
				e.printStackTrace();
			} finally {
				returnResource(jedis);
			}

			return null;

		}

		/**
		 * 获取指定属性值
		 * 
		 * @param fields
		 *            属性名
		 * 
		 * @return 属性值
		 */
		public List<String> get(final String... fields) {

			Jedis jedis = null;

			try {
				jedis = jedisPool.getResource();
				return jedis.hmget(key, fields);
			} catch (Throwable e) {
				e.printStackTrace();
			} finally {
				returnResource(jedis);
			}

			return null;
		}

		/**
		 * 设置属性
		 * 
		 * @param field
		 *            属性名
		 * @param value
		 *            属性值
		 */
		public void put(final String field, final String value) {

			Jedis jedis = null;

			try {
				jedis = jedisPool.getResource();
				jedis.hset(key, field, value);
			} catch (Throwable e) {
				e.printStackTrace();
			} finally {
				returnResource(jedis);
			}
		}

		/**
		 * 仅当属性名不存在是设置属性
		 * 
		 * @param field
		 *            属性名
		 * @param value
		 *            属性值
		 * 
		 * @return 0表示属性已存在
		 */
		public int setOnlyIfNotExists(final String field, final String value) {

			Jedis jedis = null;

			try {
				jedis = jedisPool.getResource();
				return jedis.hsetnx(key, field, value).intValue();
			} catch (Throwable e) {
				e.printStackTrace();
			} finally {
				returnResource(jedis);
			}

			return -1;
		}

		/**
		 * 保存多个属性名和属性值
		 * 
		 * @param map
		 *            属性
		 */
		public void putAll(final Map<String, String> map) {

			Jedis jedis = null;

			try {
				jedis = jedisPool.getResource();
				jedis.hmset(key, map);
			} catch (Throwable e) {
				e.printStackTrace();
			} finally {
				returnResource(jedis);
			}
		}

		/**
		 * 删除一个或多个属性
		 * 
		 * @param fields
		 *            属性名
		 * 
		 * @return 被删除的属性数量
		 */
		public int delete(final String... fields) {

			Jedis jedis = null;

			try {
				jedis = jedisPool.getResource();
				return jedis.hdel(key, fields).intValue();
			} catch (Throwable e) {
				e.printStackTrace();
			} finally {
				returnResource(jedis);
			}

			return -1;
		}

		/**
		 * 列出所有属性
		 * 
		 * @return 所有属性名
		 */
		public List<String> keys() {

			Jedis jedis = null;

			try {
				jedis = jedisPool.getResource();
				return new ArrayList<String>(jedis.hkeys(key));
			} catch (Throwable e) {
				e.printStackTrace();
			} finally {
				returnResource(jedis);
			}

			return null;
		}

		/**
		 * 读取所有属性值并转换为 Map 对象
		 * 
		 * @return 所有属性值
		 */
		public Map<String, String> toMap() {

			Jedis jedis = null;

			try {
				jedis = jedisPool.getResource();
				return jedis.hgetAll(key);
			} catch (Throwable e) {
				e.printStackTrace();
			} finally {
				returnResource(jedis);
			}

			return null;
		}

		/**
		 * 读取key的长度
		 * 
		 * @return 所有属性值
		 */
		public Long getLen() {
			Jedis jedis = null;

			try {
				jedis = jedisPool.getResource();
				return jedis.hlen(key);
			} catch (Throwable e) {
				e.printStackTrace();
			} finally {
				returnResource(jedis);
			}

			return 0L;
		}

		/**
		 * 是否存在一个key
		 * 
		 * @return 所有属性值
		 */
		public Boolean isExist(final String field) {

			Jedis jedis = null;

			try {
				jedis = jedisPool.getResource();
				return jedis.hexists(key, field);
			} catch (Throwable e) {
				e.printStackTrace();
			} finally {
				returnResource(jedis);
			}

			return false;
		}

	}

	public RedisHash getRedisHash(String key) {
		return new RedisHash(key);
	}

	public static void main(String[] args) {
		//System.out.println(getJedis().flushAll());
	}

}