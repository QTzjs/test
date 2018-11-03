package com.happy.dao.impl;

import java.util.List;

import net.sf.json.util.NewBeanInstanceStrategy;

import org.apache.struts2.components.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.happy.dao.AdminDao;
import com.happy.model.Admin;
@Repository("AdminDao")
public class AdminDaoImpl implements AdminDao {
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}

	public void setNamedParameterJdbcTemplate(
			NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
   
	
	//根据用户名和密码查询管理员
	public Admin getAdmin(String account, String password) {
		String sql = "select admin_id,admin_name,admin_account,admin_password,level,fake from admin where admin_account=:account and admin_password=:password and fake=0";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("account", account);
		sps.addValue("password", password);
		List<Admin> list = namedParameterJdbcTemplate.query(sql, sps, new BeanPropertyRowMapper<Admin>(Admin.class));
		if(list != null && list.size() > 0){
			return list.get(0);
		}http://serisboy.iteye.com/blog/1910107
		return null;
	}
	//根据用户名和密码修改密码
	public int updateAdmin(String admin_id, String password) {
		String sql = "update admin set admin_password=:password  where admin_id=:admin_id  and fake=0";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("admin_id", admin_id);
		sps.addValue("password", password);
		int num = namedParameterJdbcTemplate.update(sql, sps);
		if( num > 0){
			return num;
		}
		return 0;
	}

}
