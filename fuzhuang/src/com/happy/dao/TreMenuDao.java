package com.happy.dao;

import java.util.List;

import com.happy.model.TreMenu;

public interface TreMenuDao {
	/**
	 * 查询并获得三级菜单
	 * @param pid
	 * @return
	 */
	public List<TreMenu> getTreMenu(int pid);
	/**超级管理员专用
	 * 查询并获得三级菜单
	 * @param pid
	 * @return
	 */
	//public List<TreMenu> getTreMenu1(int pid);
	/**
	 * 查询所有菜单修改权限添加权限专用
	 */
	
}
