package com.happy.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.happy.dao.OneMenuDao;
import com.happy.model.OneMenu;
@Repository("OneMenuDao")
public class OneMenuDaoImpl implements OneMenuDao {
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}
	public void setNamedParameterJdbcTemplate(
			NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}


	
	//查询并获得一级菜单
	public List<OneMenu> getOneMenuList() {
		String sql = "select * from onemenu where 1 = 1";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		List<OneMenu> list =  namedParameterJdbcTemplate.query(sql, sps,new BeanPropertyRowMapper<OneMenu>(OneMenu.class));
		if(list != null && list.size()>0){
			return list;
		}
		return null;
	}
	

}
