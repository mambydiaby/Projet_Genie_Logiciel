package com.flight_sharing.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.flight_sharing.entities.Reservation;
import com.flight_sharing.json.ConvertObject;

@Path("reservation")
public class ReservationService extends Service {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/new")
	public String add(Reservation r) throws Exception {
		if (!IsLogged())
			return "{\"result: \":\"Please Login !\"}";

		String result = "";
		try {
			result = reservationDao.add(ConvertObject.ObjectToByte(r), r.getId());
		} catch (JsonProcessingException e) {
			registerException(e);
		}
		if (result.equals("OK")) {
			return "{\"addResult: \":\"success !\"}";
		} else {
			return "{\"addResult: \":\"error \"}";
		}
	}
}
