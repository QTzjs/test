package com.happy.util;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent sce) {

	}

	public void contextInitialized(ServletContextEvent sce) {
		ServletContext servletContext = sce.getServletContext();
		try {
			PropertiesUtils.loadProperties("classpath:zc.properties");
			//servletContext.setAttribute("imageWebroot", ApplicationUtil.getImageWebroot());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
