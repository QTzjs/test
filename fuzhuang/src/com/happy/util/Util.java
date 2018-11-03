package com.happy.util;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import flexjson.JSONSerializer;


public class Util {

	
	public static void writeJson(HttpServletResponse respone, String result) {
		respone.setContentType("application/json;charset=utf-8");
		PrintWriter out;
		try {
			out = respone.getWriter();
			out.print(result);
			out.flush();
			out.close();
		} catch (Exception e) {
			//System.out.println("Comm_Util_writeJson---->" + e);
		}
	}
	public static String formatJAVAToJSON(Object obj){
		String result = "";
		result = new JSONSerializer().exclude("*.class").deepSerialize(obj);  
		return result;
	}
	/**
	 * 把ResultSet集合转换成JsonArray数组
	 * @param rs
	 * @return
	 * @throws Exception
	 */
	public static JSONArray formatRsToJsonArray(ResultSet rs)throws Exception{
		ResultSetMetaData md=rs.getMetaData();
		int num=md.getColumnCount();
		JSONArray array=new JSONArray();
		while(rs.next()){
			JSONObject mapOfColValues=new JSONObject();
			for(int i=1;i<=num;i++){
				mapOfColValues.put(md.getColumnName(i), rs.getObject(i));
			}
			array.add(mapOfColValues);
		}
		return array;
	}
	
}
