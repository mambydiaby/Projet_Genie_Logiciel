package com.flight_sharing.reminder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilders;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.flight_sharing.entities.Flight;
import com.flight_sharing.json.ConvertObject;

public class FlightList extends Common implements Job {

	/**
	 * initialize list with the next day flights
	 */
	public void initialize() {
		try {
			// get the next day's flights list
			List<String> nextDayFlights = flightDao.search(QueryBuilders.matchPhraseQuery("date",
					LocalDateTime.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString()));
			
			for (String string : nextDayFlights) {
				flight.add((Flight) ConvertObject.JsonToObject(string, ConvertObject.FLIGHT));
			}
		} catch (Exception e) {
			registerException(e);
		}

	}

	public void execute(JobExecutionContext context) throws JobExecutionException {
		this.initialize();
	}
	
}
