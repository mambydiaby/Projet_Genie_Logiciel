package com.flight_sharing.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flight_sharing.entities.Airplane;
import com.flight_sharing.entities.Airport;
import com.flight_sharing.entities.Flight;
import com.flight_sharing.entities.Passenger;
import com.flight_sharing.entities.Pilot;
import com.flight_sharing.entities.Reservation;

public class ConvertObject {
	public static final int FLIGHT = 1;
	public static final int PILOT = 2;
	public static final int PASSENGER = 3;
	public static final int AIRPORT = 4;
	public static final int RESERVATION = 5;
	public static final int AIRPLANE = 6;
	private static ObjectMapper objectMapper = new ObjectMapper();

	public static byte[] objectToByte(Object object) throws JsonProcessingException {
		return objectMapper.writeValueAsBytes(object);
	}

	public static Object jsonToObject(String json, int id)
			throws JsonParseException, JsonMappingException, IOException {
		switch (id) {
		case FLIGHT:
			return objectMapper.readValue(json, Flight.class);
		case PILOT:
			return objectMapper.readValue(json, Pilot.class);
		case PASSENGER:
			return objectMapper.readValue(json, Passenger.class);
		case AIRPORT:
			return objectMapper.readValue(json, Airport.class);
		case RESERVATION:
			return objectMapper.readValue(json, Reservation.class);
		case AIRPLANE:
			return objectMapper.readValue(json, Airplane.class);
		default:
			return null;
		}
	}
}
