package com.happy.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.happy.dao.Packe_tDao;
import com.happy.model.Packe_technology;
@Repository("packe_tDao")
public class Packe_tDaoImpl implements Packe_tDao{
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}
	public void setNamedParameterJdbcTemplate(
			NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	//批量添加  包绑定工序表 的数据
	public int batchAddTechnology(String sql1) {
		String sql = sql1;
		MapSqlParameterSource sps = new MapSqlParameterSource();
		int num = namedParameterJdbcTemplate.update(sql, sps);
		if(num > 0 ){
			return num;
		}
		return 0;
	}
	
	//添加 包绑定工序
	public void addTechnology(Packe_technology packe_technology) {
		String sql = "insert into packe_technology (packe_num,technology_name,state) values(:packe_num,:technology_name,:state)";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("packe_num", packe_technology.getPacke_num());
		sps.addValue("technology_name", packe_technology.getTechnology_name());
		sps.addValue("state", packe_technology.getState());
		namedParameterJdbcTemplate.update(sql, sps);
	}
	
	//根据包号查询这个包所绑定的所有工序
	public List<Packe_technology> findByPackT(String packe_num) {
		String sql = "select * from packe_technology where packe_num=:packe_num";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("packe_num", packe_num);
		List<Packe_technology> list = namedParameterJdbcTemplate.query(sql, sps,new BeanPropertyRowMapper<Packe_technology>(Packe_technology.class));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	
	//查询已完成工序列表
	public List<Packe_technology> findByPackTcom(String packe_num) {
		String sql = "select * from packe_technology where packe_num=:packe_num and state=2";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("packe_num", packe_num);
		List<Packe_technology> list = namedParameterJdbcTemplate.query(sql, sps,new BeanPropertyRowMapper<Packe_technology>(Packe_technology.class));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	
	//查询未完成工序列表
	public List<Packe_technology> findByPackTyet(String packe_num) {
		String sql = "select * from packe_technology where packe_num=:packe_num and state=1";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("packe_num", packe_num);
		List<Packe_technology> list = namedParameterJdbcTemplate.query(sql, sps,new BeanPropertyRowMapper<Packe_technology>(Packe_technology.class));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	
	//改变包号绑定工序表单的工序状态为2已完成
	public int updatePt(int id,int state) {
		String sql = "update packe_technology set state=:state where id=:id";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("id", id);
		sps.addValue("state", state);
		return namedParameterJdbcTemplate.update(sql, sps);
	}
	
	//id查询包邦定工序记录
	public Packe_technology findbByPt(int id) {
		String sql = "select * from packe_technology where id=:id";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("id", id);
		List<Packe_technology> pt = namedParameterJdbcTemplate.query(sql, sps, new BeanPropertyRowMapper<Packe_technology>(Packe_technology.class));
		if(pt != null && pt.size()>0){
			return pt.get(0);
		}
		return null;
	}
	
	//根据包号和工序名称修改包绑定工序表的工序状态
	public int updateTovoidOne(String packe_num, String t_name) {
		String sql = "update packe_technology set state=1 where packe_num=:packe_num and technology_name=:technology_name";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("packe_num", packe_num);
		sps.addValue("technology_name", t_name);
		int num = namedParameterJdbcTemplate.update(sql, sps);
		if(num >0){
			return num;
		}
		return 0;
	}
	
	//根据包号更改包记录的工序完成情况
	public int updateTovoidTwo(String packe_num) {
		String sql = "update process_dimensio set completed=completed-1,surplus=surplus+1 where packe_num=:packe_num";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("packe_num", packe_num);
		return namedParameterJdbcTemplate.update(sql, sps);
	}
	public int batchupdateStart(String sql3) {
		String sql = sql3;
		MapSqlParameterSource sps = new MapSqlParameterSource();
		int num = namedParameterJdbcTemplate.update(sql, sps);
		if(num >0){
			return num;
		}
		return 0;
	}
	public List<Packe_technology> findByPnum(String plan_num1) {
		String sql = "select * from packe_technology where p_num=:p_num";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("p_num", plan_num1);
		List<Packe_technology> pt = namedParameterJdbcTemplate.query(sql, sps, new BeanPropertyRowMapper<Packe_technology>(Packe_technology.class));
		if(pt != null && pt.size()>0){
			return pt;
		}
		return null;
	}
	public int findByPnumCount(String plan_num1) {
		String sql="select count(*) from packe_technology where p_num=:p_num";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("p_num", plan_num1);
		int pt = namedParameterJdbcTemplate.queryForInt(sql, sps);
		return pt;
	}
	//根据订单号 和包号获取 包绑定工序表的 id
	public Packe_technology getId(String packe_num, String t_name) {
		String sql = "select * from packe_technology where packe_num=:packe_num and technology_name=:technology_name";
		MapSqlParameterSource sps= new MapSqlParameterSource();
		sps.addValue("packe_num", packe_num);
		sps.addValue("technology_name", t_name);
		List<Packe_technology> list = namedParameterJdbcTemplate.query(sql, sps,new BeanPropertyRowMapper<Packe_technology>(Packe_technology.class));
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	

}
