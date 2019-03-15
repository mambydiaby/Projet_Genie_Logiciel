package com.flight_sharing.reminder;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.flight_sharing.dao.ActionDao;
import com.flight_sharing.dao.FactoryDao;
import com.flight_sharing.entities.Flight;

public class Common {
	protected ActionDao flightDao = FactoryDao.createDAO(FactoryDao.FLIGHT);
	protected ActionDao pilotDao = FactoryDao.createDAO(FactoryDao.PILOT);
	protected ActionDao passengerDao = FactoryDao.createDAO(FactoryDao.PASSENGER);
	/** list of next day flight */
	protected static List<Flight> flight = new ArrayList<Flight>();

	protected void registerException(Exception e) {
		Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
	}
}
