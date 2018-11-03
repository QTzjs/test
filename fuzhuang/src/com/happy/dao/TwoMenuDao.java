package com.happy.dao;

import java.util.List;

import com.happy.model.TwoMenu;


public interface TwoMenuDao {
	/**
	 * 查询并获得二级菜单
	 */
	public List<TwoMenu> getTwoMenu(int pid);
	/**超级管理员专用
	 * 查询并获得二级菜单
	 */
	public List<TwoMenu> getTwoMenu1(int pid);
	
}
