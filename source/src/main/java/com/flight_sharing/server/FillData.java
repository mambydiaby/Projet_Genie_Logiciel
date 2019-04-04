package com.flight_sharing.server;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Files;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import com.flight_sharing.ws.Service;

public class FillData {
	/**
	 * pre-fill some flights, airports,planes,pilots,
	 */
	public static void fill() {
		Service s=new Service();
		try {

			File jsonDir = new File("data");
			File[] filesPlane = jsonDir.listFiles(
					(dir, name) -> {
						return name.toLowerCase().startsWith("plane")&&name.toLowerCase().endsWith(".json");
					}
					);
			File[] filesAirport = jsonDir.listFiles(
					(dir, name) -> {
						return name.toLowerCase().startsWith("airport")&&name.toLowerCase().endsWith(".json");
					}
					);
			File[] filesPilot = jsonDir.listFiles(
					(dir, name) -> {
						return name.toLowerCase().startsWith("pilot")&&name.toLowerCase().endsWith(".json");
					}
					);
			File[] filesFlight = jsonDir.listFiles(
					(dir, name) -> {
						return name.toLowerCase().startsWith("flight")&&name.toLowerCase().endsWith(".json");
					}
					);
			
			for(int i=0;i<filesPlane.length;i++)
				s.airplaneDao.fillData(filesPlane[i]);
			for(int  i=0;i<filesAirport.length;i++)
				s.airportDao.fillData(filesAirport[i]);
			for(int  i=0;i<filesPilot.length;i++)
				s.pilotDao.fillData(filesPilot[i]);
			for(int  i=0;i<filesFlight.length;i++)
				s.flightDao.fillData(filesFlight[i]);
			
			s.airplaneDao.closeClient();
			s.airportDao.closeClient();
			s.flightDao.closeClient();
			s.passengerDao.closeClient();
			s.pilotDao.closeClient();
			s.reservationDao.closeClient();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
