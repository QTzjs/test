package com.happy.service;

import java.util.List;

import com.happy.model.TreMenu;

public interface TreMenuService {
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
}
