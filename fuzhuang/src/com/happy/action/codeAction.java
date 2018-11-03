package com.happy.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.happy.model.Admin;
import com.happy.model.Packe_technology;
import com.happy.model.Record;
import com.happy.model.place;
import com.happy.model.place_technology;
import com.happy.model.process_dimensio;
import com.happy.service.CodeService;
import com.happy.service.Packe_tService;
import com.happy.service.PlaceService;
import com.happy.service.Place_tService;
import com.happy.service.RecordService;
import com.happy.service.sub_dataService;
import com.happy.util.ChineseInital;
import com.happy.util.ResponseUtil;
import com.happy.util.TestZXing;
import com.happy.util.ValidationUtils;

@Controller
public class codeAction extends ActionSupport implements ServletRequestAware{
	private HttpServletRequest request;
	//private process_dimensio process_dimensio;
	private int n;		//接收前端发送过来的包数n
	@Resource
	private CodeService codeService;
	@Resource
	private PlaceService placeService;
	@Resource
	private Place_tService place_tService;
	@Resource
	private sub_dataService sub_dataService;
	@Resource
	private Packe_tService packe_tService;
	@Resource
	private RecordService recordService;
	
	private String place_num;
	private String jsonData;
	private int rows;//每页显示的行数rows
	private int page; //当前页
	private String id;
	private String packe_num;
	private String pack_num;
	private String p_num;
	private String p_color;
	private String p_size;
	private String p_number;
	private String cylinder;
	
