package com.happy.dao;


import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.happy.model.Record;
import com.happy.model.place;
public interface RecordDao {

	/**
	 * 显示所有操作记录
	 * @param 
	 * @param 
	 * @return
	 */
	public List<Record>  getAllList();
	/**
	 * 分页显示所有操作记录
	 * @param 
	 * @param 
	 * @return
	 */
	public List<Record>  getAllList(int page,int rows);
	/**
	 * 组合查询
	 * @param sqlx
	 * @return
	 */
	public List<Record> combGetRecordList(String sqlx);
	/**
	 * 组合查询
	 * @param sqlx
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<Record> combGetRecordList(String sqlx, int page, int rows);
	/**
	 * 添加记录
	 * @param 
	 * @param 
	 * @return
	 */
	public int add(Record r) ;

	
}
