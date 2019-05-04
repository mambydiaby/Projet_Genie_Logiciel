package com.flight_sharing.ws;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import com.flight_sharing.dao.ActionDao;
import com.flight_sharing.dao.FactoryDao;

/**
 * this class contains all the attributes and methods common to web sevices
 * classes
 */
public class Service {
	/** passenger dao */
	protected ActionDao passengerDao = FactoryDao.createDAO(FactoryDao.PASSENGER);
	/** pilot dao */
	protected ActionDao pilotDao = FactoryDao.createDAO(FactoryDao.PILOT);
	/** flight dao */
	protected ActionDao flightDao = FactoryDao.createDAO(FactoryDao.FLIGHT);
	/** reservation dao */
	protected ActionDao reservationDao = FactoryDao.createDAO(FactoryDao.RESERVATION);
	/** airport dao */
	protected ActionDao airportDao = FactoryDao.createDAO(FactoryDao.AIRPORT);
	/** airplane dao */
	protected ActionDao airplaneDao = FactoryDao.createDAO(FactoryDao.AIRPLANE);

	@Context
	protected HttpServletRequest request;

	/**
	 * login state
	 * 
	 * @return true or false
	 */
	protected boolean IsLogged() {
		return request.getSession().getAttribute("userId") != null;
	}

	/**
	 * check whether the user's a pilot
	 * 
	 * @return true or false
	 */
	protected boolean isPilot() {
		if (request.getSession().getAttribute("type") == null)
			return false;
		return request.getSession().getAttribute("type").equals("pilot");
	}

	/**
	 * check whether the user's a passenger
	 * 
	 * @return true or false
	 */
	protected boolean isPassenger() {
		if (request.getSession().getAttribute("type") == null)
			return false;
		return request.getSession().getAttribute("type").equals("passenger");
	}

	protected void registerException(Exception e) {
		Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
	}

}
