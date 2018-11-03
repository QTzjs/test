package com.happy.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.happy.model.Record;
import com.happy.model.place;
import com.happy.service.RecordService;
import com.happy.util.CombinatorialQuery;
import com.happy.util.ResponseUtil;

@Controller
public class RecordAction {
	private HttpServletRequest request;
	@Resource
	private RecordService recordService;
	
	private String sort;//排序依据字段名
	private String order;//排序方式
	private int rows;//每页显示的行数rows
	private int page; //当前页
	private String advanceFilter;//组合查询条件
	private String filterRules;//过滤组件条件
	
	 private int id;
     private String operation;//具体操作类型
     private String date;//日期
     private String from;//从哪里进行操作
     private String record1;//预留字段
     private String opid;//操作id
     private String people;//操作人
	
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

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
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

	public String getPeople() {
		return people;
	}

	public void setPeople(String people) {
		this.people = people;
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

	public RecordService getRecordService() {
		return recordService;
	}

	public void setRecordService(RecordService recordService) {
		this.recordService = recordService;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	//查询所有订单
		public String list(){
			JSONObject resultJson = new JSONObject();
			List<Record> listAll=null;
			List<Record> listPage=null;
			if(sort != null || advanceFilter !=null && !advanceFilter.equals("[]") || filterRules != null && !filterRules.equals("[]")){
				String sql = CombinatorialQuery.queryOne(advanceFilter, sort, order, filterRules);
//				System.out.println(sql);
				listAll = recordService.combGetRecordList(sql);
				listPage = recordService.combGetRecordList(sql,page,rows);
			}else{
				listAll= recordService.getAllList();//查询所有记录
				listPage =recordService.getAllList(page, rows);//查询分页
			}
			if(listPage==null){
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
			ResponseUtil.writeJson(ServletActionContext.getResponse(),resultJson.toString());
			return null;
		}
}
