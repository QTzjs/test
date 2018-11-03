package com.happy.action;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.sql.ResultSet;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;





import com.happy.model.Admin;
import com.happy.model.ProductionPiecework;
import com.happy.model.Record;
import com.happy.model.place_technology;
import com.happy.model.sub_data;
import com.happy.model.weixin.User;
import com.happy.service.CodeService;
import com.happy.service.Packe_tService;
import com.happy.service.Place_tService;
import com.happy.service.RecordService;
import com.happy.service.sub_dataService;
import com.happy.util.CombinatorialQuery;
import com.happy.util.DBtoExcel;
import com.happy.util.ResUtil;
import com.happy.util.ResponseUtil;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 提交记录控制层
 * @author zjs
 *
 */
@Controller
public class SubRAction extends ActionSupport implements ServletRequestAware {
	@Resource
	private sub_dataService subService;
	@Resource
	private CodeService codeService;
	@Resource
	private Packe_tService packe_tService;
	@Resource
	private RecordService recordService;
	@Resource
	private Place_tService place_tService;
	private HttpServletRequest request;
	private String job_number;
	private String data;
	private String place_num;
	private String id;
	private String jsonData;
	private String packe_num;
	private String t_name;
	private String user_name;
	private int biao;
	private String style;
	private String time1;
	private String time2;
	
	
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getTime1() {
		return time1;
	}
	public void setTime1(String time1) {
		this.time1 = time1;
	}
	public String getTime2() {
		return time2;
	}
	public void setTime2(String time2) {
		this.time2 = time2;
	}
	public Place_tService getPlace_tService() {
		return place_tService;
	}
	public void setPlace_tService(Place_tService place_tService) {
		this.place_tService = place_tService;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getBiao() {
		return biao;
	}
	public void setBiao(int biao) {
		this.biao = biao;
	}

	private String sort;//排序依据字段名
	private String order;//排序方式
	private int rows;//每页显示的行数rows
	private int page; //当前页
	private String advanceFilter;//组合查询条件
	private String filterRules;//过滤组件条件
	
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
	public String getAdvanceFilter() {
		return advanceFilter;
	}
	public void setAdvanceFilter(String advanceFilter) {
		this.advanceFilter = advanceFilter;
	}
	public String getFilterRules() {
		return filterRules;
	}
	public void setFilterRules(String filterRules) {
		this.filterRules = filterRules;
	}
	public String getPacke_num() {
		return packe_num;
	}
	public void setPacke_num(String packe_num) {
		this.packe_num = packe_num;
	}
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
	public Packe_tService getPacke_tService() {
		return packe_tService;
	}
	public void setPacke_tService(Packe_tService packe_tService) {
		this.packe_tService = packe_tService;
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
	public String getJsonData() {
		return jsonData;
	}
	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPlace_num() {
		return place_num;
	}
	public void setPlace_num(String place_num) {
		this.place_num = place_num;
	}
	public CodeService getCodeService() {
		return codeService;
	}
	public void setCodeService(CodeService codeService) {
		this.codeService = codeService;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public sub_dataService getSubService() {
		return subService;
	}
	public void setSubService(sub_dataService subService) {
		this.subService = subService;
	}
	public String getJob_number() {
		return job_number;
	}
	public void setJob_number(String job_number) {
		this.job_number = job_number;
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
	
	public RecordService getRecordService() {
		return recordService;
	}
	public void setRecordService(RecordService recordService) {
		this.recordService = recordService;
	}
	//查询订单工票
	public String List(){
		//返回前端数据
		JSONObject resultJson = new JSONObject();
		List<sub_data> listAll=null;
		List<sub_data> listPage = null;
		int total=0;
		if(sort != null || advanceFilter !=null && !advanceFilter.equals("[]") || filterRules != null && !filterRules.equals("[]")){
			String sql = CombinatorialQuery.queryOne(advanceFilter, sort, order, filterRules);
//			System.out.println(sql);
			listAll = subService.combGetsubList(sql,place_num);
			listPage=subService.combGetsubList(sql,place_num,page,rows);
			request.getSession().setAttribute("sqlx", sql);
		}else{
			listAll=subService.findlist(place_num);
			listPage=subService.findlist(place_num, page, rows);
			
		}
		if(listAll==null){
			resultJson.put("rows","");
			resultJson.put("total",0);
		}else{
			resultJson.put("rows",listPage);
			total=listAll.size();
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
		
		ResponseUtil.writeJson(ServletActionContext.getResponse(),resultJson.toString());
		return null;
	}
     //查询已提交人数
	public String ListPeople() throws Exception{
		//返回前端数据
		JSONObject resultJson = new JSONObject();
		int num=subService.findlistPeople(place_num);//得到提交不重复的人
		resultJson.put("findlistPeople", num);
		ResUtil.write(resultJson, ServletActionContext.getResponse());
		return null;
	}
	 //查询已完成的工序
		public String ListFinish() throws Exception{
			//返回前端数据
			JSONObject resultJson = new JSONObject();
			int nu=subService.findfinish(place_num);
			resultJson.put("findlistFinish", nu);
			ResUtil.write(resultJson, ServletActionContext.getResponse());
			return null;
		}	
	 //查询未完成的工序
		public String ListUnfinish() throws Exception{
			//返回前端数据
			JSONObject resultJson = new JSONObject();
			int nn=codeService.findPackNum(place_num);//总包数
			int uu=codeService.findGongXu(place_num);//总工序
			int nu=subService.findfinish(place_num);//已完成
			int sum=nn*uu-nu;
			resultJson.put("unfinsh", sum);
			ResUtil.write(resultJson, ServletActionContext.getResponse());
			return null;
		}		
	//查询工票接口
    public String getInfo() throws Exception{
    	int bao=0;
    	//返回前端数据
    	JSONObject resultJson = new JSONObject();
    		//根据时间和工号查询当天所有的工单
    		List<sub_data> list=subService.list(job_number, data);
    		if(list != null){
	    		for(int i = 0;i<list.size();i++){
	    			bao += list.get(i).getNumber();
	    		}
	    		resultJson.put("data",list);
	    		resultJson.put("bao",bao);
    		}else{
    			resultJson.put("data",null);
    		}
    		ResUtil.write(resultJson, ServletActionContext.getResponse());
    	return null;
    } 
    //工单回收	//手机端返工
    public String recover() throws Exception{
		//前端返回值
		JSONObject resultJson = new JSONObject();
		int num=0;
		int a=0;
		int b=0;
		int m=0;
		String sql = "delete from sub_data where id in (";//批量删除提交记录
		String sql2 = "update process_dimensio set completed=completed-1,surplus=surplus+1 where packe_num in (";//批量修改 包表 的工序完成情况
		String sql3 = "update packe_technology set state=1 where ";//批量修改 包绑定工序表 的工序状态
		if(biao == 1){
			sub_data sd= subService.findById(Integer.parseInt(id));
			if(sd!=null){
				sql += "'"+Integer.parseInt(id)+"')";
				sql2 += "'"+sd.getPacke_num()+"')";
				sql3 += "(packe_num='"+sd.getPacke_num()+"' and technology_name='"+sd.getT_name()+"')";
				  
				a=packe_tService.batchupdateStart(sql3);//批量修改 包绑定工序表的工序状态
				b=codeService.batchAddPack(sql2);//批量修改包表的完成工序数据
				num=subService.batchDelete(sql);//批量删除提交数据
				//添加操作记录
				Record red=new Record();
				SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				red.setDate(dd.format(new Date()));
				red.setOpid(sd.getPlace_num());
				red.setOperation("返工");
				red.setUfrom("手机端");
				red.setFake(0);
				User user=(User) request.getSession().getAttribute("user");
				red.setPeople(user.getUser_name());
				red.setRecord1("管理员"+user.getUser_name()+"在"+dd.format(new Date())+"手机返工中回收了订单号为"+sd.getPlace_num()+"中"+sd.getUser_name()+"提交的包号为"+sd.getPacke_num()+"做的工序为"+sd.getT_name()+"的工单");
			    m=recordService.add(red);
				if(num>0&&a>0&&b>0&&m>0){
					resultJson.put("fangong", "success");
				}
			}
			ResUtil.write(resultJson, ServletActionContext.getResponse());
			return null;
		}
		String ii=id.replaceAll("'", "");//去除单引号
		String pn=packe_num.replaceAll("'", "");//去除单引号
		String tn=t_name.replaceAll("'", "");//去除单引号
		String pln=place_num.replaceAll("'", "");//去除单引号
		String un=user_name.replace("'", "");//去除单引号
		 if(ii.indexOf(",")!=-1){//判断是否包含，
			 String[] strs=ii.split(",");
			 String[] strs2=pn.split(",");
			 String[] strs3=tn.split(",");
			 String[] strs4=pln.split(",");
			 for(int i=0;i<strs.length;i++){
				 sql += "'"+Integer.parseInt(strs[i])+"'";
				 sql2 += "'"+strs2[i]+"'";
				 sql3 += "(packe_num='"+strs2[i]+"' and technology_name='"+strs3[i]+"')";
				 if(i!=strs.length-1){
					 sql += ",";
					 sql2 += ",";
					 sql3 += " or ";
				 }else{
					 sql += ")";
					 sql2 += ")";
				 }
			   //查询该工单
//			   sub_data sd= subService.findById(Integer.parseInt(strs[i]));
			    //根据包号和工序名称修改包绑定工序表的工序状态
//		        a = packe_tService.updateTovoidOne(sd.getPacke_num(),sd.getT_name());
//		        if(a<0){
//		        	break;
//		        }
		    	//根据包号更改包记录的工序完成情况
		        /*b = codeService.updateTovoidTwo(sd.getPacke_num());*/
//		        b = packe_tService.updateTovoidTwo(sd.getPacke_num());
//		        if(b<0){
//		        	break;
//		        }
			 }
			 a=packe_tService.batchupdateStart(sql3);//批量修改 包绑定工序表的工序状态
			 b=codeService.batchAddPack(sql2);//批量修改包表的完成工序数据
			 num=subService.batchDelete(sql);//批量删除提交数据
			//添加操作记录
				Record red=new Record();
				SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				red.setDate(dd.format(new Date()));
				red.setOpid(strs4[0]);
				red.setOperation("工单回收");
				red.setUfrom("已提交数据—提交记录详情");
				red.setFake(0);
				Admin admin=(Admin) request.getSession().getAttribute("admin");
				red.setPeople(admin.getAdmin_name());
				red.setRecord1("管理员"+admin.getAdmin_name()+"在"+dd.format(new Date())+"已提交数据—提交记录详情中回收了订单号为"+strs4[0]+"中"+un+"提交的包号为"+pn+"做的工序为"+tn+"的工单");
			    m=recordService.add(red);
		  }else{
			sql += "'"+Integer.parseInt(ii)+"')";
			sql2 += "'"+pn+"')";
			sql3 += "(packe_num='"+pn+"' and technology_name='"+tn+"')";
			  
			a=packe_tService.batchupdateStart(sql3);//批量修改 包绑定工序表的工序状态
			b=codeService.batchAddPack(sql2);//批量修改包表的完成工序数据
			num=subService.batchDelete(sql);//批量删除提交数据
		   
			//添加操作记录
			Record red=new Record();
			SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			red.setDate(dd.format(new Date()));
			red.setOpid(pln);
			red.setOperation("工单回收");
			red.setUfrom("已提交数据—提交记录详情");
			red.setFake(0);
			Admin admin=(Admin) request.getSession().getAttribute("admin");
			red.setPeople(admin.getAdmin_name());

			red.setRecord1("管理员"+admin.getAdmin_name()+"在"+dd.format(new Date())+"已提交数据—提交记录详情中回收了订单号为"+pln+"中"+un+"提交的包号为"+pn+"做的工序为"+tn+"的工单");
		    m=recordService.add(red);
        }
		
		if(num>0&&a>0&&b>0&&m>0){
			resultJson.put("statusCode","200");
			resultJson.put("title", "操作提示");
			resultJson.put("message", "操作成功！");
		}else{
			resultJson.put("statusCode","500");
			resultJson.put("title", "操作提示");
			resultJson.put("message", "操作失败！数据异常！请检查是否误删数据！");
		}
		ResponseUtil.writeJson(ServletActionContext.getResponse(),resultJson.toString());
    	return null;
    }
    
    //员工作废
    public String tovoid() throws Exception{
    	//返回前端数据
    	JSONObject resultJson = new JSONObject();
    	//根据id查询提交记录
//    	sub_data sd= subService.findById(id);
    	int num = subService.updateById(Integer.parseInt(id));
    	if(num != 0){
    		resultJson.put("ok","ok");
    	}else{
    		resultJson.put("ok",null);
    	}
    	
    	ResUtil.write(resultJson, ServletActionContext.getResponse());
    	return null;
    }
    
    //订单提交包表打印
    public String Reporrinting() throws ClassNotFoundException, SQLException{
    	//前端返回值
		JSONObject resultJson = new JSONObject();
	    //项目路径地址
	    String path=request.getSession().getServletContext().getRealPath("/download/");//File.separator
	    //根据订单号 查询这个订单绑定的所有的工序 
	    List<place_technology> plat = place_tService.findByNum(place_num);
	    if(plat != null){
//	    	String sql = "SELECT user_name,";
//	        Vector columnName = new Vector(); // 列名
//	        columnName.add("姓名");  
//	        for(int i=0;i<plat.size();i++){
//	        	sql+=" IFNULL(sum(case t_name when '"+plat.get(i).getTechnology_name()+"' then number end),' ') as '"+plat.get(i).getTechnology_name()+"'";
//	        	if(!(i==plat.size()-1)){
//	        		sql+=",";
//	        	}else{
//	        		sql+=" ";
//	        	}
//	        	columnName.add(plat.get(i).getTechnology_name()); 
//	        }
//	        sql+=" FROM sub_data WHERE place_num='"+place_num+"' GROUP BY user_name";
//	    	ResultSet rs =  subService.demo(sql);
	    	new test().Printing(plat,place_num,path);
//	    	// 导出文件  
//	        new DBtoExcel().WriteExcel(rs, path+place_num+"Report.xls", place_num+"订单提交记录报表", columnName);
			resultJson.put("downurl", place_num+"Report.xls");
	    }
		ResponseUtil.writeJson(ServletActionContext.getResponse(),resultJson.toString());
		return null;
    }
    
    //查询所有的计件工序
    public String Report(){
    	//前端返回值
		JSONObject resultJson = new JSONObject();
		List<ProductionPiecework> pp = null;
		String sql = "select a.user_name,a.place_num,c.style,a.t_name,b.price,SUM(a.number)number,(b.price * SUM(a.number)) as money,a.job_number from sub_data a,place_technology b,place c WHERE a.place_num=b.place_number and a.t_name=b.technology_name and a.place_num=c.plan_num";
		if(biao==1){
			if(user_name!=null){
				String[] user_names = user_name.split(",");
				for(int i=0;i<user_names.length;i++){
					if(i==0){
						if(user_names.length==1){
							sql+=" and a.user_name='"+user_names[i].trim()+"'";
						}else{
							sql+=" and (a.user_name='"+user_names[i].trim()+"'";
						}
					}else if(i==user_names.length-1){
						sql+=" or a.user_name='"+user_names[i].trim()+"')";
					}else{
						sql+=" or a.user_name='"+user_names[i].trim()+"'";
					}
				}
			}
			if(job_number!=null){
				String[] job_numbers = job_number.split(",");
				for(int i=0;i<job_numbers.length;i++){
					if(i==0){
						if(job_numbers.length==1){
							sql+=" and a.job_number='"+job_numbers[i].trim()+"'";
						}else{
							sql+=" and (a.job_number='"+job_numbers[i].trim()+"'";
						}
					}else if(i==job_numbers.length-1){
						sql+=" or a.job_number='"+job_numbers[i].trim()+"')";
					}else{
						sql+=" or a.job_number='"+job_numbers[i].trim()+"'";
					}
				}
//				sql+=" and a.job_number='"+job_number+"'";
			}
			if(place_num!=null){
				String[] place_nums = place_num.split(",");
				for(int i=0;i<place_nums.length;i++){
					if(i==0){
						if(place_nums.length==1){
							sql+=" and a.place_num='"+place_nums[i].trim()+"'";
						}else{
							sql+=" and (a.place_num='"+place_nums[i].trim()+"'";
						}
					}else if(i==place_nums.length-1){
						sql+=" or a.place_num='"+place_nums[i].trim()+"')";
					}else{
						sql+=" or a.place_num='"+place_nums[i].trim()+"'";
					}
				}
//				sql+=" and a.place_num='"+place_num+"'";
			}
			if(style!=null){
				String[] styles = style.split(",");
				for(int i=0;i<styles.length;i++){
					if(i==0){
						if(styles.length==1){
							sql+=" and c.style='"+styles[i].trim()+"'";
						}else{
							sql+=" and (c.style='"+styles[i].trim()+"'";
						}
					}else if(i==styles.length-1){
						sql+=" or c.style='"+styles[i].trim()+"')";
					}else{
						sql+=" or c.style='"+styles[i].trim()+"'";
					}
				}
//				sql+=" and c.style='"+style+"'";
			}
			if(time1!=null){
				sql+=" and a.sub_time>='"+time1+"'";
			}
			if(time2!=null){
				sql+=" and a.sub_time<='"+time2+"'";
			}
			sql+=" GROUP BY a.user_name,a.place_num,a.t_name order by a.user_name,a.place_num,a.t_name desc";
			pp = subService.findAll(sql);
		}else{
			pp = subService.findAll();
		}
    	if(pp!=null){
    		resultJson.put("Table", pp);
    	}
    	ResponseUtil.writeJson(ServletActionContext.getResponse(),resultJson.toString());
    	return null;
    }
    
    public static void main(String[] args) {
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        //过去一月
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        Date m = c.getTime();
        String mon = format.format(m);
        System.out.println("过去一个月："+mon);
	}
    

}
