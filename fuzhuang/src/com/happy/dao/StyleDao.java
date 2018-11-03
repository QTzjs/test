package com.happy.dao;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.happy.model.size_color;
import com.happy.model.style;

public interface StyleDao {
	
	/**
	 * 获取款式列表
	 * @return
	 */
	List<style> getStyleList();
	/**
	 * 根据当前页和每页条数获取款式列表
	 * @return
	 */
	List<style> getStyleList(int page,int rows);
	/**
	 * 添加款式
	 * @param string
	 * @return
	 */
	int addStyle(String string);
	/**
	 * 更具id查询款式
	 * @param id
	 * @return
	 */
	style findByid(int id);
	/**
	 * 编辑款式
	 * @param st
	 * @return
	 */
	int updateStyle(style st);
	/**
	 * 删除款式
	 * @param parseInt
	 * @return
	 */
	int deleteStyle(int parseInt);
	
	/**
	 * 批量删除款式
	 */
	int batchdeleteStyle(String sqlx);
	/**
	 * 组合条件查询
	 * @param sqlx
	 * @return
	 */
	List<style> combGetStyleList(String sqlx);
	/**
	 * 组合条件分页查询
	 * @param sqlx
	 * @param page
	 * @param rows
	 * @return
	 */
	List<style> combGetStyleList(String sqlx, int page, int rows);
}
