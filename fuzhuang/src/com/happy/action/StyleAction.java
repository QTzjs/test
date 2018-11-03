package com.happy.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import com.happy.model.style;
import com.happy.model.size_color;
import com.happy.model.technology;
import com.happy.service.Size_cService;
import com.happy.service.StyleService;
import com.happy.util.CombinatorialQuery;
import com.happy.util.ResponseUtil;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 款式控制层
 * @author zjs
 *
 */
@Controller
public class StyleAction extends ActionSupport implements ServletRequestAware {
	private HttpServletRequest request;
	
	@Resource
	private StyleService styleService;
	@Resource
	private Size_cService size_cService;
	private String id;
	private String jsonData;
	
	private String sort;//排序依据字段名
	private String order;//排序方式
	private String advanceFilter;//组合查询条件
	private String filterRules;//过滤组件条件
	private int rows;//每页显示的行数rows
	private int page; //当前页
	
	private String style_name;//款式名字
	private String K_id;
	private String color;
	private String size;
	private int biao;
	
    
	
	public int getBiao() {
		return biao;
	}
	public void setBiao(int biao) {
		this.biao = biao;
	}
	public String getFilterRules() {
		return filterRules;
	}
	public void setFilterRules(String filterRules) {
		this.filterRules = filterRules;
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
	public String getAdvanceFilter() {
		return advanceFilter;
	}
	public void setAdvanceFilter(String advanceFilter) {
		this.advanceFilter = advanceFilter;
	}
	public String getK_id() {
		return K_id;
	}
	public void setK_id(String k_id) {
		K_id = k_id;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getStyle_name() {
		return style_name;
	}
	public void setStyle_name(String style_name) {
		this.style_name = style_name;
	}
	public Size_cService getSize_cService() {
		return size_cService;
	}
	public void setSize_cService(Size_cService size_cService) {
		this.size_cService = size_cService;
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
	public StyleService getStyleService() {
		return styleService;
	}
	public void setStyleService(StyleService styleService) {
		this.styleService = styleService;
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
	
	//获取款式列表
	public String getStyles(){
		int total=0;
		List<style> listAll=null;
		List<style> listPage=null;
		if(sort != null || advanceFilter !=null && !advanceFilter.equals("[]") || filterRules != null && !filterRules.equals("[]")){
			String sql = CombinatorialQuery.queryOne(advanceFilter, sort, order, filterRules);
//			System.out.println(sql);
			listAll=styleService.combGetStyleList(sql);
			listPage = styleService.combGetStyleList(sql,page, rows);
		}else{
			listAll=styleService.getStyleList();
			if(biao==1){
				JSONArray json=new JSONArray();
				if(listAll!=null){
					json = JSONArray.fromObject(listAll);
				}
				ResponseUtil.writeJson(ServletActionContext.getResponse(),json.toString());
//				System.out.println(json.toString());
		    	return null;
			}
			listPage = styleService.getStyleList(page, rows);
		}
		JSONObject resultJson = new JSONObject();
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
	
	//获取款式尺寸颜色列表
	public String getStylec(){
		/*List<size_color> stylec = styleService.getStyleC(id);
		List<size_color> stylecPage = styleService.getStyleC(id, page, rows);*/
		List<size_color> stylec = size_cService.getStyleC(Integer.parseInt(id));
		List<size_color> stylecPage = size_cService.getStyleC(Integer.parseInt(id), page, rows);
		JSONObject resultJson = new JSONObject();
		if(stylec!=null){
			resultJson.put("rows",stylecPage);
			int total=stylec.size();
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
	
	
	//添加款式
	public String addStyle(){
		
    	JSONObject resultJson = new JSONObject();
    	int a=styleService.addStyle(style_name);
    	if(a>0){
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
	
	//添加款式详情
	public String addStyleC(){
		JSONObject jsonobject = JSONObject.fromObject(jsonData);
    	//前端返回值
    	JSONObject resultJson = new JSONObject();
    	
    	int a=size_cService.addStyleC(Integer.parseInt(K_id),size,color);
    	if(a>0){
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
	
	//根据款式id查询款式记录
	public String findByid(){
		style s = styleService.findByid(Integer.parseInt(id));
		if(s != null){
			JSONObject info = JSONObject.fromObject(s);
			ResponseUtil.writeJson(ServletActionContext.getResponse(),info.toString());
			return null;
		}
		return null;
	}
	
	//根据款式详情id查询款式详情记录
	public String findByCid(){
		size_color sc = size_cService.findByCid(Integer.parseInt(id));
		if(sc != null){
			JSONObject info = JSONObject.fromObject(sc);
			ResponseUtil.writeJson(ServletActionContext.getResponse(),info.toString());
			return null;
		}
		return null;
	}
	
	//编辑款式
	public String updateStyle(){
		//前端返回值
		JSONObject resultJson = new JSONObject();
		//将前端传入值序列对象
		style st = new style();
		st.setId(Integer.parseInt(id));
		st.setStyle_name(style_name);
		int num = styleService.updateStyle(st);
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
	
	//编辑款式详情
	public String updateStyleC(){
		//前端返回值
		JSONObject resultJson = new JSONObject();
		//将前端传入值序列对象
		size_color sc = new size_color();
		sc.setId(Integer.parseInt(id));
//		sc.setK_id(Integer.parseInt(jsonobject.getString("K_id")));
		sc.setColor(color);
		sc.setSize(size);
		int num = size_cService.updateStyleC(sc);
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
	
	//删除款式
	public String deleteStyle(){
		int num=0;
		//前端返回值
		JSONObject resultJson = new JSONObject();
		String sql="delete a.*,b.* from style a left join size_color b on a.id=b.k_id where a.id in (";
		String id1=id.replaceAll("'", "");//去除单引号0
		 if(id1.indexOf(",")!=-1){//判断是否包含，
			 String[] strs=id1.split(",");
			 for(int i=0;i<strs.length;i++){
				 sql += "'"+Integer.parseInt(strs[i])+"'";
				 if(i!=strs.length-1){
					 sql += ",";
				 }else{
					 sql += ")";
				 }
			 }
			 num=styleService.batchdeleteStyle(sql);
		 }else{
			 sql += Integer.parseInt(id1)+")";
			 num=styleService.batchdeleteStyle(sql);
		 }
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
	
	//删除款式详情
	public String deleteStyleC(){
		int num=0;
		String sql = "delete from size_color where id in (";
		//前端返回值
		JSONObject resultJson = new JSONObject();
		String d=id.replaceAll("'","");//去除单引号0
		
		String[] strs=d.split(",");
		if(strs.length>0){//判断是否包含，
			 for(int i=0;i<strs.length;i++){
				 sql += "'"+Integer.parseInt(strs[i])+"'";
				 if(i!=strs.length-1){
					 sql += ",";
				 }else{
					 sql += ")";
				 }
			 }
			 num=size_cService.batchdeleteStyleC(sql);
		}else{
			num=size_cService.deleteStyleC(Integer.parseInt(strs[0]));
	    }
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
