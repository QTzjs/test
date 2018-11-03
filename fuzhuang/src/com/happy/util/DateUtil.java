package com.happy.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String formatDate(Date date, String format) {
		String result = "";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		if (date != null) {
			result = sdf.format(date);
		}
		return result;
	}

	public static Date formatString(String str, String format) throws Exception {
		if (StringUtil.isEmpty(str)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(str);
	}

	public static String stringFormatString(String str, String format)
			throws Exception {
		if (StringUtil.isEmpty(str)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String strs = formatDate(sdf.parse(str), format);
		return strs;
	}

	public static String getCurrentDateStr() throws Exception {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		return sdf.format(date);
	}

	public static String getCurrentDateYMDStr() throws Exception {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return sdf.format(date);
	}

	public static String getCurrentDateStr2() throws Exception {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	public static void main(String[] args) throws ParseException {
		//System.out.println(DateUtil.stringToStamp("2009-07-16"));

	}

	/**
	 * 把时间转换为时间戳
	 * 
	 * @param value
	 * @return
	 */
	public static int stringToStamp(String str) {
		int time = 0;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Long t = date.getTime();
		String times = String.valueOf(t).length() < 11 ? String.valueOf(t)
				: String.valueOf(t).substring(0, 10);
		time = Integer.valueOf(times);
		return time;
	}

	/**
	 * 把时间戳转换为时间
	 * 
	 * @param value
	 * @return
	 */
	public static String stampToString(long value) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Long time = new Long(value);
		String times = format.format(time);
		return times;
	}

	/**
	 * 把服务器时间转字符串，做图片路径使用
	 * 
	 * @param template
	 *            转换格式
	 * 
	 * @return 时间字符串
	 */
	public static String getServerTime(String template) {
		return new SimpleDateFormat(template).format(new Date());
	}
	
}
