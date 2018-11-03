package com.happy.model;

import java.math.BigDecimal;

/**
 * 
 * @author Administrator
 * 订单绑定工序
 */
public class place_technology {
    private int id;
    private String place_number;//订单号
    private String technology_name;//工序名称
    private BigDecimal price;//征用此预留字段  为订单绑定工序时的单价
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getPlace_number() {
		return place_number;
	}
	public void setPlace_number(String place_number) {
		this.place_number = place_number;
	}
	public String getTechnology_name() {
		return technology_name;
	}
	public void setTechnology_name(String technology_name) {
		this.technology_name = technology_name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
}
	