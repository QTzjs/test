package com.happy.dao;

import java.math.BigDecimal;
import java.util.List;

import com.happy.model.place_technology;

public interface Place_tDao {
	/**
	 * 根据 订单号 查询 该订单的所有绑定的工序
	 * @param place
	 * @return
	 */
	List<place_technology> findByNum(String place);
	/**
	 * 新增数据
	 * @param pt
	 * @return
	 */
	int add_gong(place_technology pt);
	/**
	 * 根据id删除订单绑定的工序
	 * @param parseInt
	 * @return
	 */
	int delete_gong(int parseInt);
	/**
	 * 根据id批量删除订单绑定的工序
	 * @param sql
	 * @return
	 */
	int batchdelete_gong(String sql);
	/**
	 * 根据订单号分页查询该订单绑定的工序
	 * @param place
	 * @param page
	 * @param rows
	 * @return
	 */
	List<place_technology> findByNum(String place, int page, int rows);
	/**
	 * 通过订单号和工序名称查询 订单绑定工序表 的工序单价
	 * @param place_num
	 * @param technology_name
	 * @return
	 */
	BigDecimal findByPandT(String place_num, String technology_name);
	/**
	 * 根据订单号查询订单绑定的工序
	 * @param place_num
	 * @return
	 */
	List<place_technology> getAllpt(String place_num);

}
