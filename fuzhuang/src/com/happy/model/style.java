package com.happy.model;
/**
 * 
 * @author QT-001
 * 款式
 */
public class style {
   private int id;
   private String style_name;		//款式名字
   private int fake; 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStyle_name() {
		return style_name;
	}
	public void setStyle_name(String style_name) {
		this.style_name = style_name;
	}
	public int getFake() {
		return fake;
	}
	public void setFake(int fake) {
		this.fake = fake;
	}

   
}
