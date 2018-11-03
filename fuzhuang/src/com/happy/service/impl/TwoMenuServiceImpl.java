package com.happy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.happy.dao.TwoMenuDao;
import com.happy.model.TwoMenu;
import com.happy.service.TwoMenuService;
@Service("TwoMenuService")
public class TwoMenuServiceImpl implements TwoMenuService {
	@Resource
	private TwoMenuDao twoMenuDao;
	public TwoMenuDao getTwoMenuDao() {
		return twoMenuDao;
	}
	public void setTwoMenuDao(TwoMenuDao twoMenuDao) {
		this.twoMenuDao = twoMenuDao;
	}

	public List<TwoMenu> getTwoMenu(int pid) {
		return twoMenuDao.getTwoMenu(pid);
	}
	public List<TwoMenu> getTwoMenu1(int pid) {
		return twoMenuDao.getTwoMenu1(pid);
	}

}
