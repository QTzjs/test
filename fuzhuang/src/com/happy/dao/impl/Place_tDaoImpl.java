package com.happy.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.happy.dao.Place_tDao;
import com.happy.model.place_technology;
import com.mchange.v2.c3p0.ComboPooledDataSource;
@Repository("place_tDao")
public class Place_tDaoImpl implements Place_tDao {
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}
	public void setNamedParameterJdbcTemplate(
			NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	
	//根据 订单号 查询 该订单的所有绑定的工序
	public List<place_technology> findByNum(String place) {
		String sql="select * from place_technology nolock where place_number=:place_number and fake=0";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("place_number",place);
		List<place_technology> list = namedParameterJdbcTemplate.query(sql, sps,
				new BeanPropertyRowMapper<place_technology>(place_technology.class));
		if(list!=null && list.size() > 0){
			return list;
		}
		return null;
	}
	
	//新增数据
	public int add_gong(place_technology pt) {
		String sql="INSERT INTO `place_technology` (`place_number`, `technology_name`, `price`, `fake`) VALUES (:place_number,:technology_name,:price,:fake)";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("place_number", pt.getPlace_number());
		sps.addValue("technology_name", pt.getTechnology_name());
		sps.addValue("fake", 0);
		sps.addValue("price", pt.getPrice());
		KeyHolder keyholder = new GeneratedKeyHolder();
		namedParameterJdbcTemplate.update(sql, sps, keyholder);
		// 加上KeyHolder这个参数可以得到添加后主键的值
		int m = keyholder.getKey().intValue();
		return m;
	}
	
	//根据id删除订单绑定的工序
	public int delete_gong(int parseInt) {
		String sql = "DELETE FROM place_technology where id=:id";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("id", parseInt);
		int num = namedParameterJdbcTemplate.update(sql, sps);
		if(num >0){
			return num;
		}
		return 0;
	}
	
	//根据id批量删除订单绑定的工序
	public int batchdelete_gong(String sqlx) {
		String sql = sqlx;
		MapSqlParameterSource sps = new MapSqlParameterSource();
		int num = namedParameterJdbcTemplate.update(sql, sps);
		if(num > 0 ){
			return num;
		}
		return 0;
	}
	
	//根据订单号分页查询该订单绑定的工序
	public List<place_technology> findByNum(String place, int page, int rows) {
		int start = (page-1)*rows;//每页的起始下标
		String sql="select * from place_technology nolock where place_number=:place_number and fake=0 limit :start,:rows ";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("place_number",place);
		sps.addValue("start",start);
		sps.addValue("rows",rows);
		List<place_technology> list = namedParameterJdbcTemplate.query(sql, sps,
				new BeanPropertyRowMapper<place_technology>(place_technology.class));
		if(list!=null && list.size() > 0){
			return list;
		}
		return null;
	}
	
	//通过订单号和工序名称查询  订单绑定工序表 的单价
	public BigDecimal findByPandT(String place_num, String technology_name) {
		String sql="select price from place_technology nolock where place_number=:place_number and technology_name=:technology_name and fake=0";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("place_number", place_num);
		sps.addValue("technology_name", technology_name);
		List<place_technology> p = namedParameterJdbcTemplate.query(sql, sps, new BeanPropertyRowMapper<place_technology>(place_technology.class));
		if(p!=null && p.size()>0){
			return p.get(0).getPrice();
		}
		return null;
	}
	//根据订单号查询订单绑定的工序
	public List<place_technology> getAllpt(String place_num) {
		String sql = "select id,technology_name from place_technology where fake=0 and place_number=:place_number";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("place_number", place_num);
		List<place_technology> p = namedParameterJdbcTemplate.query(sql, sps, new BeanPropertyRowMapper<place_technology>(place_technology.class));
		if(p!=null && p.size()>0){
			return p;
		}
		return null;
	}
	
}
