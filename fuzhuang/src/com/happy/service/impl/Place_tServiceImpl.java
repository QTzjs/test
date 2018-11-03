package com.happy.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.happy.dao.Place_tDao;
import com.happy.model.place_technology;
import com.happy.service.Place_tService;
@Service("place_tService")
public class Place_tServiceImpl implements Place_tService {
	@Resource
	private Place_tDao place_tDao;
	public Place_tDao getPlace_tDao() {
		return place_tDao;
	}
	public void setPlace_tDao(Place_tDao place_tDao) {
		this.place_tDao = place_tDao;
	}
	
	public List<place_technology> findByNum(String place) {
		return place_tDao.findByNum(place);
	}
	public int add_gong(place_technology pt) {
		return place_tDao.add_gong(pt);
	}
	public int delete_gong(int parseInt) {
		return place_tDao.delete_gong(parseInt);
	}
	public int batchdelete_gong(String sql) {
		return place_tDao.batchdelete_gong(sql);
	}
	public List<place_technology> findByNum(String place, int page, int rows) {
		return place_tDao.findByNum(place,page,rows);
	}
	public BigDecimal findByPandT(String place_num, String technology_name) {
		return place_tDao.findByPandT(place_num,technology_name);
	}
	public List<place_technology> getAllpt(String place_num) {
		return place_tDao.getAllpt(place_num);
	}
	
}
