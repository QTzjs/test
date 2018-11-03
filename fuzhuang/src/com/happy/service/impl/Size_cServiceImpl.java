package com.happy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.happy.dao.Size_cDao;
import com.happy.model.size_color;
import com.happy.service.Size_cService;
@Service("size_cService")
public class Size_cServiceImpl implements Size_cService {
	@Resource
	private Size_cDao size_cDao;
	public Size_cDao getSize_cDao() {
		return size_cDao;
	}
	public void setSize_cDao(Size_cDao size_cDao) {
		this.size_cDao = size_cDao;
	}
	public List<size_color> getStyleC(int id) {
		return size_cDao.getStyleC(id);
	}
	public List<size_color> getStyleC(int id, int page, int rows) {
		return size_cDao.getStyleC(id,page,rows);
	}
	public int addStyleC(int i, String string2, String string3) {
		return size_cDao.addStyleC(i,string2,string3);
	}
	public size_color findByCid(int id) {
		return size_cDao.findByCid(id);
	}
	public int updateStyleC(size_color sc) {
		return size_cDao.updateStyleC(sc);
	}
	public int deleteStyleC(int parseInt) {
		return size_cDao.deleteStyleC(parseInt);
	}
	public int batchdeleteStyleC(String sqlx) {
		return size_cDao.batchdeleteStyleC(sqlx);
	}
	
}
