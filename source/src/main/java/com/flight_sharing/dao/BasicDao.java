package com.flight_sharing.dao;

<<<<<<< HEAD

=======
>>>>>>> c064310ace455292d0d89eeb5631c383720a3eee
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

<<<<<<< HEAD


=======
>>>>>>> c064310ace455292d0d89eeb5631c383720a3eee
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
