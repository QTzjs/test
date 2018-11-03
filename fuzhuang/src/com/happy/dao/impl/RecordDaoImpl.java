package com.happy.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.happy.dao.RecordDao;
import com.happy.model.Admin;
import com.happy.model.Record;
import com.happy.model.place;
@Repository("RecordDao")
public class RecordDaoImpl implements RecordDao {
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}

	public void setNamedParameterJdbcTemplate(
			NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
    //查询所有操作记录
	public List<Record> getAllList() {
		String sql = "select * from record ";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		List<Record> list = namedParameterJdbcTemplate.query(sql, sps, new BeanPropertyRowMapper<Record>(Record.class));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
    //分页显示操作记录
	public List<Record> getAllList(int page, int rows) {
		int start = (page-1)*rows;//每页的起始下标
		String sql="select * from record order by id desc limit :start,:rows ";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("start",start);
		sps.addValue("rows",rows);
		List<Record> list = namedParameterJdbcTemplate.query(sql, sps,
				new BeanPropertyRowMapper<Record>(Record.class));
		if(list!=null && list.size() > 0){
			return list;
		}
		return null;
	}
    //组合查询
	public List<Record> combGetRecordList(String sqlx) {
		String sql="select * from record "+sqlx;
		MapSqlParameterSource sps = new MapSqlParameterSource();
		List<Record> list = namedParameterJdbcTemplate.query(sql, sps,new BeanPropertyRowMapper<Record>(Record.class));
		if(list!=null && list.size() > 0){
			return list;
		}
		return null;
	}
    //组合查询
	public List<Record> combGetRecordList(String sqlx, int page, int rows) {
		int start = (page-1)*rows;//每页的起始下标
		String sql="select * from record "+sqlx +" limit :start,:rows";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("start", start);
		sps.addValue("rows", rows);
		List<Record> list = namedParameterJdbcTemplate.query(sql, sps,new BeanPropertyRowMapper<Record>(Record.class));
		if(list!=null && list.size() > 0){
			return list;
		}
		return null;
	}
	//增加记录
	public int add(Record r) {
		String sql="INSERT INTO `record` (`operation`, `people`, `date`, `ufrom`, `record1`, `opid`, `fake`) VALUES (:operation,:people,:date,:ufrom,:record1,:opid,:fake)";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("operation", r.getOperation());
		sps.addValue("people",r.getPeople());
		sps.addValue("date", r.getDate());
		sps.addValue("ufrom", r.getUfrom());
		sps.addValue("record1", r.getRecord1());
		sps.addValue("opid", r.getOpid());
		sps.addValue("fake", 0);
		KeyHolder keyholder = new GeneratedKeyHolder();
		namedParameterJdbcTemplate.update(sql, sps, keyholder);
		// 加上KeyHolder这个参数可以得到添加后主键的值
		int m = keyholder.getKey().intValue();
		return m;
	}
	
}
