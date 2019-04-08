package com.flight_sharing.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.flight_sharing.entities.Passenger;
import com.flight_sharing.json.ConvertObject;

public class FillData {

	public static void addPassenger() throws JsonProcessingException, Exception {
		ActionDao passengerDao = FactoryDao.createDAO(FactoryDao.PASSENGER);
		Passenger[] p = new Passenger[2];
		p[0] = new Passenger("azerty", "544", "Balde", "mamadou", "mybalde@test.com");
		p[1] = new Passenger("jhuy7x", "544", "Jules", "césar", "alexandre.dai@live.fr");
		for (Passenger passenger : p) {
			passengerDao.add(ConvertObject.objectToByte(passenger), passenger.getId());
		}

	}
	
	public static void main(String[] args) throws JsonProcessingException, Exception {
		addPassenger();
	}
}
