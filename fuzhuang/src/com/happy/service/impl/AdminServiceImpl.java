package com.happy.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.happy.dao.AdminDao;
import com.happy.model.Admin;
import com.happy.service.AdminService;
@Service("AdminService")
public class AdminServiceImpl implements AdminService {

	@Resource
	private AdminDao adminDao;
	
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
    
	//根据用户名和密码查询管理员
	public Admin getAdmin(String account, String password) {
		return adminDao.getAdmin(account,password);
	}
	//根据用户名和密码修改密码
	public int updateAdmin(String account, String password) {
		return adminDao.updateAdmin(account, password);
	}

}
