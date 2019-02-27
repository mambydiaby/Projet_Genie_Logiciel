package com.flight_sharing.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flight_sharing.entities.*;

public class ConvertObject {
	private static ObjectMapper objectMapper = new ObjectMapper();

	public static byte[] ObjectToByte(Flight flight) throws JsonProcessingException {
		return objectMapper.writeValueAsBytes(flight);
	}

	public static byte[] ObjectToByte(Pilote pilote) throws JsonProcessingException {
		return objectMapper.writeValueAsBytes(pilote);
	}

	public static byte[] ObjectToByte(Passenger passenger) throws JsonProcessingException {
		return objectMapper.writeValueAsBytes(passenger);
	}

}
