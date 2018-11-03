package com.happy.service;

import java.util.List;

import com.happy.model.Packe_technology;
import com.happy.model.place_technology;
import com.happy.model.process_dimensio;

/**
 * 分包业务层
 * @author zjs
 *
 */
public interface CodeService {
	
	/**
	 * 根据订单号查询包记录
	 * @param planNum
	 * @return
	 */
	List<process_dimensio> findByNumber(String planNum);
	
	/**
	 * 根据订单号当前页和每页条数查询包记录
	 * @param planNum
	 * @return
	 */
	List<process_dimensio> findByNumber(String planNum,int page,int rows);
	/**
	 * 添加新的包
	 * @param process_dimensio
	 * @return 
	 */
	int addPack(process_dimensio process_dimensio);
	
	/**
	 * 根据包号查询包记录
	 * @param packe_num
	 * @return
	 */
	process_dimensio findByPack(String packe_num);
	/**
	 * 删除信息
	 */
	int dele(int id);
	/**
	 * 改变记录
	 * @param packe_num
	 * @return
	 */
	int updatePd(String packe_num);
	/**
	 * 批量添加包
	 * @param sql
	 * @return
	 */
	int batchAddPack(String sql);
	/**
	 * 编辑包信息
	 * @param pd
	 * @return
	 */
	int updatePd(process_dimensio pd);
	/**
	 * 根据包号批量多表删除  包表(process_dimensio)和包邦定工序表(packe_technology) 
	 * @param sql1
	 * @return
	 */
	public int deletePacke_tAndP_d(String sql1);
    /**
     * 根据订单号查询总包数
     */
	public int findPackNum(String pack_num);
	/**
	 * 根据订单号查询包中工序总数
	 */
	public int findGongXu(String pack_num);
	/**
	 * 查询该订单号在包表中的最大包号
	 * @param p_num
	 * @return
	 */
	process_dimensio findByPlace(String p_num);	
	
}
