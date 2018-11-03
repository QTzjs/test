package com.happy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.happy.dao.CodeDao;
import com.happy.model.Packe_technology;
import com.happy.model.place_technology;
import com.happy.model.process_dimensio;
import com.happy.service.CodeService;
/**
 * 分包业务实现层
 * @author zjs
 *
 */
@Service("codeService")
public class CodeServiceImpl implements CodeService {
	@Resource
	private CodeDao codeDao;
	public CodeDao getCodeDao() {
		return codeDao;
	}
	public void setCodeDao(CodeDao codeDao) {
		this.codeDao = codeDao;
	}
	public List<process_dimensio> findByNumber(String planNum) {
		return codeDao.findByNumber(planNum);
	}
	public List<process_dimensio> findByNumber(String planNum,int page,int rows){
		return codeDao.findByNumber(planNum, page, rows);
	}
	public int addPack(process_dimensio process_dimensio) {
		return codeDao.addPack(process_dimensio);
	}
	public process_dimensio findByPack(String packe_num) {
		return codeDao.findByPack(packe_num);
	}
	public int dele(int id) {
		return codeDao.dele(id);
	}
	public int updatePd(String packe_num) {
		return codeDao.updatePd(packe_num);
	}
	public int batchAddPack(String sql) {
		return codeDao.batchAddPack(sql);
	}
	public int updatePd(process_dimensio pd) {
		return codeDao.updatePd(pd);
	}
	public int deletePacke_tAndP_d(String sql1) {
		return codeDao.deletePacke_tAndP_d(sql1);
	}
	public int findPackNum(String pack_num) {
		return codeDao.findPackNum(pack_num);
	}
	public int findGongXu(String pack_num) {
		return codeDao.findGongXu(pack_num);
	}
	public process_dimensio findByPlace(String p_num) {
		return codeDao.findByPlace(p_num);
	}
	

}
