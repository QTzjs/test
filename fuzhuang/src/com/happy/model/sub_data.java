package com.happy.model;

import java.math.BigDecimal;

/**
 * 提交记录实体类
 * @author zjs
 *
 */
public class sub_data {
	private int id;
	private String place_num;//订单号
	private String packe_num;//包号
	private int number;//包数量
	private String t_name;//提交的工序名称
	private String job_number;//提交人工号
	private String user_name;//提交人姓名
	private String sub_time;//提交时间
	private int fake;//
	private int state;//为1是正常工单    为2是作废工单
	private BigDecimal price;// 为订单绑定工序时的单价
	private BigDecimal money;
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getFake() {
		return fake;
	}
	public void setFake(int fake) {
		this.fake = fake;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPlace_num() {
		return place_num;
	}
	public void setPlace_num(String place_num) {
		this.place_num = place_num;
	}
	public String getPacke_num() {
		return packe_num;
	}
	public void setPacke_num(String packe_num) {
		this.packe_num = packe_num;
	}
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
	public String getJob_number() {
		return job_number;
	}
	public void setJob_number(String job_number) {
		this.job_number = job_number;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getSub_time() {
		return sub_time;
	}
	public void setSub_time(String sub_time) {
		this.sub_time = sub_time;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	
}
