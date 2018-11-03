package com.happy.util;

import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;

/**
* Created by Song on 2016/11/8.
* mail: 1147649695@qq.com
* 微信支付相关工具类
*/
public class PayWxUtil {
   //上一次订单请求日期
   private static Date preDay = new Date();
   //当前订单日期
   private static Date curDay = new Date();
   //用于记录已产生的订单号
   private static Set<Long> numPoul = new HashSet<Long>();
   /**
    * 获得签名
    * @param params 待编码参数，参数值为空不传入
    * @param key key设置路径：微信商户平台(pay.weixin.qq.com)-->账户设置-->API安全-->密钥设置
    * @return
    */
   public static String getSign(Map<String,String> params,String key) throws Exception{
       List<String> list = new ArrayList<String>(params.keySet());
       Collections.sort(list,new DictionaryCompare());
       StringBuffer sb = new StringBuffer();
       for(String keyVal:list){
    	   if(params.get(keyVal)!=null){
           sb.append(keyVal+"="+params.get(keyVal)+"&");
    	   }
       }
       sb.append("key="+key);
       return DigestUtils.md5Hex(new String(sb.toString().getBytes(),"utf-8")).toUpperCase();
   }

   /**
    * 获得随机字符串
    * @return
    */
   public static String getNonceStr(){
       Random random = new Random();
       long val = random.nextLong();
       String res = DigestUtils.md5Hex(val+"yzx").toUpperCase();
       if(32<res.length()) return res.substring(0,32);
       else return res;
   }

   /**
    * 获取订单号
    * 商户订单号（每个订单号必须唯一）
    * 组成：mch_id+yyyymmdd+10位一天内不能重复的数字。
    * @param mchId
    * @return
    */
   public static String getMchBillno(String mchId){
       Random random = new Random();
       long val = random.nextLong()%10000000000L;//获得0-9999999999内的数字
       curDay = new Date();
       //隔天清空
       if(curDay.after(preDay)) numPoul.clear();
       while(numPoul.contains(val)){
           val = random.nextLong()%10000000000L;
       }
       numPoul.add(val);
       preDay = curDay;
       //按要求，日期格式化输出
       DateFormat df = new SimpleDateFormat("yyyymmdd");
       return mchId+df.format(curDay)+format(val+"",10);
   }

   /**
    * 将字符串str按长度在前面添0补齐
    * @param str
    * @param length
    * @return
    */
   private static String format(String str,int length){
       String pre = "0000000000";
       int len = str.length();
       if(10<=len) return str.substring(0,10);
       else return pre.substring(0,10-len).concat(str);
   }
   
   //SHA1加密方法，jssdk签名算法
   public static String getSha1(String str){
       if(str==null||str.length()==0){
           return null;
       }
       char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9',
               'a','b','c','d','e','f'};
       try {
           MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
           mdTemp.update(str.getBytes("UTF-8"));

           byte[] md = mdTemp.digest();
           int j = md.length;
           char buf[] = new char[j*2];
           int k = 0;
           for (int i = 0; i < j; i++) {
               byte byte0 = md[i];
               buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
               buf[k++] = hexDigits[byte0 & 0xf];      
           }
           return new String(buf);
       } catch (Exception e) {
           // TODO: handle exception
           return null;
       }
   }

}

/**
* 按字典序排序
*/
class DictionaryCompare implements Comparator<String>{
   public int compare(String o1, String o2) {
       return o1.compareTo(o2);
   }
}
