package com.happy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.happy.dao.technologyDao;
import com.happy.model.technology;
import com.happy.service.technologyService;

@Service("technologyService")
public class technologyServiceImpl implements technologyService{
   @Resource
   private technologyDao technologydao;
   
   
	public technologyDao getTechnologydao() {
	return technologydao;
}


    public void setTechnologydao(technologyDao technologydao) {
	this.technologydao = technologydao;
    }


	public int add_gong(int number,String name) {
		
		return technologydao.add_gong(number,name);
	}


	public List<technology> getList() {
		
		return technologydao.getList();
	}
	
	

	//根据当前页每页显示行数查询所有工序
    public List<technology> getList(int page,int rows){
			return technologydao.getList(page, rows);
	}
	//根据工序id查询工序详情信息
	public technology findById(int id) {
		return technologydao.findById(id);
	}
	
	
	//更新
	public int update(technology te) {
		return technologydao.update(te);
	}

	//删除
	public int delete(int parseInt) {
		return technologydao.delete(parseInt);
	}

	  //批量删除
    public int batchDelete(String sqlx) {	
	    return technologydao.batchDelete(sqlx);
	}


	public technology findByName(String string) {
		return technologydao.findByName(string);
	}


	public technology findBynumber(int parseInt) {
		return technologydao.findBynumber(parseInt);
	}

  
	
	 
	
	
}
