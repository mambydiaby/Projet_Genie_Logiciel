package com.flight_sharing.reminder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.flight_sharing.entities.Flight;
import com.flight_sharing.entities.Passenger;
import com.flight_sharing.entities.Pilot;
import com.flight_sharing.json.ConvertObject;
import com.flight_sharing.mail.Email;

public class SendEmail extends Common implements Job {

	public void send() throws Exception {

		if (!flight.isEmpty()) {

			List<String> cc = new ArrayList<String>();
			String to;
			for (Flight ft : flight) {

				if (ft.getTime().equals(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")).toString())) {
					String pilot = pilotDao.getById(ft.getPilotId());
					Pilot pl = (Pilot) ConvertObject.JsonToObject(pilot, ConvertObject.PILOT);
					to = pl.getEmail();

					for (String pid : ft.getPassengerId()) {
						String pajson = passengerDao.getById(pid);
						Passenger pa = (Passenger) ConvertObject.JsonToObject(pajson, ConvertObject.PASSENGER);
						cc.add(pa.getEmail());
					}
					Email.send(to, cc, "rappel", "vous avez un vol demain à " + ft.getTime());

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


}