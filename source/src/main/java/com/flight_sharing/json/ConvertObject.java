package com.flight_sharing.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertObject {
	private static ObjectMapper objectMapper = new ObjectMapper();

	public static byte[] ObjectToByte(Object object) throws JsonProcessingException {
			return objectMapper.writeValueAsBytes(object);
	}

}
