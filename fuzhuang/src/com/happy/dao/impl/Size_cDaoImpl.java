package com.happy.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.happy.dao.Size_cDao;
import com.happy.model.size_color;
@Repository("size_cDao")
public class Size_cDaoImpl implements Size_cDao {
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}
	public void setNamedParameterJdbcTemplate(
			NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	//根据款式id查询款式的尺寸颜色
	public List<size_color> getStyleC(int id) {
		String sql = "select * from size_color where K_id=:K_id and fake=0";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("K_id", id);
		List<size_color> clist = namedParameterJdbcTemplate.query(sql, sps, new BeanPropertyRowMapper<size_color>(size_color.class));
		if(clist != null && clist .size() > 0){
			return clist;
		}
		return null;
	}
	
	// 根据款式id当前页和每页条数查询款式的尺寸颜色 
	public List<size_color> getStyleC(int id,int page,int rows){
		int start = (page-1)*rows;//每页的起始下标
		String sql = "select * from size_color where K_id=:K_id and fake=0 limit :start,:rows";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("K_id", id);
		sps.addValue("start",start);
		sps.addValue("rows",rows);
		List<size_color> clist = namedParameterJdbcTemplate.query(sql, sps, new BeanPropertyRowMapper<size_color>(size_color.class));
		if(clist != null && clist .size() > 0){
			return clist;
		}
		return null;
	}
	
	//添加款式详情
	public int addStyleC(int i, String string2, String string3) {
		String sql = "INSERT INTO `size_color` (`k_id`,`size`,`color`, `fake`) VALUES (:k_id,:size,:color,:fake)";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("k_id", i);
		sps.addValue("size", string2);
		sps.addValue("color", string3);
		sps.addValue("fake", 0);
		return namedParameterJdbcTemplate.update(sql, sps);
	}
	//根据款式详情id查询款式详情记录
	public size_color findByCid(int id) {
		String sql ="select * from size_color where id=:id";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("id", id);
		List<size_color> s = namedParameterJdbcTemplate.query(sql, sps, new BeanPropertyRowMapper<size_color>(size_color.class));
		if(s != null && s.size()>0){
			return s.get(0);
		}
		return null;
	}
	//编辑款式详情
	public int updateStyleC(size_color sc) {
		String sql = "update size_color set color=:color,size=:size where id=:id";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("color", sc.getColor());
		sps.addValue("size", sc.getSize());
		sps.addValue("id", sc.getId());
		return namedParameterJdbcTemplate.update(sql, sps);
	}
	//删除款式详情
	public int deleteStyleC(int parseInt) {
		String sql = "delete from size_color where id=:id ";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("id", parseInt);
		return namedParameterJdbcTemplate.update(sql, sps);
	}
	//批量删除款式详情
	public int batchdeleteStyleC(String sqlx) {
		String sql = sqlx;
		MapSqlParameterSource sps = new MapSqlParameterSource();
		int num = namedParameterJdbcTemplate.update(sql, sps);
		if(num > 0 ){
			return num;
		}
		return 0;
	}
	
}
