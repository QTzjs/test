package com.happy.advice;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class NotifyUrlReturn {
	static ApplicationContext ac = null;
	static NamedParameterJdbcTemplate namedParameterJdbcTemplate = null;
	static {
		ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		namedParameterJdbcTemplate = (NamedParameterJdbcTemplate) ac
				.getBean("namedParameterJdbcTemplate");
	}
}
