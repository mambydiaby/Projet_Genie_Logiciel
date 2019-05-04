package com.flight_sharing.dao;

import java.net.InetAddress;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
/**
 * 
 * Create different Dao and start a client for the futher search;
 * the factory in this factory method pattern,we create all Dao objects from this class.
 *
 */
public class FactoryDao {
	public static final int FLIGHT = 1;
	public static final int PILOT = 2;
	public static final int PASSENGER = 3;
	public static final int AIRPORT = 4;
	public static final int RESERVATION = 5;
	public static final int AIRPLANE = 6;
	/**
	 * transport client for elastic search: one client for all DAO;
	 */
	private static TransportClient tc = getClient();

	/**
	 *  Create a TransportClient
	 * @return a new TransportClient
	 */
	@SuppressWarnings({ "resource" })
	public static TransportClient getClient() {
		TransportClient client = null;
		try {
			Properties elasticProperties = new Properties();
			elasticProperties.load(BasicDao.class.getResourceAsStream("elastic.properties"));
			String serverAddr = elasticProperties.getProperty("addr");
			if (serverAddr == null) {
				serverAddr = "localhost";
			}
			// on startup
			client = new PreBuiltTransportClient(Settings.EMPTY)
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(serverAddr), 9300));

		} catch (NullPointerException e1) {//if can't read file
			String serverAddr = "localhost";
			try {
				client = new PreBuiltTransportClient(Settings.EMPTY)
						.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(serverAddr), 9300));
			} catch (Exception e) {

				registerException(e);
			}

		} catch (Exception e) {

			registerException(e);
		}
		return client;
	}
	/**
	 * Exceptions caused by Dao
	 * @param e
	 */
	private static void registerException(Exception e) {
		Logger.getLogger(BasicDao.class.getName()).log(Level.SEVERE, null, e);
	}

	/**
	 * Create Action Dao Same client to prevent too many sockets
	 * 
	 * @param id : index
	 * @return an Dao object to search in Database
	 */
	public static ActionDao createDAO(int id) {
		switch (id) {
		case FLIGHT:
			return new ActionDao("flight", tc);
		case PILOT:
			return new ActionDao("pilote", tc);
		case PASSENGER:
			return new ActionDao("passenger", tc);
		case AIRPORT:
			return new ActionDao("airport", tc);
		case RESERVATION:
			return new ActionDao("reservation", tc);
		case AIRPLANE:
			return new ActionDao("airplane", tc);
		default:
			return null;
		}
	}
}
