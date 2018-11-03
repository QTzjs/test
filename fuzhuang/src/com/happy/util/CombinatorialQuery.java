package com.happy.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 前端框架复合查询工具类
 * @author zjs
 *
 */
public class CombinatorialQuery {
	//组合条件查询
	public static String query(String condition){
		String json = condition;
		String sql = "where fake=0 ";
		JSONArray jsonArray=JSONArray.fromObject(json);
		String join = "";
		for (int j= 0;j<jsonArray.size();j++){  
		   JSONObject jsonObject=jsonArray.getJSONObject(j);
		   
	       String lb=jsonObject.getString("lb"); 
	       String field=jsonObject.getString("field");  
	       String op=jsonObject.getString("op"); 
	       String value=jsonObject.getString("value");  
	       String rb=jsonObject.getString("rb");
	       
	       if(!field.equals("")){
	    	   
	    	   if(op.equals("contains")){//包含
	    		   sql += join+" "+ field +" like '%"+value+"%' "; 
	    	   }else if(op.equals("equal")){//等于
	    		   sql += join+" "+ field +"='"+value+"' ";
	    	   }else if(op.equals("notequal")){//不等于
	    		   sql += join+" "+ field +"<>'"+value+"' ";
	    	   }else if(op.equals("greater")){//大于
	    		   sql += join+" "+ field +">'"+value+"' ";
	    	   }else if(op.equals("greaterorequal")){//大于或等于
	    		   sql += join+" "+ field +">='"+value+"' ";
	    	   }else if(op.equals("less")){//小于
	    		   sql += join+" "+ field +"<'"+value+"' ";
	    	   }else if(op.equals("lessorequal")){//小于或等于
	    		   sql += join+" "+ field +"<='"+value+"' ";
	    	   }else if(op.equals("beginwith")){//以....开头
	    		   sql += join+" "+ field +" like '"+value+"%' ";
	    	   }else if(op.equals("endwith")){//以....结尾
	    		   sql += join+" "+ field +" like '%"+value+"' ";
	    	   }
	       }
		} 
		if(!sql.equals("where ")){
			return sql;
		}
		return null;
	}
	
	/**
	 * 复合查询工具类
	 * @param page				//第page页
	 * @param rows				//每页行数
	 * @param advanceFilter		//组合查询条件
	 * @param sort				//排序字段名
	 * @param order				//排序方式
	 * @param filterRules		//过滤条件
	 * @return
	 */
	public static String queryOne(String advanceFilter,String sort,String order,String filterRules){
		String sql = "where fake=0 ";
		if(advanceFilter != null && !advanceFilter.equals("[]") ){
//			System.out.println("advanceFilter有条件");
			
			String json = advanceFilter;
			JSONArray jsonArray=JSONArray.fromObject(json);
			for (int j= 0;j<jsonArray.size();j++){  
			   JSONObject jsonObject=jsonArray.getJSONObject(j);
			   String join=jsonObject.getString("join");
		       String lb=jsonObject.getString("lb"); 
		       String field=jsonObject.getString("field");  
		       String op=jsonObject.getString("op"); 
		       String value=jsonObject.getString("value");  
		       String rb=jsonObject.getString("rb");
		       
		       if(!field.equals("")){
		    	   
		    	   if(op.equals("contains")){//包含
		    		   sql += join+" "+ field +" like '%"+value+"%' "; 
		    	   }else if(op.equals("equal")){//等于
		    		   sql += join+" "+ field +"='"+value+"' ";
		    	   }else if(op.equals("notequal")){//不等于
		    		   sql += join+" "+ field +"<>'"+value+"' ";
		    	   }else if(op.equals("greater")){//大于
		    		   sql += join+" "+ field +">'"+value+"' ";
		    	   }else if(op.equals("greaterorequal")){//大于或等于
		    		   sql += join+" "+ field +">='"+value+"' ";
		    	   }else if(op.equals("less")){//小于
		    		   sql += join+" "+ field +"<'"+value+"' ";
		    	   }else if(op.equals("lessorequal")){//小于或等于
		    		   sql += join+" "+ field +"<='"+value+"' ";
		    	   }else if(op.equals("beginwith")){//以....开头
		    		   sql += join+" "+ field +" like '"+value+"%' ";
		    	   }else if(op.equals("endwith")){//以....结尾
		    		   sql += join+" "+ field +" like '%"+value+"' ";
		    	   }
		       }
			}
		}
		if(filterRules != null && !filterRules.equals("[]")){
//			System.out.println("filterRules有条件");
			
			String json = filterRules;
			JSONArray jsonArray=JSONArray.fromObject(json);
			JSONObject jsonObject=jsonArray.getJSONObject(0);
			String field = jsonObject.getString("field");
	        String value=jsonObject.getString("value");
	        sql += "and "+field +" like '%"+value+"%' ";
			
		}
		if(sort != null){
//			System.out.println("sort有排序");
			sql += "order by "+sort+" "+order+" ";
		}
		return sql;
	}
	
	//组合条件+排序条件查询
	public static String queryTwo(String string1,String string2,String string3){
		System.out.println(string1);
		System.out.println(string2);
		System.out.println(string3);
		return null;
	}
	
	public static void main(String[] args) {
		double aa = -19162431.1254;
        String a = "19162b431";
        String b = "-19162431a1254";
        String c = "中文";
        System.out.println(isNumericZidai(Double.toString(aa)));
        System.out.println(isNumericZidai(a));
        System.out.println(isNumericZidai(b));
        System.out.println(isNumericZidai(c));
	}
	
	public static boolean isNumericZidai(String str) {
        for (int i = 0; i < str.length(); i++) {
//            System.out.println(str.charAt(i));
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}
