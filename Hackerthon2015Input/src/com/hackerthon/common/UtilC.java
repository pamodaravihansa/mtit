package com.hackerthon.common;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.util.Properties;


public class UtilC {
	
	private static UtilC utilC=new UtilC();
	
	protected UtilC() {}
	
	public static UtilC getUtilC() {
		return utilC;
		
	}
	public static final Properties property = new Properties();
	static Logger logger = Logger.getLogger(UtilC.class.getName());
	
	static {
		try {
			property.load(UtilQ.class.getResourceAsStream("../config/config.properties"));
			logger.log(Level.INFO, "Configuaration Propert File Loaded");
		} catch (IOException e) {
			logger.log(Level.SEVERE, "", e);
			e.printStackTrace();
		}
	}
}
