package com.happy.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import com.happy.model.technology;
import com.happy.service.technologyService;
import com.happy.util.ResponseUtil;
import com.happy.util.ValidationUtils;
import com.opensymphony.xwork2.ActionSupport;
@Controller
public class technologyAction extends ActionSupport implements ServletRequestAware {
	private HttpServletRequest request;
	@Resource
	private technologyService technologyservice;
	private String id;
	private int rows;//每页显示的行数rows
	private int page; //当前页
	private String number;
	private String name;
	private String jsonData;
	
	private int biao;
	
	
	public int getBiao() {
		return biao;
	}
	public void setBiao(int biao) {
		this.biao = biao;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getJsonData() {
		return jsonData;
	}
	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public technologyService getTechnologyservice() {
		return technologyservice;
	}
	public void setTechnologyservice(technologyService technologyservice) {
		this.technologyservice = technologyservice;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
    public String add(){
    	JSONObject resultJson = new JSONObject();
    	if(!ValidationUtils.isDigits(number)|| !(Integer.parseInt(number)>0)){
    		resultJson.put("statusCode","500");
			resultJson.put("title", "操作提示");
			resultJson.put("message", "工序编号输入有误，请重新输入！");
			ResponseUtil.writeJson(ServletActionContext.getResponse(),resultJson.toString());
			return null;
    	}
    	//查询所有的工序
    	List<technology> tlist = technologyservice.getList();
    	if(tlist == null){
    		int a=technologyservice.add_gong(Integer.parseInt(number),name);
	    	if(a>0){
				resultJson.put("statusCode","200");
				resultJson.put("title", "操作提示");
				resultJson.put("message", "操作成功！");
			}else{
				resultJson.put("statusCode","500");
				resultJson.put("title", "操作提示");
				resultJson.put("message", "操作失败！");
			}
    	}else{
    		String nu=number;
    		String na=name;
    		boolean flag=false;
    		for(int i=0;i<tlist.size();i++){
    			if(tlist.get(i).getName().equals(na) || tlist.get(i).getNumber()==Integer.parseInt(nu)){
    				flag=true;
    			}
    			
    			
    		}
    		if(flag){
    			resultJson.put("statusCode","500");
				resultJson.put("title", "操作提示");
				resultJson.put("message", "已经存在相同工序或者工序编号！");
    			
    		}else{
    			int a=technologyservice.add_gong(Integer.parseInt(number),name);
    	    	if(a>0){
    				resultJson.put("statusCode","200");
    				resultJson.put("title", "操作提示");
    				resultJson.put("message", "操作成功！");
    			}else{
    				resultJson.put("statusCode","500");
    				resultJson.put("title", "操作提示");
    				resultJson.put("message", "操作失败！");
    			}
    			
    		}
    	}
    	
		ResponseUtil.writeJson(ServletActionContext.getResponse(),resultJson.toString());
    	return null;
    }
    //工序列表
    public String List(){
    	List<technology> listAll = technologyservice.getList();
    	if(biao==1){
    		JSONArray json = new JSONArray();
    		if(listAll!=null){
    			json = JSONArray.fromObject(listAll);
    		}
			ResponseUtil.writeJson(ServletActionContext.getResponse(),json.toString());
//			System.out.println(json.toString());
	    	return null;
		}
    	List<technology> listPage = technologyservice.getList(page, rows);
		JSONObject resultJson = new JSONObject();
		if(listAll==null){
    		resultJson.put("rows","");
    		resultJson.put("total",0);
    	}else{
    		int total=listAll.size();
    		if(listPage == null || listPage.size() == 0){
    			resultJson.put("total",total);//总记录数
    			resultJson.put("rows",listAll);
    		}else{
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
    	}
		ResponseUtil.writeJson(ServletActionContext.getResponse(),resultJson.toString());
    	return null;
    }
    
    //根据工序id查询工序详情信息
    public String findById(){
    	technology te = technologyservice.findById(Integer.parseInt(id));
    	JSONObject info = JSONObject.fromObject(te);
		ResponseUtil.writeJson(ServletActionContext.getResponse(),info.toString());
    	return null;
    }
    
    //更新工序信息
    public String update(){
		//前端返回值
		JSONObject resultJson = new JSONObject();
		//查询所有的工序
    	List<technology> tlist = technologyservice.getList();
    	//将前端传入值序列对象
		technology te = new technology();
    	
    	if(tlist == null){
    		int a=technologyservice.add_gong(Integer.parseInt(number),name);
	    	te.setId(Integer.parseInt(id));
			te.setNumber(Integer.parseInt(number));
			te.setName(name);
			int num = technologyservice.update(te);
			if(num>0){
				resultJson.put("statusCode","200");
				resultJson.put("title", "操作提示");
				resultJson.put("message", "操作成功！");
			}else{
				resultJson.put("statusCode","500");
				resultJson.put("title", "操作提示");
				resultJson.put("message", "操作失败！");
			}
    	}else{
    		String nu=number;
    		String na=name;
    		boolean flag=false;
    		for(int i=0;i<tlist.size();i++){
    			if(tlist.get(i).getName().equals(na) || tlist.get(i).getNumber()==Integer.parseInt(nu)){
    				flag=true;
    			}
    			
    		}
    		if(flag){
    			resultJson.put("statusCode","500");
				resultJson.put("title", "操作提示");
				resultJson.put("message", "已经存在相同工序！");
    			
    		}else{
    			te.setId(Integer.parseInt(id));
    			te.setNumber(Integer.parseInt(number));
    			te.setName(name);
    			int num = technologyservice.update(te);
    			if(num>0){
    				resultJson.put("statusCode","200");
    				resultJson.put("title", "操作提示");
    				resultJson.put("message", "操作成功！");
    			}else{
    				resultJson.put("statusCode","500");
    				resultJson.put("title", "操作提示");
    				resultJson.put("message", "操作失败！");
    			}
    			
    		}
    	
    	}
		
		
		ResponseUtil.writeJson(ServletActionContext.getResponse(),resultJson.toString());
    	return null;
    }
    // 删除工序
    public String delete(){
    	int num=0;
		//前端返回值
		JSONObject resultJson = new JSONObject();
		String sql="update technology set fake=1 where id in";
		 if(id.indexOf(",")!=-1){//判断是否包含，
			 sql+="("+id+")";
			 System.out.println(sql);
			num=technologyservice.batchDelete(sql);
		  }else{
			 String i= id.replace("'", "");
			num=technologyservice.delete(Integer.parseInt(i));
		  }
		/*String id=jsonobject.getString("id").replaceAll("'", "");//去除单引号
		 if(id.indexOf(",")!=-1){//判断是否包含，
			 String[] strs=id.split(",");
			 for(int i=0;i<strs.length;i++){
				 num=technologyservice.delete(Integer.parseInt(strs[i]));
			 }
			  }else{
			   num=technologyservice.delete(Integer.parseInt(id));
			  }
		*/
		if(num>0){
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

	







