package com.hakerthon.common;

import java.util.Properties;


public class CommonUtil {

	public static final Properties p = new Properties();

	static {
		try {
			p.load(QueryUtil.class.getResourceAsStream("../config/config.properties"));
		} catch (Exception e) {
			
		}
	}
}
