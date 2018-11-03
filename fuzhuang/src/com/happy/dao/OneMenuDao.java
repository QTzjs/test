package com.happy.dao;
/**
 * 一级菜单dao层接口
 */
import java.util.List;

import com.happy.model.OneMenu;

public interface OneMenuDao {
	
	/**
	 * 根据用户查询并获得一级菜单
	 * @return
	 */
	public List<OneMenu> getOneMenuList();
	/**
	 * 获取所有菜单
	 */
	

}
