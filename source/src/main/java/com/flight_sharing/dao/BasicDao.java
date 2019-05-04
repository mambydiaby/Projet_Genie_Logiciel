package com.flight_sharing.dao;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Give Dao object an index 
 *
 */
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
				index = "flight_shring";
			}
			// can't open/read file

		} catch (NullPointerException e1) {
			index = "flight_sharing";
			serverAddr = "localhost";
		} catch (Exception e) {

			registerException(e);
		}
	}

	private static void registerException(Exception e) {
		Logger.getLogger(BasicDao.class.getName()).log(Level.SEVERE, null, e);
	}
}
