package com.happy.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.happy.dao.TwoMenuDao;
import com.happy.model.TwoMenu;
@Repository("TwoMenuDao")
public class TwoMenuDaoImpl implements TwoMenuDao {
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}

	public void setNamedParameterJdbcTemplate(
			NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public List<TwoMenu> getTwoMenu(int pid) {
		String sql = "select id,pid,text,state,iconCls,url,level from twomenu where id=:pid";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("pid", pid);
		List<TwoMenu> list = namedParameterJdbcTemplate.query(sql, sps,new BeanPropertyRowMapper<TwoMenu>(TwoMenu.class));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	public List<TwoMenu> getTwoMenu1(int pid) {
		String sql = "select id,pid,text,state,iconCls,url,level from twomenu where pid=:pid";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("pid", pid);
		List<TwoMenu> list = namedParameterJdbcTemplate.query(sql, sps,new BeanPropertyRowMapper<TwoMenu>(TwoMenu.class));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

}
