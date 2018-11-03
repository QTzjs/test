package com.happy.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.happy.dao.StyleDao;
import com.happy.model.size_color;
import com.happy.model.style;
@Repository("styleDao")
public class StyleDaoImpl implements StyleDao {
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}
	public void setNamedParameterJdbcTemplate(
			NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	//获取款式列表
	public List<style> getStyleList() {
		String sql = "select * from style where fake=0";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		List<style> list = namedParameterJdbcTemplate.query(sql, sps,new BeanPropertyRowMapper<style>(style.class));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	/**
	 * 根据当前页和每页条数获取款式列表
	 * @return
	 */
	public List<style> getStyleList(int page,int rows){
		int start = (page-1)*rows;//每页的起始下标
		String sql = "select * from style where fake=0 limit :start,:rows";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("start",start);
		sps.addValue("rows",rows);
		List<style> list = namedParameterJdbcTemplate.query(sql, sps,new BeanPropertyRowMapper<style>(style.class));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	//添加款式
	public int addStyle(String string) {
		String sql = "INSERT INTO `style` (`style_name`, `fake`) VALUES (:style_name,:fake)";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("style_name", string);
		sps.addValue("fake", 0);
		return namedParameterJdbcTemplate.update(sql, sps);
	}
	//更具id查询款式
	public style findByid(int id) {
		String sql = "select * from style where id=:id";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("id", id);
		List<style> list = namedParameterJdbcTemplate.query(sql, sps,new BeanPropertyRowMapper<style>(style.class));
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	//编辑款式
	public int updateStyle(style st) {
		String sql = "update style set style_name=:style_name where id=:id";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("style_name", st.getStyle_name());
		sps.addValue("id", st.getId());
		return namedParameterJdbcTemplate.update(sql, sps);
	}
	//删除款式
	public int deleteStyle(int parseInt) {
		String sql = "delete from style where id=:id";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("id", parseInt);
		return namedParameterJdbcTemplate.update(sql, sps);
	}
	// 批量删除款式
	
	public int batchdeleteStyle(String sqlx){
		String sql = sqlx;
		MapSqlParameterSource sps = new MapSqlParameterSource();
		int num = namedParameterJdbcTemplate.update(sql, sps);
		if(num > 0 ){
			return num;
		}
		return 0;
	}
	//组合条件查询
	public List<style> combGetStyleList(String sqlx) {
		String sql = "select * from style "+sqlx;
		MapSqlParameterSource sps = new MapSqlParameterSource();
		List<style> list = namedParameterJdbcTemplate.query(sql, sps,new BeanPropertyRowMapper<style>(style.class));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	//组合条件分页查询
	public List<style> combGetStyleList(String sqlx, int page, int rows) {
		int start = (page-1)*rows;//每页的起始下标
		String sql = "select * from style "+sqlx+" limit :start,:rows";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("start", start);
		sps.addValue("rows", rows);
		List<style> list = namedParameterJdbcTemplate.query(sql, sps,new BeanPropertyRowMapper<style>(style.class));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	
	

}
