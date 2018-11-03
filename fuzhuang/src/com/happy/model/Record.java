package com.happy.model;
/**
 * 操作记录实体类
 * @author 
 *
 */
public class Record {
     private int id;
     private int fake;
     private String operation;//具体操作类型
     private String date;//日期
     private String ufrom;//从哪里进行操作
     private String record1;//说明
     private String opid;//操作id
     private String people;//操作人
   
	public int getFake() {
		return fake;
	}
	public void setFake(int fake) {
		this.fake = fake;
	}
	public String getUfrom() {
		return ufrom;
	}
	public void setUfrom(String ufrom) {
		this.ufrom = ufrom;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getPeople() {
		return people;
	}
	public void setPeople(String people) {
		this.people = people;
	}
	public String getRecord1() {
		return record1;
	}
	public void setRecord1(String record1) {
		this.record1 = record1;
	}
	public String getOpid() {
		return opid;
	}
	public void setOpid(String opid) {
		this.opid = opid;
	}
     
	
}
