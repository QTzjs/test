package com.happy.util;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Flags.Flag;

import com.sun.org.apache.xpath.internal.operations.And;

public class RedPackUtil {
	// 对于微信红包，我们知道每人随机的最小红包是1分，最大金额是200元，这里我们同样来设置红包的范围，下面代码我们统一金钱的单位为分。
	// 最小红包额度
	private static final double MINMONEY = 1;
	// 最大红包额度
	private static final double MAXMONEY = 200 * 100; // 一元钱=100分钱
	// 每个红包最大是平均值的倍数
	private static final double TIMES = 2.1;
	//private static int Flag = 0;
	/**
	 * 单独的一元场
	 * @Description: 拆分红包
	 */
	public static List<Double> splitRedPacketsOne(double money, double count) {
		money = money * 100;
		if (!isRight(money, count, 100)) {
			return null;
		}
		List<Double> list = new ArrayList<Double>();
		// 红包最大金额为平均金额的TIMES倍
//		double max = (double) ((money * TIMES) / count);
		double max = 100.00;
		max = max > MAXMONEY ? MAXMONEY : max;
		//System.out.println("max1:" + max);
		for (double i = 0; i < count; i++) {
			double one = random(money, MINMONEY, max, count - i);
			list.add(one/100);
			//if (one>=100) System.out.println(String.valueOf(one));
			money -= one;
		}
		return list;
	}

	/**
	 * @param money
	 * @param count
	 * @return
	 * @Description: 拆分红包
	 */
	 public static List<Double> splitRedPackets(double maxmoney, double money, double count) {
		money = money * 100;
		// 红包最大金额为平均金额的TIMES倍
//		 double max = (double) (money * TIMES / count);
		 double max = maxmoney*100;
		 //System.out.println("maxmoney:"+maxmoney);
//		 double max = 20.00;
//		double max = 20.00;
		max = max > MAXMONEY ? MAXMONEY : max;
		//System.out.println("max1:" + max);
		if (!isRight(money, count, max)) {
			return null;
		}
		List<Double> list = new ArrayList<Double>();
		for (double i = 0; i < count; i++) {
			double one = random(money, MINMONEY, max, count - i);
			list.add(one/100);			
/*			if (one>2000) {
				System.out.println("1111111111******"+String.valueOf(one));	
				Flag = 1;
			}
*/			money -= one;
		}
		//if (Flag==1) System.out.println("List"+list);	
		return list;
	}

	/**
	 * @param money
	 * @param minS
	 * @param maxS
	 * @param count
	 * @return
	 * @Author:lulei
	 * @Description: 随机红包额度
	 */
	public static double random(double money, double minS, double maxS,	double count) {	//单位：分；minS、maxS单位也是分
		// 红包数量为1，直接返回金额
		if (count == 1) {			
			return money;
		}
		// 如果最大金额和最小金额相等，直接返回金额
		if (minS == maxS) {
			return minS;
		}
		double max = maxS > money ? money : maxS;
		// 随机产生一个红包
		double one = ((double) Math.rint(Math.random() * (max - minS) + minS))
				% max + 1;
		//if (one>=2000) System.out.println(String.valueOf(one));
		double money1 = money - one;
		//System.out.println("money1:" + money1);
		// 判断该种分配方案是否正确
		if (isRight(money1, count - 1, max)) {
			//if (one>2000) System.out.println("2222222222******"+String.valueOf(one));
			return one;
		} else {
			double avg = money1 / (count - 1);
			if (avg < MINMONEY) {
				// 递归调用，修改红包最大金额
				return random(money, minS, one, count);
			//} else if (avg > MAXMONEY) {		//龚伟写的
			} else if (avg > max) {
				// 递归调用，修改红包最小金额
				return random(money, one, maxS, count);
			}
		}
		//System.out.println("one:" + one);
		//if (one>=2000) System.out.println("3333333333******"+String.valueOf(one));
		return one;
	}

	/**
	 * @param money
	 * @param count
	 * @return
	 * @Author:lulei
	 * @Description: 此种红包是否合法
	 */
	public static boolean isRight(double money, double count, double max) {
		double avg = money / count;
		if (avg < MINMONEY) {
			return false;
		}
		//if (avg > MAXMONEY) {		//龚伟写的，会出现最后一个随机数大于红包场规定金额的错误
		if (avg > max) {	
			return false;
		}
		return true;
	}

	// public static void main(String[] args) {
	// // TODO Auto-generated method stub
	// RedPackUtil util = new RedPackUtil();
	// List<Double> moneylist = util.splitRedPackets(200, 6);
	// for (int i = 0; i < moneylist.size(); i++) {
	// double money = moneylist.get(i)/100;
	// //System.out.println("money:"+money);
	// }
	// // //System.out.println(util.splitRedPackets(100, 3));
	// }

	public static void main(String[] args) {
//		List list = splitRedPackets(20, 100, 15);
/*		List<Double> lists;
		for (int i=1; i<=10000; i++) {
			//lists = splitRedPacketsOne(1, 3);
			Flag = 0 ;
			splitRedPackets(20, 100, 15);
			if (Flag==1) System.out.println("index:" + String.valueOf(i));
		}
		System.out.println("ok");
*/	}
}
