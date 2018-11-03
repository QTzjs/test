package com.happy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.happy.dao.sub_dataDao;
import com.happy.model.Export;
import com.happy.model.ProductionPiecework;
import com.happy.model.sub_data;
import com.happy.service.sub_dataService;

@Service("sub_dataService")
public class sub_dataServiceImpl implements sub_dataService {
	@Resource
	private sub_dataDao subDao;
	public sub_dataDao getSubDao() {
		return subDao;
	}
	public void setSubDao(sub_dataDao subDao) {
		this.subDao = subDao;
	}
	public List<sub_data> list(String job, String time) {
		
		return subDao.list(job, time);
	}
	public int sub(sub_data sd) {
		return subDao.sub(sd);
	}
	public List<sub_data> findlist(String place_num) {
		
		return subDao.findlist(place_num);
	}
	/**
	 * 根据订单号查询已经提交数据的所有人
	 * @return 
	 * @return 
	 */
	public int findlistPeople(String place_num){
		return subDao.findlistPeople(place_num);
	}

	/**
	 * 根据订单号和当前页和每页条数查询已经提交数据
	 */
	public List<sub_data> findlist(String place_num,int page ,int rows){
		return subDao.findlist(place_num, page, rows);
	}
	public sub_data findById(int id) {
		return subDao.findById(id);
	}
	public int updateById(int id) {
		return subDao.updateById(id);
	}
	public int delete(int id) {
		return subDao.delete(id);
	}
	/**
	 * 批量删除工单
	 */
	public int batchDelete(String sqlx){
		return subDao.batchDelete(sqlx);
	}
	public List<sub_data> findByjob(String job_number) {
		return subDao.findByjob(job_number);
	}
	
	
	/**
	 * 根据员工号 当前页 每页条数查询员工提交的工单数据
	 * @param job_number
	 * @return
	 */
	public List<sub_data> findByjob(String job_number,int page,int rows){
		return subDao.findByjob(job_number, page, rows);
	}
	public List<sub_data> combinatoriaQuery(String advanceFilter, String job_number) {
		return subDao.combinatoriaQuery(advanceFilter,job_number);
	}
	public List<sub_data> combinatoriaQuery(String advanceFilter, String job_number, int page,
			int rows) {
		return subDao.combinatoriaQuery(advanceFilter,job_number,page,rows);
	}
	public List<Export> exportFind(String job_number) {
		return subDao.exportFind(job_number);
	}
	public List<sub_data> queryList(String zd, String tj, String zhi,
			String place_num, int page, int rows) {
		
		return subDao.queryList(zd, tj, zhi, place_num, page, rows);
	}
	public List<sub_data> combGetsubList(String sql, String place_num) {
		return subDao.combGetsubList(sql,place_num);
	}
	public List<sub_data> combGetsubList(String sql, String place_num,
			int page, int rows) {
		return subDao.combGetsubList(sql,place_num,page,rows);
	}
	public int findlistCount(String plan_num1) {
		return subDao.findlistCount(plan_num1);
	}
	public sub_data findByPnPkT(String place_num, String packe_num,
			String technology_name) {
		return subDao.findByPnPkT(place_num,packe_num,technology_name);
	}
	public int findfinish(String place_num) {
		
		return subDao.findfinish(place_num);
	}
	public List<sub_data> findByPk(String packe_num) {
		return subDao.findByPk(packe_num);
	}
	public List<Export> exportFind(String sql, String job_number) {
		return subDao.exportFind(sql,job_number);
	}
	public List<ProductionPiecework> findAll() {
		return subDao.findAll();
	}
	public List<ProductionPiecework> findAll(String sql) {
		return subDao.findAll(sql);
	}
	
}
