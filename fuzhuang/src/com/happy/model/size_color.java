package com.happy.model;
/**
 * 款式尺寸和颜色实体
 * @author QT-001
 *
 */
public class size_color {
	private int id;
	private int k_id;//款式id
	private String size;//尺寸
	private String color;//颜色
	private int fake;
	public int getFake() {
		return fake;
	}
	public void setFake(int fake) {
		this.fake = fake;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getK_id() {
		return k_id;
	}
	public void setK_id(int k_id) {
		this.k_id = k_id;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	

}
