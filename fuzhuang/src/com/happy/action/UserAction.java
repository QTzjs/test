package com.happy.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import testExport.ExportExcel;

import com.happy.model.Admin;
import com.happy.model.Export;
import com.happy.model.Record;
import com.happy.model.place;
import com.happy.model.sub_data;
import com.happy.model.weixin.User;
import com.happy.service.PlaceService;
import com.happy.service.RecordService;
import com.happy.service.UserService;
import com.happy.service.sub_dataService;
import com.happy.util.ChineseInital;
import com.happy.util.CombinatorialQuery;
import com.happy.util.ResponseUtil;
import com.opensymphony.xwork2.ActionSupport;
@Controller
public class UserAction extends ActionSupport implements ServletRequestAware {
	private HttpServletRequest request;
	@Resource
	private UserService userService;
	@Resource
	private sub_dataService sub_dataService;
	@Resource
	RecordService recordService;
	@Resource
	PlaceService placeService;
	private String sort;//排序依据字段名
	private String order;//排序方式
	private int rows;//每页显示的行数rows
	private int page; //当前页
	private String advanceFilter;//组合查询条件
	private String filterRules;//过滤组件条件
	
	private String excelTitle;//导出ecxl文档名
	private String place_num;
	private String packe_num;
	private String t_name;
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
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
	public RecordService getRecordService() {
		return recordService;
	}
	public void setRecordService(RecordService recordService) {
		this.recordService = recordService;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getFilterRules() {
		return filterRules;
	}
	public void setFilterRules(String filterRules) {
		this.filterRules = filterRules;
	}
	public String getAdvanceFilter() {
		return advanceFilter;
	}
	public void setAdvanceFilter(String advanceFilter) {
		this.advanceFilter = advanceFilter;
	}
	public String getExcelTitle() {
		return excelTitle;
	}
	public void setExcelTitle(String excelTitle) {
		this.excelTitle = excelTitle;
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

	private String user_name;
	private String user_account;
	private String password;
	private int user_level;
	private String user_time;
	private String jsonData;
	
	public String getJsonData() {
		return jsonData;
	}
	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_account() {
		return user_account;
	}
	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getUser_level() {
		return user_level;
	}
	public void setUser_level(int user_level) {
		this.user_level = user_level;
	}
	public String getUser_time() {
		return user_time;
	}
	public void setUser_time(String user_time) {
		this.user_time = user_time;
	}

	private int id;
	private String ids;
	private String job_number;
	
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getJob_number() {
		return job_number;
	}
	public void setJob_number(String job_number) {
		this.job_number = job_number;
	}
	public sub_dataService getSub_dataService() {
		return sub_dataService;
	}
	public void setSub_dataService(sub_dataService sub_dataService) {
		this.sub_dataService = sub_dataService;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public String list(){
		//返回前端数据
		JSONObject resultJson = new JSONObject();
		List<User> listAll=null;
		List<User> listPage = null;
		int total=0;
		if(sort != null || advanceFilter !=null && !advanceFilter.equals("[]") || filterRules != null && !filterRules.equals("[]")){
			String sql = CombinatorialQuery.queryOne(advanceFilter, sort, order, filterRules);
//			System.out.println(sql);
			listAll = userService.combGetUserList(sql);
			listPage=userService.combGetUserList(sql,page,rows);
		}else{
			listAll=userService.getlist();
			listPage=userService.getlist(page, rows);
		}
		if(listAll != null){
			total=listAll.size();
			resultJson.put("total",total);//总记录数
			resultJson.put("rows",listAll);
			if(listPage != null){
				resultJson.put("rows",listPage);
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
		}else{
			resultJson.put("rows","");
    		resultJson.put("total",0);
		}
		ResponseUtil.writeJson(ServletActionContext.getResponse(),resultJson.toString());
		return null;
	}
	public String add(){
		//获取前端传入的json
		//前端返回值
		JSONObject resultJson = new JSONObject();
		//将前端传入值序列对象
		int num=0;
		int m=0;
		User u=new User();
		u.setUser_name(user_name);
		u.setUser_account(user_account);
		u.setUser_level(user_level);
		u.setPassword(password);
		u.setUser_time(user_time);
		//工号生成
		String job_number="";
		//判断名字是否全是中文
		String reg = "[\\u4e00-\\u9fa5]+"; 
		boolean result = user_name.matches(reg);//false 
        if(result){
		SimpleDateFormat df = new SimpleDateFormat("yyMMdd");//设置日期格式
		String time = df.format(new Date());
		String sz = ChineseInital.getAlpha(user_name);//用工具类获取客户名首字母
		//生成1~100随机数
		int numb=new Random().nextInt(100) +1;
		job_number = sz+time+numb;
		u.setJob_number(job_number);
		u.setOpen_id("");
		u.setFake(0);
		num=userService.add_user(u);
		//添加操作记录
		Record red=new Record();
		SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		red.setDate(dd.format(new Date()));
		red.setOpid(job_number);
		red.setOperation("新增员工信息");
		red.setUfrom("员工列表");
		red.setFake(0);
		Admin admin=(Admin) request.getSession().getAttribute("admin");
		red.setPeople(admin.getAdmin_name());
		if(user_level==1){
			red.setRecord1("管理员"+admin.getAdmin_name()+"在"+dd.format(new Date())+"员工列表中新添加了员工名为"+user_name+"，手机号码为"+user_account+"，等级为普通员工，入职时间是"+user_time+"的员工");
		}else{
			red.setRecord1("管理员"+admin.getAdmin_name()+"在"+dd.format(new Date())+"员工列表中新添加了员工名为"+user_name+"，手机号码为"+user_account+"，等级为管理员，入职时间是"+user_time+"的员工");
		}
		
	    m=recordService.add(red);
		if(num>=0&&m>0){
			resultJson.put("statusCode","200");
			resultJson.put("title", "操作提示");
			resultJson.put("message", "操作成功！");
		}else{
			resultJson.put("statusCode","500");
			resultJson.put("title", "操作提示");
			resultJson.put("message", "操作失败！");
		}
          }else{
        	  resultJson.put("statusCode","500");
				resultJson.put("title", "操作提示");
				resultJson.put("message", "名字必须是中文！");  
        	  
          }
		ResponseUtil.writeJson(ServletActionContext.getResponse(),resultJson.toString());
		return null;
	}
	public String findById(){
		//返回前端数据
		List<User> list=userService.findById(id);
		JSONObject info = JSONObject.fromObject(list.get(0));
		ResponseUtil.writeJson(ServletActionContext.getResponse(),info.toString());
		return null;
	}
	public String update(){
		//获取前端传入的json
		//前端返回值
		JSONObject resultJson = new JSONObject();
		User u=new User();
		u.setId(id);
		u.setUser_name(user_name);
		u.setUser_account(user_account);
		u.setUser_level(user_level);
		u.setPassword(password);
		u.setUser_time(user_time);
		int num=userService.updateuser(u);
		List<User> list=userService.findById(id);
		User uu=list.get(0);
		//添加操作记录
		Record red=new Record();
		SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		red.setDate(dd.format(new Date()));
		red.setOpid(uu.getJob_number());
		red.setOperation("编辑员工信息");
		red.setUfrom("员工列表");
		red.setFake(0);
		Admin admin=(Admin) request.getSession().getAttribute("admin");
		red.setPeople(admin.getAdmin_name());
		if(user_level==1){
			red.setRecord1("管理员"+admin.getAdmin_name()+"在"+dd.format(new Date())+"员工列表中修改了员工号为"+uu.getJob_number()+"的员工改成了员工名为"+user_name+"，手机号码为"+user_account+"，等级为普通员工，入职时间是"+user_time+"的员工");
		}else{
			red.setRecord1("管理员"+admin.getAdmin_name()+"在"+dd.format(new Date())+"员工列表中修改了员工号为"+uu.getJob_number()+"的员工改成了员工名为"+user_name+"，手机号码为"+user_account+"，等级为管理员，入职时间是"+user_time+"的员工");
		}
		
	    int m=recordService.add(red);		
		if(num>0&&m>0){
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
	 public String delete(){
    	int num=0;
    	int m=0;
		//前端返回值
		JSONObject resultJson = new JSONObject();
		String sql="update user set fake=1 where id in";
		 if(ids.indexOf(",")!=-1){//判断是否包含，
			 sql+="("+ids+")";
			 num=userService.bathDelete(sql);
			//添加操作记录
			 	String  jn= job_number.replace("'", "");
			    String  uname=user_name.replace("'", "");
			    String  i=ids.replace("'", "");
				Record red=new Record();
				SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				red.setDate(dd.format(new Date()));
				red.setOpid(jn);
				red.setOperation("删除员工信息");
				red.setUfrom("员工列表");
				red.setFake(0);
				Admin admin=(Admin) request.getSession().getAttribute("admin");
				red.setPeople(admin.getAdmin_name());
				red.setRecord1("管理员"+admin.getAdmin_name()+"在"+dd.format(new Date())+"员工列表中删除了员工工号为"+jn+"名字为"+uname+"的员工");
			    m=recordService.add(red);	
		 }else{
			    String  i=ids.replace("'", "");
			    List<User> list=userService.findById(Integer.parseInt(i));
				User uu=list.get(0);
				//添加操作记录
				Record red=new Record();
				SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				red.setDate(dd.format(new Date()));
				red.setOpid(uu.getJob_number());
				red.setOperation("删除员工信息");
				red.setUfrom("员工列表");
				red.setFake(0);
				Admin admin=(Admin) request.getSession().getAttribute("admin");
				red.setPeople(admin.getAdmin_name());
				red.setRecord1("管理员"+admin.getAdmin_name()+"在"+dd.format(new Date())+"员工列表中删除了员工号为"+uu.getJob_number()+"名字叫"+uu.getUser_name()+"的员工");
			    m=recordService.add(red);		
			    num=userService.delete(Integer.parseInt(i));			
		 }
		/*String id=jsonobject.getString("id").replaceAll("'", "");//去除单引号
		 if(id.indexOf(",")!=-1){//判断是否包含，
			 String[] strs=id.split(",");
			 for(int i=0;i<strs.length;i++){
				 num=userService.delete(Integer.parseInt(strs[i]));
			 }
			  }else{
			   num=userService.delete(Integer.parseInt(id));
			  }*/
		
		if(num>0&&m>0){
			resultJson.put("statusCode","200");
			resultJson.put("title", "操作提示");
			resultJson.put("message", "操作成功！");
		}else{
			resultJson.put("statusCode","300");
			resultJson.put("title", "操作提示");
			resultJson.put("message", "操作失败！");
		}
		ResponseUtil.writeJson(ServletActionContext.getResponse(),resultJson.toString());
    	return null;
    }
	 
	 //根据员工号查询员工提交的工单数据
	 public String findByjob(){
		 JSONObject resultJson = new JSONObject();
		 List<sub_data> listAll;
		 List<sub_data> listPage;
		 if(sort != null || advanceFilter !=null && !advanceFilter.equals("[]") || filterRules != null && !filterRules.equals("[]")){
			 String sql = CombinatorialQuery.queryOne(advanceFilter, sort, order, filterRules);
			 //组合查询
			 listAll = sub_dataService.combinatoriaQuery(sql,job_number);
			 listPage = sub_dataService.combinatoriaQuery(sql,job_number, page, rows);
//			 System.out.println(sql);
			 request.getSession().setAttribute("sql", sql);
		 }else{
			 listAll =  sub_dataService.findByjob(job_number);
			 listPage =  sub_dataService.findByjob(job_number, page, rows);
			 request.getSession().removeAttribute("sql");
		 }
		 
		 if(listAll != null){
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
			 
		 }else{
			 resultJson.put("rows","");	
			 resultJson.put("total",0);
		 }
			ResponseUtil.writeJson(ServletActionContext.getResponse(),resultJson.toString());
		 return null;
	 }
	 
	 //根据工单id删除工单
	 public String deleteuserinfo(){
		 	int num=0;
		 	int m=0;
			//前端返回值
			JSONObject resultJson = new JSONObject();
			String sql="update sub_data set fake=1 where id in";
			String pnum=place_num.replace("'", "");
			String un=user_name.replace("'", "");
			String pn=packe_num.replace("'", "");
			String tn=t_name.replace("'", "");
			 if(ids.indexOf(",")!=-1){//判断是否包含，
				 String[] strs=pnum.split(",");
				 String[] strs2=pn.split(",");
				 String[] strs3=tn.split(",");
				 String[] strs4=un.split(",");
				 sql+="("+ids+")";
			     num= sub_dataService.batchDelete(sql);
			   //添加操作记录
					Record red=new Record();
					SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					red.setDate(dd.format(new Date()));
					red.setOpid(strs[0]);
					red.setOperation("删除工单");
					red.setUfrom("员工列表—员工工单提交详情");
					red.setFake(0);
					Admin admin=(Admin) request.getSession().getAttribute("admin");
					red.setPeople(admin.getAdmin_name());
					red.setRecord1("管理员"+admin.getAdmin_name()+"在"+dd.format(new Date())+"员工列表—员工工单提交详情中删除了订单号为"+strs[0]+"中"+strs4[0]+"提交的包号为"+pn+"做的工序为"+tn+"的工单");
				    m=recordService.add(red);
			  }else{
				  	String  i=ids.replace("'", "");  
				  	//添加操作记录
					Record red=new Record();
					SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					red.setDate(dd.format(new Date()));
					red.setOpid(pnum);
					red.setOperation("删除工单");
					red.setUfrom("员工列表—员工工单提交详情");
					red.setFake(0);
					Admin admin=(Admin) request.getSession().getAttribute("admin");
					red.setPeople(admin.getAdmin_name());
					red.setRecord1("管理员"+admin.getAdmin_name()+"在"+dd.format(new Date())+"员工列表—员工工单提交详情中删除了订单号为"+pnum+"中"+un+"提交的包号为"+pn+"做的工序为"+tn+"的工单");
				    m=recordService.add(red);
				    num= sub_dataService.delete(Integer.parseInt(i));
			  }
			/*String id=jsonobject.getString("id").replaceAll("'", "");//去除单引号
			 if(id.indexOf(",")!=-1){//判断是否包含，
				 String[] strs=id.split(",");
				 for(int i=0;i<strs.length;i++){
					 num= sub_dataService.delete(Integer.parseInt(strs[i]));
				 }
				  }else{
				   num= sub_dataService.delete(Integer.parseInt(id));
			}*/
			if(num>0){
				resultJson.put("statusCode","200");
				resultJson.put("title", "操作提示");
				resultJson.put("message", "操作成功！");
			}else{
				resultJson.put("statusCode","300");
				resultJson.put("title", "操作提示");
				resultJson.put("message", "操作失败！");
			}
			ResponseUtil.writeJson(ServletActionContext.getResponse(),resultJson.toString());
		 return null;
	 }
	 //导出数据为ecxl
	 public String ecxlOut(){
		 //fieldName
		 //excelTitle:文章管理_导出数据_20180323104111
		 //colName:标题,创建时间
		 //跟据员工id查询员工所提交的数据
		//前端返回值
		JSONObject resultJson = new JSONObject();
	    ExportExcel<Export> ex = new ExportExcel<Export>();
		String[] headers = { "订单号", "包号","工序名称", "单价","数量","工钱", "工号","提交人","提交时间"};
		String sql=(String) request.getSession().getAttribute("sql");
		List<Export> listAll = null;
		if(sql==null){
			 //导出结果查询
			 listAll = sub_dataService.exportFind(job_number);
		}else{
		     listAll = sub_dataService.exportFind(sql,job_number);
		}
		
		String user_name = listAll.get(0).getUser_name();
		SimpleDateFormat df = new SimpleDateFormat("MM-dd-HH-mm-ss");//设置日期格式
		String time = df.format(new Date());
		String path=request.getSession().getServletContext().getRealPath("/download")+File.separator;
		File file=new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
		try{
			OutputStream out = new FileOutputStream(path+"daochu"+time+".xls");
			ex.exportExcel(headers, listAll, out);
			out.close();
			//JOptionPane.showMessageDialog(null, "导出成功!");
//			System.out.println("excel导出成功！");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
/*		resultJson.put("message", "导出表格成功");*/
		resultJson.put("downurl", "daochu"+time+".xls");
		ResponseUtil.writeJson(ServletActionContext.getResponse(),resultJson.toString());
		return null;
	 }
	 
	 //员工生产计件获取所有的员工
	 public String getAllUserList() {
		 JSONObject jsonObject = new JSONObject();
		 List<User> list = userService.getlist();
		 List<place> listPlace = placeService.findDai();
		 jsonObject.put("list", list);
		 jsonObject.put("listPlace", listPlace);
		 ResponseUtil.writeJson(ServletActionContext.getResponse(),jsonObject.toString());
		return null;
	}
	 
	 public static void main(String[] args) {
		String sql="where fake=0 and user=3";
		int i=sql.indexOf("order by");
		System.out.println(i);
		StringBuffer sb = new StringBuffer();
		if(i>-1){
			sb.append(sql).insert(i, "and jonb='"+1+"' ");
			System.out.println(sb.toString());
		}else{
			sb.append(sql).insert(sql.length(), " and jonb='"+1+"' ");
			System.out.println(sb.toString());
		}
	}

}
