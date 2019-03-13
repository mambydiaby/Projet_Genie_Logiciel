package com.flight_sharing.reminder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flight_sharing.dao.ActionDao;
import com.flight_sharing.dao.FactoryDao;
import com.flight_sharing.entities.Flight;
import com.flight_sharing.entities.Passenger;
import com.flight_sharing.entities.Pilot;
import com.flight_sharing.mail.Email;

public class SendEmail implements Job {

	public void send() throws Exception {
		// get the fligth list
		List<Flight> flightList = FlightList.flight;

		ActionDao pilotDao = FactoryDao.createDAO(FactoryDao.PILOT);
		ActionDao passengerDao = FactoryDao.createDAO(FactoryDao.PASSENGER);
		ObjectMapper mapper = new ObjectMapper();

		if (!flightList.isEmpty()) {

			List<String> cc = new ArrayList<String>();
			String to;
			for (int i = 0; i < flightList.size(); i++) {

				if (flightList.get(i).getTime()
						.equals(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")).toString())) {

					String pilot = pilotDao.getById(flightList.get(i).getPilotId());
					Pilot pl = mapper.readValue(pilot, Pilot.class);
					to = pl.getEmail();

					for (String pid : flightList.get(i).getPassengerId()) {
						String pajson = passengerDao.getById(pid);
						Passenger pa = mapper.readValue(pajson, Passenger.class);
						cc.add(pa.getEmail());
					}
					Email.send(to, cc, "rappel", "vous avez un vol demain à " + flightList.get(i).getTime());

				}

			}

		}
	}

	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			this.send();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			registerException(e);
		}
	}
	
	private static void registerException(Exception e) {
		Logger.getLogger(SendEmail.class.getName()).log(Level.SEVERE, null, e);
	}
}