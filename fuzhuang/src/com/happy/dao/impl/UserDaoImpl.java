package com.happy.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.happy.dao.UserDao;
import com.happy.model.weixin.User;
@Repository("userDao")
public class UserDaoImpl implements UserDao {
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}
	public void setNamedParameterJdbcTemplate(
			NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	//通过openid查询员工
	public User findByOpenid(String oPEN_ID) {
		String sql = "select * from user where open_id=:open_id and fake=0";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("open_id", oPEN_ID);
		List<User> list = namedParameterJdbcTemplate.query(sql, sps,new BeanPropertyRowMapper<User>(User.class));
		if(list != null && list.size() > 0){
			return list.get(0);
		}
	
		return null;
	}
	//通过手机号和工号查询员工
	public User findByAccountAndJob(User user) {
		String sql = "select * from user where user_account=:user_account and job_number=:job_number and fake=0";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("user_account", user.getUser_account());
		sps.addValue("job_number", user.getJob_number());
		List<User> list = namedParameterJdbcTemplate.query(sql, sps,new BeanPropertyRowMapper<User>(User.class));
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	//更新数据
	public void update(User u) {
		String sql = "update user set open_id=:open_id where id=:id";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("open_id", u.getOpen_id());
		sps.addValue("id", u.getId());
		namedParameterJdbcTemplate.update(sql, sps);
	}
	//查询所有员工
	public List<User> getlist() {
		String sql = "select * from user where fake=0";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("fake",0);
		List<User> list = namedParameterJdbcTemplate.query(sql, sps,new BeanPropertyRowMapper<User>(User.class));
		return list;
	}
	//根据当前页和每页条数查询所有员工
	public List<User> getlist(int page,int rows){
		int start = (page-1)*rows;//每页的起始下标
		String sql = "select * from user where fake=0 limit :start,:rows ";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("start",start);
		sps.addValue("rows",rows);
		List<User> list = namedParameterJdbcTemplate.query(sql, sps,new BeanPropertyRowMapper<User>(User.class));
		return list;
	}
	public int add_user(User u) {
		String sql="insert into user (user_name,user_account,password,user_level,job_number,open_id,user_time,fake) "
							+ "values(:user_name,:user_account,:password,:user_level,:job_number,:open_id,:user_time,:fake)";
		
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("user_name",u.getUser_name());
		sps.addValue("user_account",u.getUser_account());
		sps.addValue("password",u.getPassword());
		sps.addValue("user_level",u.getUser_level());
		sps.addValue("job_number",u.getJob_number());
		sps.addValue("open_id",0);
		sps.addValue("user_time",u.getUser_time());
		sps.addValue("fake",0);
		/*KeyHolder keyholder = new GeneratedKeyHolder();*/
		/*namedParameterJdbcTemplate.update(sql, sps, keyholder);*/
		// 加上KeyHolder这个参数可以得到添加后主键的值
//		int m = keyholder.getKey().intValue();
		int m = namedParameterJdbcTemplate.update(sql, sps);
		if(m>0){
			return m;
		}
		return 0;
	}
	public int updateuser(User u) {
		String sql = "update user set user_name=:user_name,user_account=:user_account,password=:password,user_level=:user_level,user_time=:user_time where id=:id";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("id",u.getId());
		sps.addValue("user_name",u.getUser_name());
		sps.addValue("user_account",u.getUser_account());
		sps.addValue("password",u.getPassword());
		sps.addValue("user_level",u.getUser_level());
		sps.addValue("user_time",u.getUser_time());
		int num=namedParameterJdbcTemplate.update(sql, sps);
		return num;
	}
	public List<User> findById(int id) {
		String sql = "select * from user where id=:id";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("id",id);
		List<User> list = namedParameterJdbcTemplate.query(sql, sps,new BeanPropertyRowMapper<User>(User.class));
		if(list != null && list.size() >0 ){
			return list;
		}
		return null;
	}
	public int delete(int id) {
		String sql = "update user set fake=1 where id=:id";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("id", id);
		return namedParameterJdbcTemplate.update(sql, sps);
	}
	//批量删除
	public int bathDelete(String sqlx){
		String sql = sqlx;
		MapSqlParameterSource sps = new MapSqlParameterSource();
		int num=namedParameterJdbcTemplate.update(sql, sps);
		if(num>0){
			return num;
		}
		return 0;
	}
	//组合条件查询
	public List<User> combGetUserList(String sqlx) {
		String sql = "select * from user "+sqlx;
		MapSqlParameterSource sps = new MapSqlParameterSource();
		List<User> list = namedParameterJdbcTemplate.query(sql, sps,new BeanPropertyRowMapper<User>(User.class));
		if(list != null && list.size() >0 ){
			return list;
		}
		return null;
	}
	//组合条件分页查询
	public List<User> combGetUserList(String sqlx, int page, int rows) {
		int start = (page-1)*rows;//每页的起始下标
		String sql = "select * from user "+sqlx+" limit :start,:rows";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("start",start);
		sps.addValue("rows",rows);
		List<User> list = namedParameterJdbcTemplate.query(sql, sps,new BeanPropertyRowMapper<User>(User.class));
		if(list != null && list.size() >0 ){
			return list;
		}
		return null;
	}
}
