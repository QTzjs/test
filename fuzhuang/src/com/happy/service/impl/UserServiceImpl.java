package com.happy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;

import com.happy.dao.UserDao;
import com.happy.model.weixin.User;
import com.happy.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	//通过openid查询员工
	public User findByOpenid(String oPEN_ID) {
		return userDao.findByOpenid(oPEN_ID);
	}
	//通过手机号和工号查询员工
	public User findByAccountAndJob(User user) {
		return userDao.findByAccountAndJob(user);
	}
	//更新数据
	public void update(User u) {
		userDao.update(u);
	}
	public List<User> getlist() {
		
		return userDao.getlist();
	}
	//根据当前页和每页条数查询所有员工
	public List<User> getlist(int page,int rows){
		return userDao.getlist(page, rows);
	}
	public int add_user(User u) {
	
		return userDao.add_user(u);
	}
	public int updateuser(User u) {
		
		return userDao.updateuser(u);
	}
	public List<User> findById(int id) {
		return userDao.findById(id);
	}
	
	
	public int delete(int id) {
		return userDao.delete(id);
	}
	//批量删除
	public int bathDelete(String sqlx){
		return userDao.bathDelete(sqlx);
	}
	//组合条件查询
	public List<User> combGetUserList(String sql) {
		return userDao.combGetUserList(sql);
	}
	//组合条件分页查询
	public List<User> combGetUserList(String sql, int page, int rows) {
		return userDao.combGetUserList(sql,page,rows);
	}
}
