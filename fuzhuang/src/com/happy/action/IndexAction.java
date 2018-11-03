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

import com.happy.model.Admin;
import com.happy.model.OneMenu;
import com.happy.model.TwoMenu;
import com.happy.model.TreMenu;
import com.happy.service.OneMenuService;
import com.happy.service.TreMenuService;
import com.happy.service.TwoMenuService;
import com.happy.util.ResponseUtil;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 首页action
 * @author zjs
 *
 */
@Controller
public class IndexAction extends ActionSupport implements ServletRequestAware {
	
	private HttpServletRequest request;
	
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	@Resource
	private OneMenuService oneMenuService;
	@Resource
	private TwoMenuService twoMenuService;
	@Resource
	private TreMenuService treMenuService;
	
	public TreMenuService getTreMenuService() {
		return treMenuService;
	}
	public void setTreMenuService(TreMenuService treMenuService) {
		this.treMenuService = treMenuService;
	}
	public TwoMenuService getTwoMenuService() {
		return twoMenuService;
	}
	public void setTwoMenuService(TwoMenuService twoMenuService) {
		this.twoMenuService = twoMenuService;
	}
	public OneMenuService getOneMenuService() {
		return oneMenuService;
	}
	public void setOneMenuService(OneMenuService oneMenuService) {
		this.oneMenuService = oneMenuService;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	private Integer pid;
	private int id;
	private String uuid;
	private String[] order_account;
	
	public String[] getOrder_account() {
		return order_account;
	}
	public void setOrder_account(String[] order_account) {
		this.order_account = order_account;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	
	/*//一级菜单
	public String oneMenu() throws Exception{
		//从session获取admin_id
		Admin admin=(Admin) ServletActionContext.getRequest().getSession().getAttribute("admin");
		String isLogin = (String) ServletActionContext.getRequest().getSession().getAttribute("isLogin");
		//System.out.println(isLogin);
		JSONObject resultJson = new JSONObject();
		List<OneMenu> list = oneMenuService.getOneMenuList();
		resultJson.put("menu",list);
		ResponseUtil.writeJson(ServletActionContext.getResponse(),resultJson.toString());
	//	System.out.println(resultJson);
		return null;
	}*/
	//二级菜单
	public String twoMenu() throws Exception{
			Admin admin=(Admin) ServletActionContext.getRequest().getSession().getAttribute("admin");
			if(admin!=null){
				if(admin.getLevel()==1){ //超级管理员默认查询当前一级菜单的所有二级菜单
					JSONObject json = new JSONObject();
					List<TwoMenu> l = twoMenuService.getTwoMenu1(pid);
					//List<List<TwoMenu>> list = new ArrayList<List<TwoMenu>>();
					//list.add(l);
					/*int len=l.size()-1;//最大下标
					String twomenu="[";
					for(int i=0;i<l.size();i++){
						if(i==len){
							twomenu+="{"+l.get(i)+"}";
						}else{
							twomenu+="{"+l.get(i)+"},";
						}
					}
					twomenu+="]";*/
					JSONArray listArray=JSONArray.fromObject(l);
					//JSONObject jsonOb = JSONObject.fromObject(listArray);
					ResponseUtil.write(listArray, ServletActionContext.getResponse());
				}
			}
			return null;
		}
		//三级菜单
		public String treMenu() throws Exception {
			
				Admin admin=(Admin) ServletActionContext.getRequest().getSession().getAttribute("admin");
				if(admin!=null){
					if(admin.getLevel()==1){ //超级管理员默认查询当前二级菜单的所有二级菜单
						List<TreMenu> l = treMenuService.getTreMenu(pid);
						JSONArray listArray=JSONArray.fromObject(l);
						//json.put("level", 10);
						ResponseUtil.write(listArray, ServletActionContext.getResponse());
					}
				}
			return null;	
	}
	//树形菜单
	public String tree() throws Exception{
		    int pid[]={1,2,3,4,5};//所有一级
			//JSONObject json = new JSONObject();
			List<List<TwoMenu>> list = new ArrayList<List<TwoMenu>>();
		    String msg = "";
			for(int i=0;i<pid.length;i++){
				List<TwoMenu> l = twoMenuService.getTwoMenu1(pid[i]);
				for(int j=0;j<l.size();j++){
					JSONObject js1 = JSONObject.fromObject(l.get(j));
					String m = js1.toString();
					m = m.substring(0, m.length()-1)+",children:[";
					List<TreMenu> l1 = treMenuService.getTreMenu(l.get(j).getId());
					for(int k=0;k<l1.size();k++){
						JSONObject js2 = JSONObject.fromObject(l1.get(k));
						m = m+js2.toString()+",";
					}
					m = m.substring(0, m.length()-1)+"]},";
					msg =msg+ m;
				}
				
			}
			msg = "["+msg.substring(0, msg.length()-1)+"]";
			//JSONObject jss = new JSONObject(msg);
			//System.out.println(msg);
			//json.put("success", true);
			ResponseUtil.write(msg, ServletActionContext.getResponse());
	    return null;
	}
	/*public String info() throws Exception{
		if(uuid!=null){
			return "info";
		}
		return null;
	}
	/*public String select()throws Exception{
		if(id!=0){
			System.out.println(id);
		}
		JSONObject  json = new JSONObject();
		json.put("carton_type", "对口箱");
		json.put("model_name", "对口箱1");
		json.put("carton_length", "58");
		json.put("carton_width", "54");
		json.put("carton_height", "53");
		json.put("spec_type", "制造尺寸");
		json.put("material_science", "B636B");
		json.put("pit_type", "BC");
		json.put("carton_color", "灰白");
		json.put("pack_num", "50");
		json.put("line_type", "外压");
		json.put("fabric_width", "20");
		json.put("isnail", "是");
		json.put("isviscose", "否");
		//String msg = "[{\"iconCls\": \"closed\", \"id\": 201, \"level\": \"1\", \"pid\": 1, \"state\": \"colos\", \"text\": \"物资损耗管理 \", \"url\": \"\"}]";
		ResponseUtil.write(json, ServletActionContext.getResponse());
		return null;
	}
	public String test() throws Exception{
		JSONObject json = new JSONObject();
		json.put("msg", 1);
		
		System.out.println(uuid);
		ResponseUtil.write(json, ServletActionContext.getResponse());
		return null;
	}*/
	
}
