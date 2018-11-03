package com.happy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.happy.dao.OneMenuDao;
import com.happy.model.OneMenu;
import com.happy.service.OneMenuService;

@Service("OneMenuService")
public class OneMenuServiceImpl implements OneMenuService {
	
	@Resource
	private OneMenuDao oneMenuDao;
	public void setOneMenuDao(OneMenuDao oneMenuDao) {
		this.oneMenuDao = oneMenuDao;
	}
	public OneMenuDao getOneMenuDao() {
		return oneMenuDao;
	}
	//获取一级菜单
	public List<OneMenu> getOneMenuList() {
		return oneMenuDao.getOneMenuList();
	}
	
}
