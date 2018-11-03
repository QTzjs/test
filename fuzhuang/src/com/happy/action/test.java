package com.happy.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.happy.model.place_technology;
import com.happy.util.DBtoExcel;

/**
 * 导出表格工具类
 * @author zjs
 *
 */
public class test {

	//根据订单号来打印出所有的提交记录
	public void Printing(List<place_technology> plat,String place_num,String path) throws ClassNotFoundException, SQLException {
//		String DRIVER = "com.mysql.jdbc.Driver";  
//        String URL = "jdbc:mysql://182.61.48.227:3306/fuzhaung?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=UTF-8;";  
//        String USERNAME = "admin";  
//        String USERPASSWORD = "some_pass";  
        
        String sql = "SELECT user_name,";
        Vector columnName = new Vector(); // 列名
        columnName.add("姓名");  
        for(int i=0;i<plat.size();i++){
        	sql+=" IFNULL(sum(case t_name when '"+plat.get(i).getTechnology_name()+"' then number end),' ') as '"+plat.get(i).getTechnology_name()+"'";
        	if(!(i==plat.size()-1)){
        		sql+=",";
        	}else{
        		sql+=" ";
        	}
        	columnName.add(plat.get(i).getTechnology_name()); 
        }
        sql+=" FROM sub_data WHERE place_num='"+place_num+"' GROUP BY user_name";
//        sql = "SELECT user_name, IFNULL(sum(case t_name when '点位' then number end),0) as '点位', IFNULL(sum(case t_name when '双针车口袋' then number end),0) as '双针车口袋', IFNULL(sum(case t_name when '平车贴口袋' then number end),0) as '平车贴口袋', IFNULL(sum(case t_name when '搭肩' then number end),0) as '搭肩' FROM sub_data WHERE place_num='61905(BXSY)' GROUP BY user_name"; // 查询语句  
//        columnName.add("点位");  
//        columnName.add("双针车口袋"); 
        
          
        // 连接数据库  
//        Class.forName(DRIVER);  
//        Connection conn = (Connection) DriverManager.getConnection(URL,USERNAME,USERPASSWORD);  
//        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);  
//        ResultSet rs = (ResultSet) ps.executeQuery();  
//        System.out.println(path);
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        DataSource dataSource = (DataSource) ac.getBean("dataSource");
        Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        // 导出文件  
        new DBtoExcel().WriteExcel(rs, path+place_num+"Report.xls", place_num+"订单提交记录报表", columnName);  
	}
	
	
	public void Printing(String sql,Vector columnName,String path) throws SQLException{
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        DataSource dataSource = (DataSource) ac.getBean("dataSource");
        Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        // 导出文件  
        new DBtoExcel().WriteExcel(rs, path+"Report.xls", "订单提交记录报表", columnName);
	}

	

}
