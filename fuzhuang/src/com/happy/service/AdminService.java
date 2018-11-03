package com.happy.service;

import com.happy.model.Admin;

public interface AdminService {
	/**
	 * 根据用户名和密码查询管理员
	 * @param username
	 * @param password
	 * @return
	 */
	public Admin getAdmin(String account,String password);
	/**
	 * 根据用户名和密码更改密码
	 * @param username
	 * @param password
	 * @return
	 */
	int updateAdmin(String account, String password);
}
