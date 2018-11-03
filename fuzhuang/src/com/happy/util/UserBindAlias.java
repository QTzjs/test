package com.happy.util;
import com.gexin.rp.sdk.base.IAliasResult;
import com.gexin.rp.sdk.http.IGtPush;
/**
 * @author Luois
 * 用户绑定别名
 * @time 2016年7月19日
 */
public class UserBindAlias {
	static String appId = "PYPq5twiSg69LrL66ffOm1";
	static String appkey = "5bSLJVaNLGAVplajnqUdT5";
	static String mastersecret = "RCRUSnzqvoAAJvg9WWnEs";
	static String host = "http://sdk.open.api.igexin.com/apiex.htm";
    public static void tobindAlias(String Alias, String CID) {	    
        IGtPush push = new IGtPush(host, appkey, mastersecret);
        IAliasResult bindSCid = push.bindAlias(appId, Alias, CID);
        //System.out.println("绑定结果：" + bindSCid.getResult() + "错误码:" + bindSCid.getErrorMsg());
    }

}
