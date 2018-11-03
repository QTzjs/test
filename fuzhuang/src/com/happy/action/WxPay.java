package com.happy.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.happy.model.Packe_technology;
import com.happy.model.place;
import com.happy.model.place_technology;
import com.happy.model.process_dimensio;
import com.happy.model.sub_data;
import com.happy.model.weixin.AccessToken;
import com.happy.model.weixin.Signature;
import com.happy.model.weixin.Ticket;
import com.happy.model.weixin.ToKen;
import com.happy.model.weixin.User;
import com.happy.model.weixin.WechatResult;
import com.happy.service.CodeService;
import com.happy.service.Packe_tService;
import com.happy.service.PlaceService;
import com.happy.service.Place_tService;
import com.happy.service.ToKenService;
import com.happy.service.UserService;
import com.happy.service.sub_dataService;
import com.happy.util.Contact;
import com.happy.util.HttpUtils;
import com.happy.util.PayWxUtil;
import com.happy.util.ResUtil;
import com.google.gson.Gson;
import com.happy.model.weixin.WeiXinUtil;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class WxPay extends ActionSupport implements ServletRequestAware {
	private HttpServletRequest request;
	@Resource
	private ToKenService toKenService;
	private String urlx; // 接收扫一扫页面ajax发送过来的url
	@Resource
	private UserService userService;
	@Resource
	private CodeService codeService;
	@Resource
	private sub_dataService sub_dataService;
	@Resource
	private PlaceService placeService;
	@Resource
	private Place_tService place_tService;
	@Resource
	private Packe_tService packe_tService;

	private static Lock lock = new ReentrantLock();
	private static Map<Integer, Lock> locks = new HashMap<Integer, Lock>();

	// 接收激活页面的数据
	private String user_account;
	private String job_number;

	// 接收扫码页面传过来的包号
	private String packe_num;
	private int id;
	
	private String t_name;

	private String place_num;
	private String user_name;

	private int number;

	private int biao;

	public String getT_name() {
		return t_name;
	}

	public void setT_name(String t_name) {
		this.t_name = t_name;
	}

	public int getBiao() {
		return biao;
	}

	public void setBiao(int biao) {
		this.biao = biao;
	}

	public Packe_tService getPacke_tService() {
		return packe_tService;
	}

	public void setPacke_tService(Packe_tService packe_tService) {
		this.packe_tService = packe_tService;
	}

	public Place_tService getPlace_tService() {
		return place_tService;
	}

	public void setPlace_tService(Place_tService place_tService) {
		this.place_tService = place_tService;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getPlace_num() {
		return place_num;
	}

	public void setPlace_num(String place_num) {
		this.place_num = place_num;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public sub_dataService getSub_dataService() {
		return sub_dataService;
	}

	public void setSub_dataService(sub_dataService sub_dataService) {
		this.sub_dataService = sub_dataService;
	}

	public PlaceService getPlaceService() {
		return placeService;
	}

	public void setPlaceService(PlaceService placeService) {
		this.placeService = placeService;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CodeService getCodeService() {
		return codeService;
	}

	public void setCodeService(CodeService codeService) {
		this.codeService = codeService;
	}

	public String getPacke_num() {
		return packe_num;
	}

	public void setPacke_num(String packe_num) {
		this.packe_num = packe_num;
	}

	public String getUser_account() {
		return user_account;
	}

	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}

	public String getJob_number() {
		return job_number;
	}

	public void setJob_number(String job_number) {
		this.job_number = job_number;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getUrlx() {
		return urlx;
	}

	public void setUrlx(String urlx) {
		this.urlx = urlx;
	}

	public ToKenService getToKenService() {
		return toKenService;
	}

	public void setToKenService(ToKenService toKenService) {
		this.toKenService = toKenService;
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

	// 获取code 微信支付第一步 通过code换取网页授权access_token
	public String code() throws IOException {

		request.getSession().removeAttribute("erro");

		try {
			// 回调地址
			String redirect_uri = URLEncoder.encode(WeiXinUtil.ip
					+ "WxPayopenid.action?codeID=1", "utf-8");

			/*
			 * String url =
			 * "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" +
			 * WeiXinUtil.appid + "&redirect_uri=" + redirect_uri +
			 * "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect"
			 * ;
			 */
			// scope作用域分两种 1为snsapi_base 则本步骤中获取到网页授权access_token的同时，也获取到了openid
			// 2为snsapi_userinfo
			String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
					+ WeiXinUtil.appid
					+ "&redirect_uri="
					+ redirect_uri
					+ "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
			ServletActionContext.getResponse().sendRedirect(url);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 获取openid 微信支付第二步 由第一步自动回调到这个方法，
	public String openid() throws Exception {
		HttpSession session = request.getSession();
		String OPEN_ID = "";

		String code = request.getParameter("code");

		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
				+ WeiXinUtil.appid
				+ "&secret="
				+ WeiXinUtil.secret
				+ "&code="
				+ code + "&grant_type=authorization_code";
		if (code != null) {
			String json = HttpUtils.get(requestUrl);
			WechatResult result = new Gson().fromJson(json, WechatResult.class);
			OPEN_ID = result.getOpenid();
			String access_token = result.getAccess_token();
			/*
			 * 此access_token为网页授权的access_token，和数据库存储的普通access_token不同，
			 * 数据库存储的是用来调用微信js接口用的
			 */
			// session.setAttribute("token", access_token);
			session.setAttribute("open_id", OPEN_ID);

			User user = userService.findByOpenid(OPEN_ID);
			if (user != null) {
				request.getSession().setAttribute("user", user);
				ServletActionContext.getResponse().sendRedirect(
						WeiXinUtil.ip + "jsp/weixin/sys.jsp");
				return null;
			} else {
				ServletActionContext.getResponse().sendRedirect(
						WeiXinUtil.ip + "jsp/weixin/register.jsp");
				return null;
			}
		}
		return null;

	}
	
	/**
	 * 调用微信后台项目的接口，获取调用微信开放js接口需要的凭证（access_token）
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getAcc() {
		request.getSession().setAttribute("urlx", urlx);// 把前端传过来的urlx地址保存在session中
		String requestUrl1 = WeiXinUtil.ip+"WxPaygetAcce.action";
		String url = "http://www.0791youxi.com/GameWebsite/WxPaygetToken.action?requestUrl1="
				+ requestUrl1;
		/*
		 * String requestUrl1 =
		 * "http://localhost:8080/HappysSystem/WxPaygetAcce.action"; String url
		 * =
		 * "http://localhost:8080/GameWebsite/WxPaygetToken.action?requestUrl1="
		 * +requestUrl1;
		 */
		try {
			ServletActionContext.getResponse().sendRedirect(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 调用第三方接口后回调的地址方法
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getAcce() throws Exception {
		String yy = (String) request.getSession().getAttribute("urlx");
		Signature s = new Signature();
		String access_token = "";
		String ticket = "";

		access_token = request.getParameter("access_token");
		ticket = request.getParameter("ticket");
		String end_time = request.getParameter("end_time");

		// 为签名实体类的属性赋值 为生成签名做准备
		s.setJsapi_ticket(ticket);
		s.setNoncestr(PayWxUtil.getNonceStr()); // 获得随机字符串
		s.setTimeStamp(new Date().getTime() / 1000 + "");
		s.setUrl(WeiXinUtil.ip + yy);

		String str = "jsapi_ticket=" + s.getJsapi_ticket() + "&noncestr="
				+ s.getNoncestr() + "&timestamp=" + s.getTimeStamp() + "&url="
				+ s.getUrl();
		s.setSignature(PayWxUtil.getSha1(str)); // 访问接口获得的签名
		
		JSONObject json = new JSONObject();
		json.put("s", s);
		ResUtil.write(json, ServletActionContext.getResponse());

		return null;
	}

	// 员工在激活页面的激活方法
	public String register() throws IOException {
		User user = new User();
		user.setJob_number(job_number);
		user.setUser_account(user_account);
		User u = userService.findByAccountAndJob(user); // 通过手机号和工号查询有无员工
		if (u != null && u.getOpen_id().equals("0")) {// 激活成功
			request.getSession().removeAttribute("erro");
			String openid = (String) request.getSession().getAttribute(
					"open_id");
			u.setOpen_id(openid);
			userService.update(u);// 绑定openid
			request.getSession().setAttribute("user", u);
			ServletActionContext.getResponse().sendRedirect(
					WeiXinUtil.ip + "jsp/weixin/sys.jsp");
			return null;
		} else {// 激活失败
			request.getSession().setAttribute("erro", "激活失败，手机号或者工号错误！");
			ServletActionContext.getResponse().sendRedirect(
					WeiXinUtil.ip + "jsp/weixin/register.jsp");
			return null;
		}
	}

	// 员工扫码根据包号查询包记录
	public String findByPack() throws Exception {
		JSONObject json = new JSONObject();
		// 根据包号查询包记录
		process_dimensio pack = codeService.findByPack(packe_num);
		json.put("pack", pack);
		if (biao == 1) {
			List<sub_data> sl = sub_dataService.findByPk(packe_num);// 根据包号查询提交记录
			json.put("subList", sl);
		} else {
			if (pack != null) {
				// 已完成工序列表集合
				List<Packe_technology> comList = packe_tService
						.findByPackTcom(packe_num);
				// 未完成工序列表集合
				List<Packe_technology> yetList = packe_tService
						.findByPackTyet(packe_num);
				json.put("comList", comList);
				json.put("yetList", yetList);
			}
		}
		ResUtil.write(json, ServletActionContext.getResponse());
		return null;
	}

	public String jump() {
		try {
			ServletActionContext.getResponse().sendRedirect(
					WeiXinUtil.ip + urlx);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 提交工序完成记录
	public String sub() throws IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			ServletActionContext.getResponse().sendRedirect(
					WeiXinUtil.ip + "jsp/weixin/again.jsp");
			return null;
		}
		Packe_technology pt=null;
		String yuan = "";
		if(biao==3){
			//根据订单号 和包号获取 包绑定工序表的 id
			pt = packe_tService.getId(packe_num,t_name);
			if(pt==null){
				yuan = packe_num+"包不存在或者已被删除！";
				yuan = URLEncoder.encode(yuan.toString(),"UTF-8");
				ServletActionContext
					.getResponse()
					.sendRedirect(
							WeiXinUtil.ip
									+ "jsp/weixin/erro.jsp?yuan="+yuan);
				return null;
			}else{
				if(!(pt.getP_num().equals(place_num))){
					yuan = "生产号不匹配，请先选择正确的生产号提交！";
					yuan = URLEncoder.encode(yuan.toString(),"UTF-8");
					ServletActionContext
					.getResponse()
						.sendRedirect(
								WeiXinUtil.ip
										+ "jsp/weixin/erro.jsp?yuan="+yuan);
					return null;
				}else if(pt.getState() == 2){
					yuan = packe_num+"包的"+t_name+"工序已经完成";
					yuan = URLEncoder.encode(yuan.toString(),"UTF-8");
					ServletActionContext
					.getResponse()
						.sendRedirect(
								WeiXinUtil.ip
										+ "jsp/weixin/erro.jsp?yuan="+yuan);
					return null;
				}else{
					id=pt.getId();
					/*System.out.println("查询出的id设为："+id);*/
				}
			}
		}
		if (id != 0 && packe_num != null) {
			Lock s_lock = null;
			for (int i = 0; i < 6; i++) {
				if (lock.tryLock()) {
					try {
						s_lock = locks.get(id);
						if (s_lock == null) {
							s_lock = new ReentrantLock();
							locks.put(id, s_lock);
						}
					} finally {
						lock.unlock();
					}
					break;
				}
				if (i == 5) {
					return null;
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			if (s_lock.tryLock()) {
				try {
					if(biao!=3){
						// id查询包邦定工序记录
						pt = packe_tService.findbByPt(id);
					}else{
						process_dimensio pd = codeService.findByPack(packe_num);
						number=pd.getP_number();
					}
					String t_nams=URLEncoder.encode(pt.getTechnology_name().toString(),"UTF-8");
					if (pt != null && pt.getState() == 1) {
						int state = 1;
						// 根据订单号和包号以及工序号查询 提交记录表 里有没有这条提交记录
						sub_data s = sub_dataService.findByPnPkT(place_num,
								packe_num, pt.getTechnology_name());
						if (s == null) {
							sub_data sd = new sub_data();
							sd.setPacke_num(packe_num);
							sd.setT_name(pt.getTechnology_name());
							sd.setJob_number(job_number);
							sd.setPlace_num(place_num);
							sd.setUser_name(user_name);
							SimpleDateFormat df = new SimpleDateFormat(
									"yyyy-MM-dd HH:mm:ss");// 设置日期格式
							String time = df.format(new Date());
							sd.setSub_time(time);
							sd.setNumber(number);
							// 设置这条记录的金额
							// 1.通过订单号和工序名称查询订单绑定工序表的单价
							BigDecimal price = place_tService.findByPandT(
									place_num, pt.getTechnology_name());
							BigDecimal shuliang = new BigDecimal(number);
							BigDecimal money = shuliang.multiply(price);// 乘
							sd.setMoney(money);
							// 设置工序单价
							sd.setPrice(price);
							// 保存提交记录
							int numx = sub_dataService.sub(sd);// 返回一个id
							if (numx > 0) {
								// 1.改变包号绑定工序表单的工序状态为2已完成
								state = 2;
								int num = packe_tService.updatePt(id, state);
								if (num > 0) {
									// 2.改变包号表的已完成个数
									// 改变记录
									int num2 = codeService.updatePd(packe_num);
									if (num2 > 0) {
										// 所有操作都成功 返回提交成功信息
										ServletActionContext
												.getResponse()
												.sendRedirect(
														WeiXinUtil.ip
																+ "jsp/weixin/success.jsp#t_name="+t_nams+";place_num="+place_num+";packe_num="+packe_num);
										return null;
									} else {
										yuan = "修改"+packe_num+"包的完成进度失败！";
										yuan = URLEncoder.encode(yuan.toString(),"UTF-8");
										state = 1;
										packe_tService.updatePt(id, state);// 操作失败
																			// 把数据还原
										sub_dataService.delete(numx);// 修改失败
																		// 删除提交数据
										ServletActionContext
												.getResponse()
												.sendRedirect(
														WeiXinUtil.ip
																+ "jsp/weixin/erro.jsp?yuan="+yuan);
										return null;
									}
								} else {
									yuan = "修改"+packe_num+"包的"+t_name+"工序状态失败！";
									yuan = URLEncoder.encode(yuan.toString(),"UTF-8");
									sub_dataService.delete(numx);// 修改失败 删除提交数据
									ServletActionContext
											.getResponse()
											.sendRedirect(
													WeiXinUtil.ip
															+ "jsp/weixin/erro.jsp?yuan="+yuan);
									return null;
								}
							}else{
								yuan = "提交记录提交失败！";
								yuan = URLEncoder.encode(yuan.toString(),"UTF-8");
								ServletActionContext
									.getResponse()
									.sendRedirect(
											WeiXinUtil.ip
													+ "jsp/weixin/erro.jsp?yuan="+yuan);
							return null;
							}
						} else {
							yuan = "提交记录提交失败！已经有人提交"+packe_num+"包的"+t_name+"工序";
							yuan = URLEncoder.encode(yuan.toString(),"UTF-8");
							ServletActionContext.getResponse().sendRedirect(
									WeiXinUtil.ip + "jsp/weixin/erro.jsp?yuan="+yuan);

							return null;
						}
					} else {
						yuan = packe_num+"包不存在已被删除或者"+t_name+"工序已完成！";
						yuan = URLEncoder.encode(yuan.toString(),"UTF-8");
						ServletActionContext.getResponse().sendRedirect(
								WeiXinUtil.ip + "jsp/weixin/erro.jsp?yuan="+yuan);

						return null;
					}
				} finally {
					s_lock.unlock();
				}
			}
			yuan = "提交"+packe_num+"包的"+t_name+"工序时发生不可预知的错误，尽快联系开发人员修正，请谅解！";
			yuan = URLEncoder.encode(yuan.toString(),"UTF-8");
			ServletActionContext.getResponse().sendRedirect(
					WeiXinUtil.ip + "jsp/weixin/erro.jsp?yuan="+yuan);
			return null;
		} else {
			yuan = "提交"+packe_num+"包的"+t_name+"工序时发生不可预知的错误，尽快联系开发人员修正，请谅解！";
			yuan = URLEncoder.encode(yuan.toString(),"UTF-8");
			ServletActionContext.getResponse().sendRedirect(
					WeiXinUtil.ip + "jsp/weixin/erro.jsp?yuan="+yuan);
			return null;
		}
	}
	
	public String getSelect() throws Exception{
		JSONObject json = new JSONObject();
		if(biao!=2){
			//查询出所有订单的订单号
			List<place> pList = placeService.getAllpnum();
			json.put("pList", pList);
		}
		//根据订单号查询 包绑定的工序
		List<place_technology> ptList = place_tService.getAllpt(place_num);
		json.put("ptList", ptList);
		ResUtil.write(json, ServletActionContext.getResponse());
		return null;
	}
	
	public String demo() throws IOException {
		ServletActionContext.getResponse().sendRedirect(
				WeiXinUtil.ip + "jsp/weixin/demo.jsp");
		return null;
		
	}

	public static void main(String[] args) {
	}
	

}
