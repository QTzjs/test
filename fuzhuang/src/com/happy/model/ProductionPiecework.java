package com.happy.model;

import java.math.BigDecimal;
/**
 * 员工生产计件报表实体类
 * @author zjs
 *
 */
public class ProductionPiecework {
	private String user_name;
	private String place_num;
	private String t_name;
	private String style;
	private int number;
	private double price;// 为订单绑定工序时的单价
	private double money;
	private String job_number;
	
	
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPlace_num() {
		return place_num;
	}
	public void setPlace_num(String place_num) {
		this.place_num = place_num;
	}
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getJob_number() {
		return job_number;
	}
	public void setJob_number(String job_number) {
		this.job_number = job_number;
	}
	
}
