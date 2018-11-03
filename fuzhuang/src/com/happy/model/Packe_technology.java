package com.happy.model;
/**
 * 包绑定工序实体类
 * @author zjs
 *
 */
public class Packe_technology {
	private int id;
	private String packe_num;//包号
	private String technology_name;//工序名称
	private int state;//工序完成状态  1表示未完成  2表示已完成
	private String p_num;//订单编号
	
	public String getP_num() {
		return p_num;
	}
	public void setP_num(String p_num) {
		this.p_num = p_num;
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
	public String getPacke_num() {
		return packe_num;
	}
	public void setPacke_num(String packe_num) {
		this.packe_num = packe_num;
	}
	public String getTechnology_name() {
		return technology_name;
	}
	public void setTechnology_name(String technology_name) {
		this.technology_name = technology_name;
	}
	
}
