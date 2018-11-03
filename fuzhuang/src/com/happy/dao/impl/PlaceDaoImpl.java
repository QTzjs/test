package com.happy.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.happy.model.place;
import com.happy.model.place_technology;
import com.happy.dao.PlaceDao;

@Repository("placeDao")
public class PlaceDaoImpl implements PlaceDao{
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}
	public void setNamedParameterJdbcTemplate(
			NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	/**
	 * 根据当前页和每页显示行数实现分页查询订单
	 */
	public List<place> getPlace(int page,int rows) {
		//System.out.println("当前页"+page);// 当前页
		//System.out.println("每页显示的行数"+rows);//每页显示的行数
		//String sql="select * from place where fake=0 order by id desc limit "+(page-1)*rows+","+rows;
		int start = (page-1)*rows;//每页的起始下标
		String sql="select * from place where fake=0 order by id desc limit :start,:rows ";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("start",start);
		sps.addValue("rows",rows);
		List<place> list = namedParameterJdbcTemplate.query(sql, sps,
				new BeanPropertyRowMapper<place>(place.class));
		if(list!=null && list.size() > 0){
			return list;
		}
		return null;
		
	}
	/**
	 * 
	 */
	
	/**
	 * 查询所有订单
	 */
	public List<place> getPlace(){
		String sql="select * from place where fake=0 order by id asc";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		List<place> list = namedParameterJdbcTemplate.query(sql, sps,
				new BeanPropertyRowMapper<place>(place.class));
		if(list!=null && list.size() > 0){
			return list;
		}
		return null;
		
	}
	
	public int add(place p) {
		String sql="INSERT INTO `place` (`plan_num`, `style`, `style_num`, `number`, `material`, `cloth`, `distribution`, `fabric`, `technology`, `customer`, `delivery_time`, `place_time`, `state`, `input_name`, `input_time`, `place_speed`, `fake`) VALUES (:plan_num,:style,:style_num,:number,:material,:cloth,:distribution,:fabric,:technology,:customer,:delivery_time,:place_time,:state,:input_name,:input_time,:place_speed,:fake)";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("plan_num", p.getPlan_num());
		sps.addValue("style", p.getStyle());
		sps.addValue("style_num", p.getStyle_num());
		sps.addValue("number", p.getNumber());
		sps.addValue("material", p.getMaterial());
		sps.addValue("cloth", p.getCloth());
		sps.addValue("distribution", p.getDistribution());
		sps.addValue("fabric", p.getFabric());
		sps.addValue("technology", p.getTechnology());
		sps.addValue("customer", p.getCustomer());
		sps.addValue("delivery_time", p.getDelivery_time());
		sps.addValue("place_time", p.getPlace_time());
		sps.addValue("state", p.getState());
		sps.addValue("input_name", p.getInput_name());
		sps.addValue("input_time", p.getInput_time());
		sps.addValue("place_speed", p.getPlace_speed());
		sps.addValue("fake", 0);
		KeyHolder keyholder = new GeneratedKeyHolder();
		namedParameterJdbcTemplate.update(sql, sps, keyholder);
		// 加上KeyHolder这个参数可以得到添加后主键的值
		int m = keyholder.getKey().intValue();
		return m;
	}
	
	//模糊查询
	public List<place> findByCustomer(String st) {
		String sql="select * from place where customer LIKE '%"
				+ st + "%'";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		List<place> placeList = namedParameterJdbcTemplate.query(sql, sps,new BeanPropertyRowMapper<place>(place.class));
		if(placeList!=null && placeList.size() > 0){
			return placeList;
		}
		return null;
	}
	
	
	public List<place> findById(int id) {
		String sql="select * from place where id="+id;
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("id",id);
		List<place> list = namedParameterJdbcTemplate.query(sql, sps,
				new BeanPropertyRowMapper<place>(place.class));
		if(list!=null && list.size() > 0){
			return list;
		}
		return null;
	}
	//修改订单
	public int updateInfo(place p) {
		String sql = "update place set plan_num=:plan_num,style=:style,number=:number,material=:material,cloth=:cloth,distribution=:distribution,fabric=:fabric,technology=:technology,customer=:customer,delivery_time=:delivery_time,place_time=:place_time where id=:id";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("id", p.getId());
		sps.addValue("plan_num",p.getPlan_num());
		sps.addValue("style",p.getStyle());
		sps.addValue("number",p.getNumber());
		sps.addValue("material",p.getMaterial());
		sps.addValue("cloth", p.getCloth());
		sps.addValue("distribution",p.getDistribution());
		sps.addValue("fabric", p.getFabric());
		sps.addValue("technology",p.getTechnology());
		sps.addValue("customer", p.getCustomer());
		sps.addValue("delivery_time",p.getDelivery_time());
		sps.addValue("place_time", p.getPlace_time());
		return namedParameterJdbcTemplate.update(sql, sps);
	}
	//根据id删除订单
	public int delete(int id) {
		String sql = "update place set fake=1 where id=:id";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("id", id);
		return namedParameterJdbcTemplate.update(sql, sps);
	}
	
	//根据订单号查询订单记录
	public place findByNumber(String planNum) {
		String sql = "select * from place where plan_num=:plan_num";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("plan_num", planNum);
		List<place> order = namedParameterJdbcTemplate.query(sql, sps, new BeanPropertyRowMapper<place>(place.class));
		if(order != null&&order.size()>0){
			return order.get(0);
		}
		return null;
	}
	
	
	public List<place> findDai() {
		String sql="select * from place where state=1 and fake=0";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		List<place> list = namedParameterJdbcTemplate.query(sql, sps,
				new BeanPropertyRowMapper<place>(place.class));
		if(list!=null && list.size() > 0){
			return list;
		}
		return null;
	}
	
	// 根据当前页和每页条数查询所有待生产订单
	public List<place> findDai(int page,int rows){
		int start = (page-1)*rows;//每页的起始下标
		String sql="select * from place where state=1 and fake=0 limit :start,:rows ";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("start",start);
		sps.addValue("rows",rows);
		List<place> list = namedParameterJdbcTemplate.query(sql, sps,
				new BeanPropertyRowMapper<place>(place.class));
		if(list!=null && list.size() > 0){
			return list;
		}
		return null;
	}
	
	public int count() {
		String sql="select * from place where fake=0";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		List<place> list = namedParameterJdbcTemplate.query(sql, sps,new BeanPropertyRowMapper<place>(place.class));
		return list.size();
	}
	/**
	 * 
	 * 根据订单号更新完成率
	 */
	public int updatePlace_Speed(String plan_num,String place_speed) {
		String sql = "update place set place_speed=:place_speed where plan_num=:plan_num";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("plan_num",plan_num);
		sps.addValue("place_speed", place_speed);
		int num=namedParameterJdbcTemplate.update(sql, sps);
		return num;
		
	}
	/**
	 * 根据订单号更新已添加到包的数量
	 */
	public int  updateResidue(String  plan_num,int residue){
		String sql = "update place set residue=residue+"+residue+" where  plan_num=:plan_num";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("plan_num",plan_num);
		sps.addValue("residue", residue);
		int num=namedParameterJdbcTemplate.update(sql, sps);
		return num;
	}
	//批量关联删除5张表
	public int batchDelete(String sql1) {
		String sql = sql1;
		MapSqlParameterSource sps = new MapSqlParameterSource();
		int num=namedParameterJdbcTemplate.update(sql, sps);
		return num;
	}
	//组合查询
	public List<place> combGetPlaceList(String sqlx) {
		String sql="select * from place "+sqlx;
		MapSqlParameterSource sps = new MapSqlParameterSource();
		List<place> list = namedParameterJdbcTemplate.query(sql, sps,new BeanPropertyRowMapper<place>(place.class));
		if(list!=null && list.size() > 0){
			return list;
		}
		return null;
	}
	//组合查询
	public List<place> combGetPlaceList(String sqlx, int page, int rows) {
		int start = (page-1)*rows;//每页的起始下标
		String sql="select * from place "+sqlx +" limit :start,:rows";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		sps.addValue("start", start);
		sps.addValue("rows", rows);
		List<place> list = namedParameterJdbcTemplate.query(sql, sps,new BeanPropertyRowMapper<place>(place.class));
		if(list!=null && list.size() > 0){
			return list;
		}
		return null;
	}
	//查询出所有订单的订单号
	public List<place> getAllpnum() {
		String sql = "select plan_num from place where state=1 and fake=0";
		MapSqlParameterSource sps = new MapSqlParameterSource();
		List<place> list = namedParameterJdbcTemplate.query(sql, sps,new BeanPropertyRowMapper<place>(place.class));
		if(list!=null && list.size() > 0){
			return list;
		}
		return null;
	}
	
}
