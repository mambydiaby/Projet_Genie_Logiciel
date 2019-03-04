package com.flight_sharing.alert;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilders;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flight_sharing.dao.ActionDao;
import com.flight_sharing.dao.FactoryDao;
import com.flight_sharing.entities.Flight;
import com.flight_sharing.entities.Passenger;
import com.flight_sharing.entities.Pilote;
import com.flight_sharing.mail.Email;

public class RemindJob implements Job {
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("rapel lancé");
		List<Flight> flight = getFlight();
		List<String> email = new ArrayList<String>();
		for (Flight f : flight) {
			try {
				email=getEmail(f);
				
				String to = email.get(0);
				email.remove(0);
				Email.send(to, email, "rappel", "vous avez un vol demain");
				System.out.println("rdv dans 1h");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private List<Flight> getFlight() {
		ActionDao flightDao = FactoryDao.createDAO(FactoryDao.FLIGHT);
		List<Flight> flight = new ArrayList<Flight>();
		try {
			// date's format
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			// get the next day's flights list
			List<String> nextDayFlights = flightDao
					.searchByPages(QueryBuilders.matchQuery("date", LocalDateTime.now().plusDays(1).format(formatter)));
			// convert json list to Flight list
			ObjectMapper mapper = new ObjectMapper();
			for (String string : nextDayFlights) {
				flight.add(mapper.readValue(string, Flight.class));
			}

			LocalDateTime date;
			Duration duration = null;
			for (Flight f : flight) {
				date = LocalDateTime.parse(f.getDate(), formatter);
				duration = Duration.between(date, LocalDateTime.now());
				long diff = Math.abs(duration.toHours());
				if (!(date.isAfter(LocalDateTime.now()) && diff < 23 && 22 < diff)) {
					flight.remove(f);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flight;
	}
	
	public List<String> getEmail(Flight f) throws Exception{
		ActionDao piloteDao = FactoryDao.createDAO(FactoryDao.PILOTE);
		ActionDao passengerDao = FactoryDao.createDAO(FactoryDao.PASSENGER);
		ObjectMapper mapper = new ObjectMapper();
		List<String> email = new ArrayList<String>();
		String pljson= piloteDao.getById(f.getPiloteId());
		Pilote pl = mapper.readValue(pljson, Pilote.class);
		email.add(pl.getEmail());
		for (String pid : f.getPassengerId()) {
			String pajson=passengerDao.getById(pid);
			Passenger pa = mapper.readValue(pajson, Passenger.class);
			email.add(pa.getEmail());
		}
		
		return email;
	}

}