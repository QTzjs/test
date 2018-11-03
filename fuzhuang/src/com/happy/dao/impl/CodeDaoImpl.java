package com.happy.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.happy.dao.CodeDao;
import com.happy.model.Packe_technology;
import com.happy.model.place_technology;
import com.happy.model.process_dimensio;
@Repository("codeDao")
public class CodeDaoImpl implements CodeDao {
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}
	public void setNamedParameterJdbcTemplate(
			NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	//根据订单号查询包记录
	public List<process_dimensio> findByNumber(String planNum) {
		String sql = "select * from process_dimensio where fake=0 and p_num=:p_num order by `packe_num` asc";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("p_num", planNum);
		List<process_dimensio> list = namedParameterJdbcTemplate.query(sql, sps,new BeanPropertyRowMapper<process_dimensio>(process_dimensio.class));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	
	//根据订单号 当前页和每页条数查询包记录
	public List<process_dimensio> findByNumber(String planNum,int page,int rows) {
		int start = (page-1)*rows;//每页的起始下标
		String sql = "select * from process_dimensio where fake=0 and p_num=:p_num order by `packe_num` asc limit :start,:rows";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("p_num", planNum);
		sps.addValue("start",start);
		sps.addValue("rows",rows);
		List<process_dimensio> list = namedParameterJdbcTemplate.query(sql, sps,new BeanPropertyRowMapper<process_dimensio>(process_dimensio.class));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
		
	 //添加新的包
	public int addPack(process_dimensio process_dimensio) {
		String sql = "insert into process_dimensio (p_num,p_color,p_size,packe_num,p_number,cylinder,girard,production_number,bed,fake,completed,current,surplus) values(:p_num,:p_color,:p_size,:packe_num,:p_number,:cylinder,:girard,:production_number,:bed,:fake,:completed,:current,:surplus)";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("p_num", process_dimensio.getP_num());
		sps.addValue("p_color", process_dimensio.getP_color());
		sps.addValue("p_size", process_dimensio.getP_size());
		sps.addValue("packe_num", process_dimensio.getPacke_num());
		sps.addValue("p_number", process_dimensio.getP_number());
		sps.addValue("cylinder", process_dimensio.getCylinder());
		sps.addValue("girard", process_dimensio.getGirard());
		sps.addValue("production_number", "1");
		sps.addValue("bed",1);
		sps.addValue("fake",0);
		sps.addValue("completed", process_dimensio.getCompleted());
		sps.addValue("current", process_dimensio.getCurrent());
		sps.addValue("surplus", process_dimensio.getSurplus());
		int num = namedParameterJdbcTemplate.update(sql, sps);
		if( num > 0){
			return num;
		}
		return 0;
	}
	
	//根据包号查询包记录
	public process_dimensio findByPack(String packe_num) {
		String sql = "select * from process_dimensio where packe_num=:packe_num and fake=0";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("packe_num", packe_num);
		List<process_dimensio> list = namedParameterJdbcTemplate.query(sql, sps,new BeanPropertyRowMapper<process_dimensio>(process_dimensio.class));
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	public int dele(int id) {
		String sql = "update process_dimensio set fake=1 where id=:id";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("id", id);
		return namedParameterJdbcTemplate.update(sql, sps);
	}
	
	//改变包号表的已完成个数
	public int updatePd(String packe_num) {
		String sql = "update process_dimensio set completed=completed+1,surplus=surplus-1 where packe_num=:packe_num and fake=0";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("packe_num", packe_num);
		return namedParameterJdbcTemplate.update(sql, sps);
	}
	
	//批量操作包表
	public int batchAddPack(String sqlx) {
		String sql = sqlx;
		MapSqlParameterSource sps = new MapSqlParameterSource();
		int num = namedParameterJdbcTemplate.update(sql, sps);
		if(num > 0 ){
			return num;
		}
		return 0;
	}
	
	//编辑包信息
	public int updatePd(process_dimensio pd) {
		String sql = "update process_dimensio set p_color=:p_color,p_size=:p_size,p_number=:p_number,cylinder=:cylinder where packe_num=:packe_num";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("p_color", pd.getP_color());
		sps.addValue("p_size", pd.getP_size());
		sps.addValue("p_number", pd.getP_number());
		sps.addValue("cylinder", pd.getCylinder());
		sps.addValue("packe_num", pd.getPacke_num());
		int num = namedParameterJdbcTemplate.update(sql, sps);
		if(num >0 ){
			return num;
		}
		return 0;
	}
	
	//根据包号批量多表删除  包表(process_dimensio)和包邦定工序表(packe_technology) 
	public int deletePacke_tAndP_d(String sql1) {
		String sql = "DELETE a.*,b.* FROM process_dimensio a LEFT JOIN packe_technology b ON a.packe_num=b.packe_num WHERE a.packe_num in"+sql1;
		MapSqlParameterSource sps = new MapSqlParameterSource();
		int num = namedParameterJdbcTemplate.update(sql, sps);
		if(num>0){
			return num;
		}
		return 0;
	}
	// 根据订单号查询总包数
	public int findPackNum(String pack_num){
		String sql = "select COUNT(DISTINCT id) from process_dimensio where fake=0 and p_num=:p_num";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("p_num",pack_num);
		int n = namedParameterJdbcTemplate.queryForInt(sql, sps);
		return n;
	}
	// 根据订单号查询总工序
	public int findGongXu(String pack_num) {
		String sql="select sum(completed+surplus) from (select p.completed,p.surplus from process_dimensio p where fake=0 and p_num=:p_num limit 1) t";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("p_num",pack_num);
		int n = namedParameterJdbcTemplate.queryForInt(sql, sps);
		return n;
	}
	public process_dimensio findByPlace(String p_num) {
		String sql = "select packe_num from process_dimensio where fake=0 and p_num=:p_num and id=(SELECT max(id) FROM process_dimensio WHERE fake=0 and p_num=:p_num)";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("p_num", p_num);
		List<process_dimensio> list = namedParameterJdbcTemplate.query(sql, sps,new BeanPropertyRowMapper<process_dimensio>(process_dimensio.class));
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
}
