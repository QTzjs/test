package com.happy.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Administrator
 */
public class EmailSend {
//发送的邮箱名
//public static String mailUserName = "jxwtbcs@163.come";
public static String mailUserName = "register@jft.net.cn";
//密码
public static String mailPassword = "123456";
//public static String mailPassword = "226889&leng";
//邮箱服务器
public static String mailServerHost = "mail.jft.net.cn";
//public static String mailServerHost = "smtp.163.com";
//端口
public static int mailServerPort = 25;
    private static Log log = LogFactory.getLog(EmailSend.class);
    public boolean SendMail(String mailAddress, String mailTitle, String mailContent) {
        
        boolean flag = false;
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", mailServerHost);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.transport.protocol", mailServerPort);
        props.put("mail.smtp.auth", "true");
        System.setProperties(props);
        final String username = mailUserName;//登录用户名
        final String password = mailPassword;//登录密码
        //使用验证

        Session javasession1 = Session.getInstance(props,
                new Authenticator() {

                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new javax.mail.PasswordAuthentication(username, password);
                    }
                });

        MimeMessage message = new MimeMessage(javasession1);
        Address address;
        try {
            address = new InternetAddress(mailUserName, "众超");//邮箱名称
            Address toAaddress = new InternetAddress(mailAddress);//用户邮箱地址
            String nick="";
			nick=javax.mail.internet.MimeUtility.encodeText("众超");
           //  message.setFrom(address);
            message.setFrom(new InternetAddress(nick+"<"+username+">"));
            message.setRecipient(MimeMessage.RecipientType.TO, toAaddress);
            message.setSubject(mailTitle);
            message.setText(mailContent);


            message.setSentDate(new Date());
            javax.mail.Transport.send(message);
            //保存发�?邮件地址
            Date d = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            flag = true;
        } catch (Exception ex) {
            flag = false;
        }
        return flag;
    }
    public static void main(String[] args) {
        EmailSend ms = new EmailSend();
        try {
            String mailTitle = "众超对您邮箱发�?的验证码";
        String    mailContent = "欢迎您使用邮箱号密码找回服务，验证码" + ms.getRandNum(6) + "验证码在当前页面有效，谢谢合作";
         boolean re=ms.SendMail("694182105@qq.com", mailTitle, mailContent);
         //System.out.print(re);
        } catch (Exception ex) {
        }
    }
    
    public String getRandNum(int bitNum) {
        String sRand = "";//6位随机码

        for (int i = 0; i < bitNum; i++) {
            //产生10以内随机数字rn
            Random r = new Random();
            sRand += String.valueOf(r.nextInt(10));
        }
        return sRand;
    }
}
