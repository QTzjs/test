package com.happy.model.weixin;
/**
 * 生成签名实体类
 * @author QT-666
 *
 */
public class Signature {
    private String noncestr;//随机字符串
    private String jsapi_ticket;//调用js凭证
    private String timeStamp;//时间戳
    private String url;//支付页面地址
    private String signature;//签名
    
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getNoncestr() {
		return noncestr;
	}
	public void setNoncestr(String noncestr) {
		this.noncestr = noncestr;
	}
	public String getJsapi_ticket() {
		return jsapi_ticket;
	}
	public void setJsapi_ticket(String jsapi_ticket) {
		this.jsapi_ticket = jsapi_ticket;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "Signature [noncestr=" + noncestr + ", jsapi_ticket="
				+ jsapi_ticket + ", timeStamp=" + timeStamp + ", url=" + url
				+ ", signature=" + signature + "]";
	}
	
}
