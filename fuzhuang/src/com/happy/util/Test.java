package com.happy.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

import org.apache.commons.lang.time.StopWatch;

import com.happy.redis.Msg;
import com.happy.redis.ObjectUtil;
import com.happy.redis.RedPacketAlgorithm;
import com.happy.redis.RedisUtil;

public class Test {
	protected final static Logger logger = Logger.getLogger("executeLog");

	public void test() {

		final RedisUtil redisUtil = new RedisUtil();

		long userId = 45243043L;

		String hongbaoId = "22";
		final String user_id = "113";
		final String insertkey = hongbaoId+"---"+user_id;
		// 将大红包拆分成小红包数组
//		long[] rpDatas = RedPacketAlgorithm.generate(total, count, max, min);

		// 将生成的红包放入队列中
		final String key = "red_packet_original_queue9_" + hongbaoId;
		String redisbonus_id=hongbaoId+"bonus_id";
		if(redisUtil.get(redisbonus_id)==null||redisUtil.get(redisbonus_id).equals("")){
			//System.out.println("come");
			for (int i = 0; i < 3; i++) {
				Msg msg = new Msg();
				msg.setId(i + 1);
//				msg.setMoney(9);
				msg.setSourceUserId(userId);
				// logger.info("key : " + key + ", msg : " + msg);
				try {
					redisUtil.lpush(key.getBytes(), ObjectUtil.objectToBytes(msg));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			redisUtil.set(redisbonus_id, hongbaoId);
		}
		
		StopWatch watch = new StopWatch();

		logger.info("start:" + System.currentTimeMillis() / 1000);

		// 已经领取用户列表
//		final String mapKey = "map9_" + key;

		// 最后生成的领取记录队列
//		final String finshKey = "finish9_" + key;
		/**
		 * 接收到一个获取红包请求 判断是否已经领取过 如果没有，则从redis队列中取出一个分给该用户 添加该用户的领取记录
		 * 如果没有，则返回已经领取完 将完成队列处理入库
		 */
		// 模拟300人同时不同的抢红包
		int threadNum = 3;
		final CountDownLatch latch = new CountDownLatch(threadNum);
		watch.start();
		for (int i = 0; i < 3; ++i) {
//			final int temp = i;
			if(redisUtil.getLen(key.getBytes())==0){
				//System.out.println(insertkey+"红包已经抢完了");
				break;
			}else{
				//
				
				if(redisUtil.getLen(insertkey.getBytes())!=0){
					redisUtil.lpop(key.getBytes());
					//System.out.println("key.getBytes()size:" + redisUtil.getLen(key.getBytes()));
					//System.out.println(insertkey+"你已经抢过了");
					break;
				}else{
					
					Msg msg = new Msg();
					msg.setId(Integer.valueOf(user_id));
//					msg.setMoney(5);
					msg.setSourceUserId(Integer.valueOf(user_id));
					// logger.info("key : " + key + ", msg : " + msg);
					try {
						redisUtil.lpush(hongbaoId.getBytes(), ObjectUtil.objectToBytes(msg));
						redisUtil.lpush(insertkey.getBytes(), ObjectUtil.objectToBytes(msg));
						//System.out.println("insertkey.getBytes()size:" + redisUtil.getLen(insertkey.getBytes()));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}
		// }
		
		try {
			List<byte[]> msgs = redisUtil.getRedisList(hongbaoId.getBytes());

			for (byte[] msg : msgs) {
				Msg msgsMsg = null;
				try {
					msgsMsg = (Msg) ObjectUtil.bytesToObject(msg);
					//System.out.println("成功领取的---"+ String.valueOf(msgsMsg.getId()));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// logger.info(String.valueOf(msgsMsg.getGetUserId()));
			}
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		//System.out.println("insertkey.getBytes()size:" + redisUtil.getLen(insertkey.getBytes()));
		// logger.info(redisUtil.getRedisList(key.getBytes()));
		// logger.info(redisUtil.getRedisList(finshKey.getBytes()));

		
		watch.stop();
		// logger.info("time:" + String.valueOf(watch.getStartTime()));
		// logger.info("speed:" + total/watch.getTime());
		// logger.info("end:" + System.currentTimeMillis()/1000);

	}

	public static void main(String[] args) {
//		Test t = new Test();
//		t.test();
		double return_price = 158.72000000000003;
		BigDecimal bg = new BigDecimal(return_price);  
        double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();  
        //System.out.println(f1);  
	}

}
