package com.happy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.happy.dao.StyleDao;
import com.happy.model.size_color;
import com.happy.model.style;
import com.happy.service.StyleService;
@Service("styleService")
public class StyleServiceImpl implements StyleService {
	@Resource
	private StyleDao styleDao;
	public StyleDao getStyleDao() {
		return styleDao;
	}
	public void setStyleDao(StyleDao styleDao) {
		this.styleDao = styleDao;
	}
	//获取款式列表
	public List<style> getStyleList() {
		return styleDao.getStyleList();
	}
	/**
	 * 根据当前页和每页条数获取款式列表
	 * @return
	 */
	public List<style> getStyleList(int page,int rows){
		return styleDao.getStyleList(page, rows);
	}
	//添加款式
	public int addStyle(String string) {
		return styleDao.addStyle(string);
	}
	//更具id查询款式
	public style findByid(int id) {
		return styleDao.findByid(id);
	}
	//编辑款式
	public int updateStyle(style st) {
		return styleDao.updateStyle(st);
	}
	//删除款式
	public int deleteStyle(int parseInt) {
		return styleDao.deleteStyle(parseInt);
	}
	// 批量删除款式
	public int batchdeleteStyle(String sqlx){
		return styleDao.batchdeleteStyle(sqlx);
	}
	//组合条件查询
	public List<style> combGetStyleList(String sqlx) {
		return styleDao.combGetStyleList(sqlx);
	}
	//组合条件分页查询
	public List<style> combGetStyleList(String sqlx, int page, int rows) {
		return styleDao.combGetStyleList(sqlx,page,rows);
	}

}
