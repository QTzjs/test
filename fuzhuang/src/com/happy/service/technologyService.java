package com.happy.service;

import java.util.List;

import com.happy.model.technology;

public interface technologyService {

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
		 * 更新
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
		 */
		public int batchDelete(String sqlx);
		/**
		 * 根据工序name查询表中有没有同名的工序
		 * @param string
		 * @return
		 */
		public technology findByName(String string);
		/**
		 * 根据工序number查询表中有没有同名的工序
		 * @param parseInt
		 * @return
		 */
		public technology findBynumber(int parseInt);
}
