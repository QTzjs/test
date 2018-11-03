package com.happy.dao;

import java.util.List;

import com.happy.model.Packe_technology;

public interface Packe_tDao {
	/**
	 * 批量添加  包绑定工序表 的数据
	 * @param sql1
	 * @return
	 */
	int batchAddTechnology(String sql1);
	/**
	 * 添加 包绑定工序
	 * @param packe_technology
	 */
	void addTechnology(Packe_technology packe_technology);
	/**
	 * 根据包号查询这个包所绑定的所有工序
	 * @param packe_num
	 * @return
	 */
	List<Packe_technology> findByPackT(String packe_num);
	/**
	 * 已完成工序列表集合
	 * @param packe_num
	 * @return
	 */
	List<Packe_technology> findByPackTcom(String packe_num);
	/**
	 * 未完成工序列表集合
	 * @param packe_num
	 * @return
	 */
	List<Packe_technology> findByPackTyet(String packe_num);
	/**
	 * 改变包绑定工序表的 工序状态为2已完成
	 * @param id
	 * @param state 
	 * @return
	 */
	int updatePt(int id, int state);
	/**
	 * id查询包邦定工序记录
	 * @param id
	 * @return
	 */
	Packe_technology findbByPt(int id);
	/**
	 * 根据包号和工序名称修改 包绑定工序表 的工序状态
	 * @param packe_num
	 * @param t_name
	 * @return
	 */
	int updateTovoidOne(String packe_num, String t_name);
	/**
	 * 根据包号更改包记录的工序完成情况
	 * @param packe_num
	 * @return
	 */
	int updateTovoidTwo(String packe_num);
	/**
	 * 批量修改 包绑定工序表的工序状态
	 * @param sql3
	 * @return
	 */
	int batchupdateStart(String sql3);
	/**
	 * 根据订单号 查询 包邦定工序表 的数据
	 * @param plan_num1
	 * @return
	 */
	List<Packe_technology> findByPnum(String plan_num1);
	/**
	 * 查询这个订单号的所有包绑定工序 数据量
	 * @param plan_num1
	 * @return
	 */
	int findByPnumCount(String plan_num1);
	/**
	 * 根据订单号 和包号获取 包绑定工序表的 id
	 * @param packe_num
	 * @param t_name
	 * @return
	 */
	Packe_technology getId(String packe_num, String t_name);
	
}
