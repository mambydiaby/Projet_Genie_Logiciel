package com.flight_sharing.reminder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.elasticsearch.index.query.QueryBuilders;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flight_sharing.dao.ActionDao;
import com.flight_sharing.dao.FactoryDao;
import com.flight_sharing.entities.Flight;

public class FlightList implements Job {
	/** list of next day flight */
	public static List<Flight> flight;

	/**
	 * initialize list with the next day flights
	 */
	public void initialize() {
		ActionDao flightDao = FactoryDao.createDAO(FactoryDao.FLIGHT);
		flight = new ArrayList<Flight>();
		try {
			// get the next day's flights list
			List<String> nextDayFlights = flightDao.search(QueryBuilders.matchPhraseQuery("date",
					LocalDateTime.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString()));
			
			// convert json list to Flight list
			ObjectMapper mapper = new ObjectMapper();
			for (String string : nextDayFlights) {
				flight.add(mapper.readValue(string, Flight.class));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			registerException(e);
		}

	}

	public void execute(JobExecutionContext context) throws JobExecutionException {
		this.initialize();
	}
	
	private static void registerException(Exception e) {
		Logger.getLogger(FlightList.class.getName()).log(Level.SEVERE, null, e);
	}
}
