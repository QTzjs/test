package com.happy.model;
/**
 * 
 * @author QT-001
 *  订单实体
 */
public class place {
	    private int id;
        private String plan_num;//订单号 
        private String style;//款式
        private int style_num;//款号
        private int number;//数量
        private String material;//里料
        private String cloth;//主布
        private String distribution;//配布
        private String fabric;//面料说明
        private String technology;//工艺说明
        private String customer;//客户
        private String delivery_time;//交货日期
        private String place_time;//下单日期
        private String place_speed;//订单进度
        private int state;//状态
        private String input_name;//录入人
        private String input_time;//录入时间
        private int fake;
        private int residue;//已生成包的数量
        
		public int getResidue() {
			return residue;
		}
		public void setResidue(int residue) {
			this.residue = residue;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getPlan_num() {
			return plan_num;
		}
		public void setPlan_num(String plan_num) {
			this.plan_num = plan_num;
		}
		public String getStyle() {
			return style;
		}
		public void setStyle(String style) {
			this.style = style;
		}
		public int getStyle_num() {
			return style_num;
		}
		public void setStyle_num(int style_num) {
			this.style_num = style_num;
		}
		public int getNumber() {
			return number;
		}
		public void setNumber(int number) {
			this.number = number;
		}
		public String getMaterial() {
			return material;
		}
		public void setMaterial(String material) {
			this.material = material;
		}
		public String getCloth() {
			return cloth;
		}
		public void setCloth(String cloth) {
			this.cloth = cloth;
		}
		public String getDistribution() {
			return distribution;
		}
		public void setDistribution(String distribution) {
			this.distribution = distribution;
		}
		public String getFabric() {
			return fabric;
		}
		public void setFabric(String fabric) {
			this.fabric = fabric;
		}
		public String getTechnology() {
			return technology;
		}
		public void setTechnology(String technology) {
			this.technology = technology;
		}
		public String getCustomer() {
			return customer;
		}
		public void setCustomer(String customer) {
			this.customer = customer;
		}
		public String getDelivery_time() {
			return delivery_time;
		}
		public void setDelivery_time(String delivery_time) {
			this.delivery_time = delivery_time;
		}
		public String getPlace_time() {
			return place_time;
		}
		public void setPlace_time(String place_time) {
			this.place_time = place_time;
		}
		public String getPlace_speed() {
			return place_speed;
		}
		public void setPlace_speed(String place_speed) {
			this.place_speed = place_speed;
		}
		public int getState() {
			return state;
		}
		public void setState(int state) {
			this.state = state;
		}
		public String getInput_name() {
			return input_name;
		}
		public void setInput_name(String input_name) {
			this.input_name = input_name;
		}
		public String getInput_time() {
			return input_time;
		}
		public void setInput_time(String input_time) {
			this.input_time = input_time;
		}
		public int getFake() {
			return fake;
		}
		public void setFake(int fake) {
			this.fake = fake;
		}
		
		
       
}
