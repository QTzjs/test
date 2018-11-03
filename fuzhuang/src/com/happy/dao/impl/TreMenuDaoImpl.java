package com.happy.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.happy.dao.TreMenuDao;
import com.happy.model.TreMenu;
@Repository("TreMenuDao")
public class TreMenuDaoImpl implements TreMenuDao {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}


	public void setNamedParameterJdbcTemplate(
			NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}


	//根据pid查询菜单列表
	public List<TreMenu> getTreMenu(int pid) {
		String sql="select * from tremenu where pid=:pid ";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("pid", pid);
		List<TreMenu> list = namedParameterJdbcTemplate.query(sql, sps, new BeanPropertyRowMapper<TreMenu>(TreMenu.class));
		if(list != null && list.size()>0){
			return list;
		}
		return null;
	}
/*	//根据pid查询菜单列表
	public List<TreMenu> getTreMenu(int pid) {
		String sql="select * from tremenu where 1=1";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("pid", pid);
		List<TreMenu> list = namedParameterJdbcTemplate.query(sql, sps, new BeanPropertyRowMapper<TreMenu>(TreMenu.class));
		if(list != null && list.size()>0){
			return list;
		}
		return null;
	}*/
}
