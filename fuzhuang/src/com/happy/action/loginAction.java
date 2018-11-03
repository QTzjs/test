package com.happy.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.happy.model.Admin;
import com.happy.service.AdminService;
import com.happy.util.ResUtil;


/**
 * 登录action
 * @author lzp
 *
 */
@Controller
public class loginAction extends ActionSupport implements ServletRequestAware{
	private HttpServletRequest request;
	private String account;
	private String password;
	private Admin admin;
	private String id;
	@Resource
	private AdminService adminService;

	public AdminService getAdminService() {
		return adminService;
	}
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	//登录的方法
	public String login(){
				//管理员登录
				Admin admin =  adminService.getAdmin(account, password);
				if(admin != null){
					ServletActionContext.getRequest().getSession().setAttribute("admin", admin);
					ServletActionContext.getRequest().getSession().setAttribute("fruit", 2);
					ServletActionContext.getRequest().getSession().removeAttribute("erro");
					ServletActionContext.getRequest().getSession().setAttribute("isLogin", "123");
					return "index";
				}
				ServletActionContext.getRequest().getSession().setAttribute("erro", 1);
				return "login";
		}
	//得到用户名密码
	public String getUser() throws Exception{
		Admin user=(Admin) ServletActionContext.getRequest().getSession().getAttribute("admin");
		JSONObject resultJson = new JSONObject();
		resultJson.put("name", user.getAdmin_name());
		resultJson.put("pass", user.getAdmin_password());
		resultJson.put("id", user.getAdmin_id());
		ResUtil.write(resultJson, ServletActionContext.getResponse());
		return null;
	}
	//修改密码
	public String updatePass() throws Exception{		
		JSONObject resultJson = new JSONObject();
		int num=adminService.updateAdmin(id, password);
		if(num>0){
			resultJson.put("success", "修改成功");
		}
		ResUtil.write(resultJson, ServletActionContext.getResponse());
		return null;
	}
	//退出登录
	public String loginout(){
		ServletActionContext.getRequest().getSession().invalidate();
		return "login";
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
}
