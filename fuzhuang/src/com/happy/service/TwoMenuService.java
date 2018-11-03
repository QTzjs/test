package com.happy.service;

import java.util.List;

import com.happy.model.TwoMenu;

public interface TwoMenuService {
	/**
	 * 查询并获得二级菜单
	 */
	public List<TwoMenu> getTwoMenu(int pid);
	/**超级管理员专用
	 * 查询并获得二级菜单
	 */
	public List<TwoMenu> getTwoMenu1(int pid);
}
