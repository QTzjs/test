package com.happy.dao;

import java.util.List;

import com.happy.model.technology;

/**
 * 
 * @author QT-001
 * 工序接口
 */
public interface technologyDao {
   /**
    * 添加工序
    */
	public int add_gong(int number,String name);
	/**
	 * 查询所有工序
	 */
	public List<technology> getList();
	/**
	 * 根据当前页每页显示行数查询所有工序
	 */
	public List<technology> getList(int page,int rows);
	/**
	 * 根据工序id查询工序详情信息
	 * @param id
	 * @return
	 */
	public technology findById(int id);
	/**
	 * 更新信息
	 * @param te
	 * @return
	 */
	public int update(technology te);
	
	/**
	 * 删除
	 * @param parseInt
	 * @return
	 */
	public int delete(int parseInt);
	/**
	 * 批量删除
	 * @param parseInt
	 * @return
	 */
	public int batchDelete(String sqlx);
	public technology findByName(String string);
	public technology findBynumber(int parseInt);
}
