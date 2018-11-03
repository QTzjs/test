package com.happy.util;

import java.io.File;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
*此拦截器设置在获取样本图片上
*/
public class PreviewUploadInterceptor extends AbstractInterceptor {
    private static final long serialVersionUID = 4829071700747547993L;
    public static final String filepath="tmp";
     
 
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
      
        ActionContext ac = invocation.getInvocationContext();
         
        Map<String, Object> params = ac.getParameters();
        String[] values = (String[])params.get("fileName");
        ServletContext servletContext = (ServletContext) ac.get(StrutsStatics.SERVLET_CONTEXT);
        String filename=servletContext.getRealPath(filepath)+System.getProperty("file.separator")+values[0];
         
        // invoke action
        String result = invocation.invoke();
         
        File file=new File(filename);
        if(file.isFile() && file.exists()){
            file.delete();
           
        }
         
        return result;
    }
}
