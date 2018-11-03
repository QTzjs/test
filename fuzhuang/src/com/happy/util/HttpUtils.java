package com.happy.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
/**
 * http post 提交 和 get请求
 * @author QT-666
 *
 */
public class HttpUtils {
 private static RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(15000).setConnectTimeout(15000)
   .setConnectionRequestTimeout(15000).build();

 public static void get(String url, Map<String, String> params){
	    CloseableHttpClient httpClient = null;
	    HttpGet httpGet = null;
	    try {
	        httpClient = HttpClients.createDefault();
	        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();
	        String ps = "";
	        for (String pKey : params.keySet()) {
	            if(!"".equals(ps)){
	                ps = ps + "&";
	            }
	            ps = pKey+"="+params.get(pKey);
	        }
	        if(!"".equals(ps)){
	            url = url + "?" + ps;
	        }
	        httpGet = new HttpGet(url);
	        httpGet.setConfig(requestConfig);
	        CloseableHttpResponse response = httpClient.execute(httpGet);
	        HttpEntity httpEntity = response.getEntity();
	        System.out.println(EntityUtils.toString(httpEntity,"utf-8"));
	    } catch (ClientProtocolException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }finally{
	        try {
	            if(httpGet!=null){
	                httpGet.releaseConnection();
	            }
	            if(httpClient!=null){
	                httpClient.close();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
 /** 
     * 发送 post请求 
     * @param httpUrl 地址 
     * @param maps 参数 
     */  
 public static void post(String url, Map<String, String> params){
	    CloseableHttpClient httpClient = null;
	    HttpPost httpPost = null;
	    try {
	        httpClient = HttpClients.createDefault();
	        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();
	        httpPost = new HttpPost(url);
	        httpPost.setConfig(requestConfig);
	        List<NameValuePair> ps = new ArrayList<NameValuePair>();
	        for (String pKey : params.keySet()) {
	            ps.add(new BasicNameValuePair(pKey, params.get(pKey)));
	        }
	        httpPost.setEntity(new UrlEncodedFormEntity(ps));
	        CloseableHttpResponse response = httpClient.execute(httpPost);
	        HttpEntity httpEntity = response.getEntity();
	        System.out.println(EntityUtils.toString(httpEntity,"utf-8"));
	    } catch (ClientProtocolException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }finally{
	        try {
	            if(httpPost!=null){
	                httpPost.releaseConnection();
	            }
	            if(httpClient!=null){
	                httpClient.close();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
 /** 
     * 发送post请求Https,参数是字符串 
     * @param httpPost 
     * @return 
     */  
 public static String post(String url, String body) throws Exception{
	    String str="";
	    CloseableHttpClient httpClient = null;
	    HttpPost httpPost = null;
	    try {
	        httpClient = HttpClients.createDefault();
	        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();
	        httpPost = new HttpPost(url);
	        httpPost.setConfig(requestConfig);
	        httpPost.setEntity(new StringEntity(body,"utf-8"));
	        CloseableHttpResponse response = httpClient.execute(httpPost);
	        HttpEntity httpEntity = response.getEntity();
	        str = EntityUtils.toString(httpEntity,"utf-8");
	       
	      
	  
	    } catch (ClientProtocolException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }finally{
	        try {
	            if(httpPost!=null){
	                httpPost.releaseConnection();
	            }
	            if(httpClient!=null){
	                httpClient.close();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    return new String(str.getBytes("iso-8859-1"));
	}
 public static String get(String strURL) throws Exception{
	
	 URL url = new URL(strURL);  
	 HttpURLConnection httpConn = (HttpURLConnection)  
	         url.openConnection();  
	 httpConn.setRequestMethod("GET");  
	 httpConn.connect();  
	   
	 BufferedReader reader = new BufferedReader(new InputStreamReader(  
	         httpConn.getInputStream(),"utf-8"));  
	 String line;  
	 StringBuffer buffer = new StringBuffer();  
	 while ((line = reader.readLine()) != null) {  
	     buffer.append(line);  
	 }  
	 reader.close();  
	 httpConn.disconnect();  

	 return buffer.toString();
	}
 public static void main(String[] args) throws Exception {
	 String msg = get("https://api.weixin.qq.com/cgi-bin/user/info?access_token=5_j9zzPVu0HhTeTSmaeO4ghLeY42AqTVz4oYgiGKTzSHBIX8riDYFvd5y2F6Hlp084Kbm0vhph3yAFxTzAYxImqtAET9xcwPHqVDRySExE_jwjDsdftAgXvYvsWfmsZszNeFbLEhL-0U2xrJGpEMEaAIATBT&openid=onqd-1Dxc7CEAKPCLDSS5DQZkSes&lang=zh_CN");
     System.out.println(msg);
 }
}
