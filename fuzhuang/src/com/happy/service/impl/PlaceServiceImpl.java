package com.happy.service.impl;

import com.happy.dao.PlaceDao;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.happy.model.place;
import com.happy.model.place_technology;
import com.happy.service.PlaceService;

@Service("placeService")
public class PlaceServiceImpl implements PlaceService{
    @Resource
    private PlaceDao placeDao;
    
    
	public PlaceDao getPlaceDao() {
		return placeDao;
	}


	public void setPlaceDao(PlaceDao placeDao) {
		this.placeDao = placeDao;
	}

	/**
	 * 根据当前页和每页显示行数实现分页查询订单
	 * @return
	 */
	public List<place> getPlace(int page,int rows) {
		return placeDao.getPlace(page,rows);
	}
    
	/**
	 * 查询所有订单
	 * @return
	 */
	public List<place> getPlace() {
		return placeDao.getPlace();
	}


	public int add(place p) {
		return placeDao.add(p);
	}


	public List<place> findByCustomer(String st) {
		return placeDao.findByCustomer(st);
	}

	public List<place> findById(int id) {
		return placeDao.findById(id);
	}


	public int updateInfo(place p) {
		return placeDao.updateInfo(p);
	}


	public int delete(int id) {
		return placeDao.delete(id);
	}


	public place findByNumber(String planNum) {
		return placeDao.findByNumber(planNum);
	}

	public List<place> findDai() {
		return placeDao.findDai();
	}
	//根据当前页和每页条数查询所有待生产订单
	public List<place> findDai(int page,int rows){
		return placeDao.findDai(page, rows);
		
	}

	public int count() {
		return placeDao.count();
	}

	public int updatePlace_Speed(String plan_num, String place_speed) {
		return placeDao.updatePlace_Speed(plan_num, place_speed);
	}
	/**
	 * 根据订单号更新已添加到包的数量
	 */
	public int  updateResidue(String  plan_num,int residue){
		return placeDao.updateResidue(plan_num, residue);
	}


	public int batchDelete(String sql1) {
		return placeDao.batchDelete(sql1);
	}

	
	public List<place> combGetPlaceList(String sqlx) {
		return placeDao.combGetPlaceList(sqlx);
	}


	public List<place> combGetPlaceList(String sqlx, int page, int rows) {
		return placeDao.combGetPlaceList(sqlx,page,rows);
	}


	public List<place> getAllpnum() {
		return placeDao.getAllpnum();
	}
}
