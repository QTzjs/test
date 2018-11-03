package com.happy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.happy.dao.RecordDao;
import com.happy.model.Record;
import com.happy.service.RecordService;
@Service("RecordService")
public class RecordServiceImpl implements RecordService {
	@Resource
	private RecordDao recordDao;
    public void setRecordDao(RecordDao recordDao) {
		this.recordDao = recordDao;
	}
	//查询所有操作记录
	public List<Record> getAllList() {
		
		return recordDao.getAllList();
	}
	//分页查询所有操作记录
	public List<Record> getAllList(int page, int rows) {

		return recordDao.getAllList(page, rows);
	}
	//组合查询操作记录
	public List<Record> combGetRecordList(String sqlx){
		return recordDao.combGetRecordList(sqlx);
	}
	//分页组合查询操作记录
	public List<Record> combGetRecordList(String sqlx, int page, int rows){
		return recordDao.combGetRecordList(sqlx, page, rows);
	}
	//添加记录
	public int add(Record r) {
		return recordDao.add(r);
	}
}
