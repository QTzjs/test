package com.happy.model;
/**
 * 提交记录实体类
 * @author zjs
 *
 */
public class Sub {
	private int id;
	private String place_num;//订单号
	private String packe_num;//包号
	private String t_name;//提交的工序名称
	private String job_number;//提交人工号
	private String user_name;//提交人姓名
	private String sub_time;//提交时间
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
	
}
