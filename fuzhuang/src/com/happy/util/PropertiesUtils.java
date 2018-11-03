package com.happy.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;

/**
 * 
 * @author gongwei 获取properties文件信息的工具类
 * 
 */
public class PropertiesUtils {

	private static final String DEFAULT_ENCODING = "UTF-8";

	private static Logger logger = LoggerFactory
			.getLogger(PropertiesUtils.class);

	private static PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();
	private static ResourceLoader resourceLoader = new DefaultResourceLoader();
	private static Properties properties = new Properties();

	/**
	 * 载入多个properties文件, 相同的属性在�?��载入的文件中的�?将会覆盖之前的载�?
	 * 
	 */
	public static void loadProperties(String... resourcesPaths)
			throws IOException {
		for (String location : resourcesPaths) {
			logger.debug("Loading properties file from:" + location);
			InputStream is = null;
			try {
				Resource resource = resourceLoader.getResource(location);
				is = resource.getInputStream();
				propertiesPersister.load(properties, new InputStreamReader(is,
						DEFAULT_ENCODING));
			} catch (IOException ex) {
				logger.info("Could not load properties from classpath:"
						+ location + ": " + ex.getMessage());
			} finally {
				if (is != null) {
					is.close();
				}
			}
		}
	}

	public static String getValue(String key) {
		return properties.getProperty(key);
	}
}
