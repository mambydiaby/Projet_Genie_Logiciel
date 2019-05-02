package com.flight_sharing.dao;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class BasicDao implements Dao {
	/* index of tables */
	public static String index;
	/* address of the server */
	protected static String serverAddr;
	/* setting elastic properties */
	static {
		try {
			Properties elasticProperties = new Properties();
			elasticProperties.load(BasicDao.class.getResourceAsStream("elastic.properties"));
			serverAddr = elasticProperties.getProperty("addr");
			index = elasticProperties.getProperty("index");
			if (index == null || index.length() == 0) {
				index = "default";
			}
			//sometimes happens 

		} catch(NullPointerException e1) {
			index = "default";
			serverAddr="localhost";
		}catch (Exception e) {
			
			registerException(e);
		}
	}


	private static void registerException(Exception e) {
		Logger.getLogger(BasicDao.class.getName()).log(Level.SEVERE, null, e);
	}
}
