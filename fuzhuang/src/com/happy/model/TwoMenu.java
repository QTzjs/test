package com.happy.model;
/**
 * 二级菜单实体类
 * @author zjs
 *2017/12/19
 */
public class TwoMenu {
	private int id;
	private int pid;			//父级菜单
	private String text;			//菜单名字
	private String state;			//菜单默认状态  打开/关闭
	private String iconCls;			//菜单图标名称
	private String url;				//菜单映射地址
	private String level;			//菜单显示权限等级
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
}
