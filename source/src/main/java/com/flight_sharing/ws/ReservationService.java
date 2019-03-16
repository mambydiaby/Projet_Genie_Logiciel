package com.flight_sharing.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.elasticsearch.index.query.QueryBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.flight_sharing.entities.Flight;
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
			result = reservationDao.add(ConvertObject.objectToByte(r), r.getId());
		} catch (JsonProcessingException e) {
			registerException(e);
		}
		if (result.equals("OK")) {
			return "{\"addResult: \":\"success !\"}";
		} else {
			return "{\"addResult: \":\"error \"}";
		}
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("application/x-www-form-urlencoded")
	@Path("/approve/{id}")
	public String approve(@PathParam("id") String id) throws Exception {

		if (!(IsLogged() && isPilot()))
			return "{\"result: \":\"Please Login !\"}";
		Reservation rt = (Reservation) ConvertObject.jsonToObject(reservationDao.getById(id),
				ConvertObject.RESERVATION);
		if (rt == null || rt.isApproved())
			return "{\"result: \":\"error\"}";
		Flight flight = (Flight) ConvertObject.jsonToObject(flightDao.getById(rt.getFlightId()), ConvertObject.FLIGHT);
		if (flight == null)
			return "{\"result: \":\"error\"}";
		flight.setSeat(flight.getSeat() - rt.getSeat());
		flight.getPassengerId().add(rt.getPassengerId());
		flightDao.add(ConvertObject.objectToByte(flight), flight.getId());
		reservationDao.update(id, "approved", "true");
		return "{\"result: \":\"ok\"}";
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/disapprove/{id}")
	public String disapprove(@PathParam("id") String id) throws Exception {

		if (!(IsLogged() && isPilot()))
			return "{\"result: \":\"Please Login !\"}";

		reservationDao.delete(id);

		return "{\"result: \":\"ok \"}";
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("application/x-www-form-urlencoded")
	@Path("/toapprove/{piloteId}")
	public List<String> toApprove(@PathParam("piloteId") String piloteId) throws Exception {
		if (!(IsLogged() && isPilot()))
			return null;
		List<String> rtList = reservationDao
				.search(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("approved", "false"))
						.mustNot(QueryBuilders.termQuery("approved", "true")));

		if (!rtList.equals(null))
			for (int i = 0; i < rtList.size(); i++) {
				Reservation rt = (Reservation) ConvertObject.jsonToObject(rtList.get(i), ConvertObject.RESERVATION);
				if (rt == null)
					return null;
				Flight flight = (Flight) ConvertObject.jsonToObject(flightDao.getById(rt.getFlightId()),
						ConvertObject.FLIGHT);
				if (flight != null && !flight.getPilotId().equals(piloteId))
					rtList.remove(i);

			}

		return rtList;
	}
}
