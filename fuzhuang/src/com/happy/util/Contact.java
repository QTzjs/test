package com.happy.util;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.happy.model.weixin.User;

public class Contact {
	public static User loginUser=null;
	public static int secondCount=30;
	/**
	 * 用户提交工序队列
	 */
	public static BlockingQueue gongQueue = new ArrayBlockingQueue(1);

	

}
