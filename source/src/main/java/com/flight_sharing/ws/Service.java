package com.flight_sharing.ws;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import com.flight_sharing.dao.ActionDao;
import com.flight_sharing.dao.FactoryDao;

public class Service {
	public ActionDao passengerDao = FactoryDao.createDAO(FactoryDao.PASSENGER);
	public ActionDao pilotDao = FactoryDao.createDAO(FactoryDao.PILOT);
	public ActionDao flightDao = FactoryDao.createDAO(FactoryDao.FLIGHT);
	public ActionDao reservationDao = FactoryDao.createDAO(FactoryDao.RESERVATION);
	public ActionDao airportDao = FactoryDao.createDAO(FactoryDao.AIRPORT);
	public ActionDao airplaneDao = FactoryDao.createDAO(FactoryDao.AIRPLANE);

	@Context
	protected HttpServletRequest request;

	protected boolean IsLogged() {
		return request.getSession().getAttribute("userId") != null;
	}
	
	protected boolean isPilot() {
		return request.getSession().getAttribute("type").equals("pilot");
	}
	
	protected boolean isPassenger() {
		return request.getSession().getAttribute("type").equals("passenger");
	}
	
	protected void registerException(Exception e) {
		Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
	}

}
