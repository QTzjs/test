package com.happy.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.happy.dao.technologyDao;
import com.happy.model.place;
import com.happy.model.technology;

@Repository("technologyDao")
public class technologyDaoImpl implements technologyDao{
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}
	public void setNamedParameterJdbcTemplate(
			NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	public int add_gong(int number,String name) {
		String sql="INSERT INTO `technology` (`number`, `name`, `fake`) VALUES (:number,:name,:fake);";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("number", number);
		sps.addValue("name", name);
		sps.addValue("fake", 0);
		KeyHolder keyholder = new GeneratedKeyHolder();
		namedParameterJdbcTemplate.update(sql, sps, keyholder);
		// 加上KeyHolder这个参数可以得到添加后主键的值
		int m = keyholder.getKey().intValue();
		return m;
	}
	public List<technology> getList() {
		String sql="select * from technology where fake=0";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		List<technology> list = namedParameterJdbcTemplate.query(sql, sps,
				new BeanPropertyRowMapper<technology>(technology.class));
		return list;
	}
	
	/**
	 * 根据当前页每页显示行数查询所有工序
	 */
	public List<technology> getList(int page,int rows){
		int start = (page-1)*rows;//每页的起始下标
		String sql="select * from technology where fake=0 limit :start,:rows";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("start",start);
		sps.addValue("rows",rows);
		List<technology> list = namedParameterJdbcTemplate.query(sql, sps,
				new BeanPropertyRowMapper<technology>(technology.class));
		return list;
		
	}
	//根据工序id查询工序详情信息
	public technology findById(int id) {
		String sql = "select * from technology where id=:id";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("id", id);
		List<technology> te = namedParameterJdbcTemplate.query(sql, sps, new BeanPropertyRowMapper<technology>(technology.class));
		if(te != null && te.size()>0){
			return te.get(0);
		}
		return null;
	}
	//更新信息
	public int update(technology te) {
		String sql = "update technology set number=:number,name=:name where id=:id";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("number", te.getNumber());
		sps.addValue("name", te.getName());
		sps.addValue("id", te.getId());
		return namedParameterJdbcTemplate.update(sql, sps);
	}
	//删除
	public int delete(int parseInt) {
		String sql = "update technology set fake=1 where id=:id";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("id", parseInt);
		return namedParameterJdbcTemplate.update(sql, sps);
	}
	//批量删除
	public int batchDelete(String sqlx){
		String sql = sqlx;
		MapSqlParameterSource sps = new MapSqlParameterSource();
		int num = namedParameterJdbcTemplate.update(sql, sps);
		if(num > 0 ){
			return num;
		}
		return 0;
	}
	public technology findByName(String string) {
		String sql = "select * from technology where name=:name and fake=0";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("name", string);
		List<technology> te = namedParameterJdbcTemplate.query(sql, sps, new BeanPropertyRowMapper<technology>(technology.class));
		if(te != null && te.size()>0){
			return te.get(0);
		}
		return null;
	}
	public technology findBynumber(int parseInt) {
		String sql = "select * from technology where number=:number and fake=0";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("number", parseInt);
		List<technology> te = namedParameterJdbcTemplate.query(sql, sps, new BeanPropertyRowMapper<technology>(technology.class));
		if(te != null && te.size()>0){
			return te.get(0);
		}
		return null;
	}

}
