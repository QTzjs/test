package com.happy.service;
/**
 * 一级菜单业务接口
 */
import java.util.List;

import com.happy.model.OneMenu;

public interface OneMenuService {
	/**
	 * 查询并获得一级菜单
	 * @return
	 */
	public List<OneMenu> getOneMenuList();
}