	public RecordService getRecordService() {
		return recordService;
	}
	public void setRecordService(RecordService recordService) {
		this.recordService = recordService;
	}
	public String getPack_num() {
		return pack_num;
	}
	public void setPack_num(String pack_num) {
		this.pack_num = pack_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getP_size() {
		return p_size;
	}
	public void setP_size(String p_size) {
		this.p_size = p_size;
	}
	public String getP_number() {
		return p_number;
	}
	public void setP_number(String p_number) {
		this.p_number = p_number;
	}
	public String getCylinder() {
		return cylinder;
	}
	public void setCylinder(String cylinder) {
		this.cylinder = cylinder;
	}
	public Place_tService getPlace_tService() {
		return place_tService;
	}
	public void setPlace_tService(Place_tService place_tService) {
		this.place_tService = place_tService;
	}
	public Packe_tService getPacke_tService() {
		return packe_tService;
	}
	public void setPacke_tService(Packe_tService packe_tService) {
		this.packe_tService = packe_tService;
	}
	public sub_dataService getSub_dataService() {
		return sub_dataService;
	}
	public void setSub_dataService(sub_dataService sub_dataService) {
		this.sub_dataService = sub_dataService;
	}
	public String getPacke_num() {
		return packe_num;
	}
	public void setPacke_num(String packe_num) {
		this.packe_num = packe_num;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getPlace_num() {
		return place_num;
	}
	public void setPlace_num(String place_num) {
		this.place_num = place_num;
	}
	public PlaceService getPlaceService() {
		return placeService;
	}
	public void setPlaceService(PlaceService placeService) {
		this.placeService = placeService;
	}
	public CodeService getCodeService() {
		return codeService;
	}
	public void setCodeService(CodeService codeService) {
		this.codeService = codeService;
	}
	
	public String getJsonData() {
		return jsonData;
	}
	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
	public int getN(){
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public String makecode() throws Exception{
		//定义生成的参数
		String filePath="D:\\erwei";//这里是存放路径,验证是否存在，如果不存在则创建一个（或者存入项目路径）
		String content="";
		int num=2018112300;//生成内容
		TestZXing tz=new TestZXing();
		for(int  i=1;i<5;i++){
			String fileName="fuz"+i+".png";//文件名称
			content="ZD"+num+i;
			tz.Encode(filePath,fileName,content);//生成二维码
		}
		return null;
	}
	
	public String toSubcontract(){
		//前端返回值
		JSONObject resultJson = new JSONObject();
		String sql = "insert into process_dimensio (p_num,p_color,p_size,packe_num,p_number,cylinder,girard,production_number,bed,fake,completed,current,surplus) "
				+ "values";
		//定义添加新的包绑定记录的sql语句
		String sql1="insert into packe_technology (packe_num,technology_name,state,p_num) values";
		//插入对象
		
		
		String qita = "";//包的序号前面的	qita+scort=完整的包号
		qita = p_num+"";//包号的前半部分生成 ，改成用款号当成包号的前半部分
		String scort = "";//包的序号
		List<place_technology> tlist= place_tService.findByNum(p_num);//根据订单号查询其绑定的所有工序
		if(tlist==null){
			resultJson.put("statusCode","500");
			resultJson.put("title", "操作提示");
			resultJson.put("message", "该订单没有绑定工序请绑定工序再添加！");
			ResponseUtil.writeJson(ServletActionContext.getResponse(),resultJson.toString());
			return null;
		}
		
		String[] p_colors = p_color.split(",");
		String[] p_sizes = p_size.split(",");
		String[] packe_nums = packe_num.split(",");
		String[] p_numbers = p_number.split(",");
		
		//根据订单号查询订单记录
		place order =  placeService.findByNumber(p_num);
		
		int allNumber=order.getNumber();//订单总数
		int res = order.getResidue();//订单已分包的数量
		int allNumbers = allNumber-res;//订单实际剩余分包数
		int residue=0;
		for(int i = 0;i<p_colors.length;i++){
			residue+=Integer.valueOf(packe_nums[i].trim())*Integer.valueOf(p_numbers[i].trim());
		}
		if(residue>allNumbers){
			resultJson.put("statusCode","500");
			resultJson.put("title", "操作提示");
			resultJson.put("message", "数量不能大于总订单数");
			ResponseUtil.writeJson(ServletActionContext.getResponse(),resultJson.toString());
			return null;
		}
		
//		List<process_dimensio> list = codeService.findByNumber(p_num);//这里是排序查询  get（0）是最大的
		//查询该订单号在包表中的最大包号
		process_dimensio p = codeService.findByPlace(p_num);
		
		//包的序号前面的生成
		//客户名    当客户的名字为空时默认为V V	 //包号=订单id+客户名字首字母+当前时间年月日+序号
//		String customer = order.getCustomer();
//		SimpleDateFormat df = new SimpleDateFormat("yyMMdd");//设置日期格式
//		String time = df.format(new Date());
//		String sz = ChineseInital.getAlpha(customer);//用工具类获取客户名首字母
		/*qita = order.getId()+sz+time+"";*/
		//根据订单号查询包记录
		int num=0;
		int num1 = 0;
		int num2 = 0;
		String packnum="";
		String packenum ="";
		
		
		if(p==null){
			int number=0;
			for(int x = 0;x<p_colors.length;x++){
				process_dimensio process_dimensio = new process_dimensio();
				Packe_technology packe_technology = new Packe_technology();
				
				int bao = Integer.valueOf(packe_nums[x].trim());//当前包数
				
				//设置新的包的数据
				process_dimensio.setP_num(p_num);
				process_dimensio.setP_color(p_colors[x].trim());
				process_dimensio.setBed(0);
				process_dimensio.setP_number(Integer.parseInt(p_numbers[x].trim()));
				process_dimensio.setP_size(p_sizes[x].trim());
				process_dimensio.setProduction_number(p_num);
				process_dimensio.setCylinder(cylinder);
				process_dimensio.setGirard(1);
				process_dimensio.setCompleted(0);//设置已完成工序个数
				process_dimensio.setSurplus(tlist.size());//设置未完成工序个数
				process_dimensio.setCurrent("剪线");//设置当前进行中工序名称
				
				
				for(int i=0;i<bao;i++){
					number += 1;
					String newString = String.valueOf(number);//int 转 string
					int demi = newString.length();//获得新的包的序号的长度   才好在其面前+0
					if(demi == 1){
						scort = "000"+newString;
					}else if(demi == 2){
						scort = "00"+newString;
					}else if(demi == 3){
						scort = "0"+newString;
					}else if(demi == 4){
						scort = newString;
					}
					packenum = qita+scort;
					//新的包号设置
					process_dimensio.setPacke_num(packenum);
					sql += "('"+process_dimensio.getP_num()+"','"+process_dimensio.getP_color()+"','"+process_dimensio.getP_size()+"','"+process_dimensio.getPacke_num()
							+"','"+process_dimensio.getP_number()+"','"+process_dimensio.getCylinder()+"','"+process_dimensio.getGirard()
							+"','"+process_dimensio.getProduction_number()+"','"+process_dimensio.getBed()+"','"+process_dimensio.getFake()
							+"','"+process_dimensio.getCompleted()+"','"+process_dimensio.getCurrent()+"','"+process_dimensio.getSurplus()+"')";
					if(!(x==p_colors.length-1 && i==bao-1)){
						sql += ",";
					}
					//批量添加包绑定工序数据
					for(int j=0;j<tlist.size();j++){
						packe_technology.setPacke_num(packenum);
						packe_technology.setTechnology_name(tlist.get(j).getTechnology_name());
						packe_technology.setState(1);
						packe_technology.setP_num(p_num);
						sql1 +="('"+packe_technology.getPacke_num()+"','"+packe_technology.getTechnology_name()+"','"+packe_technology.getState()
								+"','"+packe_technology.getP_num()+"')";
						if(!(x==p_colors.length-1 && i==bao-1 && j==tlist.size()-1)){
							sql1 += ",";
						}
					}
				}
			}
		}else{
			
			
			
			packnum= p.getPacke_num();//获取其包号
			for(int x = 0;x<p_colors.length;x++){
				process_dimensio process_dimensio = new process_dimensio();
				Packe_technology packe_technology = new Packe_technology();
				
				int bao = Integer.valueOf(packe_nums[x].trim());//当前包数
				
				//设置新的包的数据
				process_dimensio.setP_num(p_num);
				process_dimensio.setP_color(p_colors[x].trim());
				process_dimensio.setBed(0);
				process_dimensio.setP_number(Integer.parseInt(p_numbers[x].trim()));
				process_dimensio.setP_size(p_sizes[x].trim());
				process_dimensio.setProduction_number(p_num);
				process_dimensio.setCylinder(cylinder);
				process_dimensio.setGirard(1);
				process_dimensio.setCompleted(0);//设置已完成工序个数
				process_dimensio.setSurplus(tlist.size());//设置未完成工序个数
				process_dimensio.setCurrent("剪线");//设置当前进行中工序名称
				
				
				String demo = packnum.substring(packnum.length()-4);//获取包号长度-3是为了以下截取包号中的序号
				int year = Integer.valueOf(demo);//string 转 int
				
				for(int i=0;i<bao;i++){
					year += 1;//序号+1就是新的包的序号了
					String newString = String.valueOf(year);//int 转 string
					int demi = newString.length();//获得新的包的序号的长度   才好在其面前+0
					if(demi == 1){
						scort = "000"+newString;
					}else if(demi == 2){
						scort = "00"+newString;
					}else if(demi == 3){
						scort = "0"+newString;
					}else if(demi == 4){
						scort = newString;
					}
					packenum = qita+scort;
					packnum=packenum;
					
					//新的包号设置
					process_dimensio.setPacke_num(packenum);
					
					sql += "('"+process_dimensio.getP_num()+"','"+process_dimensio.getP_color()+"','"+process_dimensio.getP_size()+"','"+process_dimensio.getPacke_num()
							+"','"+process_dimensio.getP_number()+"','"+process_dimensio.getCylinder()+"','"+process_dimensio.getGirard()
							+"','"+process_dimensio.getProduction_number()+"','"+process_dimensio.getBed()+"','"+process_dimensio.getFake()
							+"','"+process_dimensio.getCompleted()+"','"+process_dimensio.getCurrent()+"','"+process_dimensio.getSurplus()+"')";
					if(!(x==p_colors.length-1 && i==bao-1)){
						sql += ",";
					}
					//批量添加包绑定工序数据
					for(int j=0;j<tlist.size();j++){
						packe_technology.setPacke_num(packenum);
						packe_technology.setTechnology_name(tlist.get(j).getTechnology_name());
						packe_technology.setState(1);
						packe_technology.setP_num(p_num);
						sql1 +="('"+packe_technology.getPacke_num()+"','"+packe_technology.getTechnology_name()+"','"+packe_technology.getState()
								+"','"+packe_technology.getP_num()+"')";
						if(!(x==p_colors.length-1 && i==bao-1 && j==tlist.size()-1)){
							sql1 += ",";
						}
					}
				}
			}
		}
		num2=placeService.updateResidue(p_num, residue);
		num = codeService.batchAddPack(sql);//批量添加包
		num1 = packe_tService.batchAddTechnology(sql1);//批量给新的包添加绑定工序
		//添加操作记录
		Record re=new Record();
		SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		re.setDate(dd.format(new Date()));
		re.setOpid(p_num);
		re.setOperation("添加信息");
		re.setUfrom("二维码生成—添加二维码信息");
		re.setFake(0);
		Admin admin=(Admin) request.getSession().getAttribute("admin");
		re.setPeople(admin.getAdmin_name());
		re.setRecord1("管理员"+admin.getAdmin_name()+"在"+dd.format(new Date())+"二维码生成中的添加二维码信息给"+"订单号为"+p_num+"的订单添加了"+packe_num+"包，每包为"+p_number+"件"+p_color+"尺码为"+p_size+"的包");
		int m=recordService.add(re);
		if(num>0 && num1>0 && num2>0 && m>0){
			resultJson.put("statusCode","200");
			resultJson.put("title", "操作提示");
			resultJson.put("message", "操作成功！");
		}else{
			resultJson.put("statusCode","500");
			resultJson.put("title", "操作提示");
			resultJson.put("message", "操作失败！");
		}
			
	  ResponseUtil.writeJson(ServletActionContext.getResponse(),resultJson.toString());
	  return null;
	}
	
	
	public String findById(){
		JSONObject resultJson = new JSONObject();
		if(place_num.equals("")){
			resultJson.put("rows","");
			resultJson.put("total",0);
		}else{
			List<process_dimensio> listAll = codeService.findByNumber(place_num);
			if(listAll==null){
				resultJson.put("rows","");
				resultJson.put("total",0);
			}else{
				resultJson.put("rows",listAll);
				resultJson.put("total",listAll.size());
			}
		}		
		ResponseUtil.writeJson(ServletActionContext.getResponse(),resultJson.toString());
		return null;
	}
	
	public String findByIdPage(){
		JSONObject resultJson = new JSONObject();
		if(place_num.equals("")){
			resultJson.put("rows","");
			resultJson.put("total",0);
		}else{
			List<process_dimensio> listAll = codeService.findByNumber(place_num);
			List<process_dimensio> listPage = codeService.findByNumber(place_num, page, rows);
			if(listAll==null){
				resultJson.put("rows","");
				resultJson.put("total",0);
			}else{
				resultJson.put("rows",listPage);
				int total=listAll.size();
				resultJson.put("total",total);//总记录数
				int totalPage=total%rows==0?(total/rows):(total/rows)+1;//总页数
				resultJson.put("totalPage", totalPage);
				resultJson.put("currentPage", page);//当前页
				resultJson.put("numPerPage", rows);//每页数
				resultJson.put("nextPage", totalPage-page==0?page:page+1);//下一页
				resultJson.put("previousPage", page-0==1?page:page-1);//上一页
				resultJson.put("hasPreviousPage", true);//有上一页
				resultJson.put("hasNextPage", true);//有下一页
				resultJson.put("firstPage", true);//首页
				resultJson.put("lastPage", true);//尾页
			}
		}		
		ResponseUtil.writeJson(ServletActionContext.getResponse(),resultJson.toString());
		return null;
	}
	
	//根据包号查询包记录
	public String findByPack(){
		process_dimensio pd=codeService.findByPack(packe_num);
	
		request.getSession().setAttribute("nm", pd.getP_number());
		JSONObject info = JSONObject.fromObject(pd);
		ResponseUtil.writeJson(ServletActionContext.getResponse(),info.toString());
		return null;
	}
	
	//修改包
	public String update(){
		//前端返回值
		JSONObject resultJson = new JSONObject();
		if(!ValidationUtils.isDigits(p_number)){
			resultJson.put("statusCode","500");
			resultJson.put("title", "操作提示");
			resultJson.put("message", "包的数量输入有误，请重新输入！");
			ResponseUtil.writeJson(ServletActionContext.getResponse(),resultJson.toString());
			return null;
		}
		//将前端传入值序列对象
		process_dimensio pd=new process_dimensio();
		pd.setPacke_num(packe_num);
		pd.setP_color(p_color);
		pd.setP_size(p_size);
//		pd.setCylinder(cylinder);
		int upadteNumber=Integer.parseInt(p_number);//修改的件数
		if(upadteNumber<0 || upadteNumber==0){
			resultJson.put("statusCode","500");
			resultJson.put("title", "操作提示");
			resultJson.put("message", "件数必须大于0，请重新输入！");
			ResponseUtil.writeJson(ServletActionContext.getResponse(),resultJson.toString());
			return null;
		}
		pd.setP_number(upadteNumber);
		int afterNumber=(Integer) request.getSession().getAttribute("nm");//获取编辑时查询并保存的原先数量
		int nn=0;
		if(upadteNumber!=afterNumber){
			place order =  placeService.findByNumber(p_num);//根据订单号查询订单
	        int allNumber=order.getNumber();//得到订单总数
	        int residue=order.getResidue();//获取到已分包的数量
	        int re=upadteNumber-afterNumber;//件数的变化量
			//判断修改件数后是否大于总数
	        int nu=residue+re-allNumber;
	        if(nu>0){
	        	resultJson.put("statusCode","500");
				resultJson.put("title", "操作提示");
				resultJson.put("message", "修改的件数已超过总量，请重新输入！");
				ResponseUtil.writeJson(ServletActionContext.getResponse(),resultJson.toString());
				return null;
	        }
	        nn=placeService.updateResidue(p_num, re);
		}
    	
		int num = codeService.updatePd(pd);
		//添加操作记录
		Record red=new Record();
		SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		red.setDate(dd.format(new Date()));
		red.setOpid(p_num);
		red.setOperation("修改包信息");
		red.setUfrom("二维码生成—添加二维码信息");
		red.setFake(0);
		Admin admin=(Admin) request.getSession().getAttribute("admin");
		red.setPeople(admin.getAdmin_name());
		red.setRecord1("管理员"+admin.getAdmin_name()+"在"+dd.format(new Date())+"二维码生成中的添加二维码信息修改了订单号"+p_num+"中包号为"+packe_num+"的包改成了"+p_color+"尺码为"+p_size+"件数为"+p_number);
		int m=recordService.add(red);
		if(num>0||nn>0){
			resultJson.put("statusCode","200");
			resultJson.put("title", "操作提示");
			resultJson.put("message", "操作成功！");
		}else{
			resultJson.put("statusCode","500");
			resultJson.put("title", "操作提示");
			resultJson.put("message", "操作失败！");
		}
				
		ResponseUtil.writeJson(ServletActionContext.getResponse(),resultJson.toString());
		return null;
	}
	
	//删除包
	 public String delete(){
	    int num=0;
    	int num1=0;
    	int num2=0;
    	int m=0;
		//前端返回值
		JSONObject resultJson = new JSONObject();
		String sql1 = "(";//多表联合删除语句  删除包表和删除包绑定工序表  包表是主表 包表有数据就能删除成功
		String sql2 = "delete from sub_data where packe_num in (";//根据包号批量删除员工提交工单
		String id1=id.replaceAll("'", "");//去除单引号
		String packe_num1 = packe_num.replaceAll("'", "");//去除单引号
		String p_num1=p_num.replaceAll("'", "");//去除单引号
		String[] p_nu=p_num1.split(",");
		place place =  placeService.findByNumber(p_nu[0]);//根据订单号查询订单
		String p_number1=p_number.replaceAll("'", "");//删除的数量
		//System.out.println(p_number);
		int deleteAll=0;
		 if(id.indexOf(",")!=-1){//判断是否包含，
			 String[] strs=id1.split(",");
			 String[] packNum = packe_num1.split(",");
			 String[] st=p_number1.split(",");
			 for(int i=0;i<strs.length;i++){
				 sql1 += "'"+packNum[i]+"'";
				 sql2 += "'"+packNum[i]+"'";
				 deleteAll+=Integer.parseInt(st[i]);
				 if(i!=strs.length-1){
					 sql1 += ",";
					 sql2 += ",";
				 }else{
					 sql1 += ")";
					 sql2 += ")";
				 }
			 }
			//1.根据包号批量多表删除  包表(process_dimensio)和包邦定工序表(packe_technology) 
			 //包表是主表，只要主表有数据其他表没数据没关系，都能删除成功
			 num=codeService.deletePacke_tAndP_d(sql1);
			 num1=placeService.updateResidue(p_nu[0], -deleteAll);
			 num2 = sub_dataService.batchDelete(sql2);
//					 //2.根据id批量删除包表(process_dimensio)
//					 num=codeService.dele();
			//添加操作记录
				Record red=new Record();
				SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				red.setDate(dd.format(new Date()));
				red.setOpid(p_nu[0]);
				red.setOperation("删除信息");
				red.setUfrom("二维码生成—添加二维码信息");
				red.setFake(0);
				Admin admin=(Admin) request.getSession().getAttribute("admin");
				red.setPeople(admin.getAdmin_name());
				red.setRecord1("管理员"+admin.getAdmin_name()+"在"+dd.format(new Date())+"二维码生成中的添加二维码信息删除了订单号"+p_nu[0]+"中包号为"+packe_num1+"的包");
			    m=recordService.add(red);
		  }else{
			  //根据id查询包号
			  sql1 += "'"+packe_num1+"')";
			  sql2 +="'"+packe_num1+"')";
			  
//					  num=codeService.dele(Integer.parseInt(id));
			  num=codeService.deletePacke_tAndP_d(sql1);
			 num2 = sub_dataService.batchDelete(sql2);
			 num1=placeService.updateResidue(p_nu[0], -Integer.parseInt(p_number1));
			//添加操作记录
				Record red=new Record();
				SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				red.setDate(dd.format(new Date()));
				red.setOpid(p_nu[0]);
				red.setOperation("删除信息");
				red.setUfrom("二维码生成—添加二维码信息");
				red.setFake(0);
				Admin admin=(Admin) request.getSession().getAttribute("admin");
				red.setPeople(admin.getAdmin_name());
				red.setRecord1("管理员"+admin.getAdmin_name()+"在"+dd.format(new Date())+"二维码生成中的添加二维码信息删除了订单号"+p_nu[0]+"中包号为"+packe_num1+"的包");
			    m=recordService.add(red);
		  }
		
		if(num>0&&num1>0&&m>0){
			resultJson.put("statusCode","200");
			resultJson.put("title", "操作提示");
			resultJson.put("message", "操作成功！");
		}else{
			resultJson.put("statusCode","500");
			resultJson.put("title", "操作提示");
			resultJson.put("message", "操作失败！");
		}
		ResponseUtil.writeJson(ServletActionContext.getResponse(),resultJson.toString());
    	return null;
    }
	
}
