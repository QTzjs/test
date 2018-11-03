package com.happy.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.happy.dao.ToKenDao;
import com.happy.model.weixin.ToKen;
@Repository("tokenDao")
public class ToKenDaoImpl implements ToKenDao {
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}
	public void setNamedParameterJdbcTemplate(
			NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public List<ToKen> findToken() {
		String sql = "select * from token";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		List<ToKen> userList = namedParameterJdbcTemplate.query(sql, sps,
				new BeanPropertyRowMapper<ToKen>(ToKen.class));
		return userList;
	}

	public int addToken(ToKen token) {
		String sql = "insert into token (access_token,ticket,end_time) values(:access_token,:ticket,:end_time)";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("access_token", token.getAccess_token());
		sps.addValue("ticket", token.getTicket());
		sps.addValue("end_time", token.getEnd_time());
		int num = namedParameterJdbcTemplate.update(sql, sps);
		return num;
	}

	public int updateToken(ToKen token) {
		String sql =  "update token set access_token=:access_token,ticket=:ticket,end_time=:end_time where token_id = :token_id";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("access_token", token.getAccess_token());
		sps.addValue("ticket", token.getTicket());
		sps.addValue("end_time", token.getEnd_time());
		sps.addValue("token_id", token.getToken_id());
		int num = namedParameterJdbcTemplate.update(sql, sps);
		return num;
	}

	public void deleteToken(ToKen token) {
		String sql = "delete from token where token_id=:token_id";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("token_id", token.getToken_id());
		namedParameterJdbcTemplate.update(sql, sps);
	}

}
