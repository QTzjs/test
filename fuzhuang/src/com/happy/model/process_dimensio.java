package com.happy.model;
/**
 * 分包 参数实体
 * @author QT-001
 *	
 */
public class process_dimensio {
	private int id;
    private String p_num;//订单号
    private String p_color;//颜色
    private String p_size;//尺寸
    private String packe_num;//包号
    private int p_number;//件数
    private String cylinder;//缸号
    private int girard;//款号
    private String production_number;//生产号
    private int bed;//床号
    private int fake;//
    private int completed;//已完成工序个数
    private String current;//当前进行工序
    private int surplus;//剩余多少道工序
    
	public int getCompleted() {
		return completed;
	}
	public void setCompleted(int completed) {
		this.completed = completed;
	}
	public String getCurrent() {
		return current;
	}
	public void setCurrent(String current) {
		this.current = current;
	}
	public int getSurplus() {
		return surplus;
	}
	public void setSurplus(int surplus) {
		this.surplus = surplus;
	}
	public int getFake() {
		return fake;
	}
	public void setFake(int fake) {
		this.fake = fake;
	}
	public String getPacke_num() {
		return packe_num;
	}
	public void setPacke_num(String packe_num) {
		this.packe_num = packe_num;
	}
	public int getGirard() {
		return girard;
	}
	public void setGirard(int girard) {
		this.girard = girard;
	}
	public String getProduction_number() {
		return production_number;
	}
	public void setProduction_number(String production_number) {
		this.production_number = production_number;
	}
	public int getBed() {
		return bed;
	}
	public void setBed(int bed) {
		this.bed = bed;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getP_size() {
		return p_size;
	}
	public void setP_size(String p_size) {
		this.p_size = p_size;
	}
	public int getP_number() {
		return p_number;
	}
	public void setP_number(int p_number) {
		this.p_number = p_number;
	}
	public String getP_num() {
		return p_num;
	}
	public void setP_num(String p_num) {
		this.p_num = p_num;
	}
	public String getP_color() {
		return p_color;
	}
	public void setP_color(String p_color) {
		this.p_color = p_color;
	}
	public String getCylinder() {
		return cylinder;
	}
	public void setCylinder(String cylinder) {
		this.cylinder = cylinder;
	}
    
    
}
