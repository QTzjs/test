package com.happy.model;
/**
 * 
 * @author QT-001
 * 工序实体
 */
public class technology {
    private int id;
    private int number;//工序编号
    private String name;//工序名称
    private int fake;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getFake() {
		return fake;
	}
	public void setFake(int fake) {
		this.fake = fake;
	}
    
}
