package com.happy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.happy.dao.TreMenuDao;
import com.happy.model.TreMenu;
import com.happy.service.TreMenuService;
@Service("TreMenuService")
public class TreMenuServiceImpl implements TreMenuService {
	@Resource
	private TreMenuDao treMenuDao;
	
	public TreMenuDao getTreMenuDao() {
		return treMenuDao;
	}

	public void setTreMenuDao(TreMenuDao treMenuDao) {
		this.treMenuDao = treMenuDao;
	}

	public List<TreMenu> getTreMenu(int pid) {
		return treMenuDao.getTreMenu(pid);
	}
	

}
