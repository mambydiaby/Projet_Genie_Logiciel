package com.flight_sharing.ws;

import java.time.LocalDateTime;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import com.flight_sharing.entities.Flight;
import com.flight_sharing.entities.Passenger;
import com.flight_sharing.entities.Reservation;
import com.flight_sharing.mail.Email;
import com.flight_sharing.mapping.ConvertObject;

@Path("flight")
public class FlightService extends Service {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("application/x-www-form-urlencoded")
	@Path("/filter")
	public List<String> searchDetail(@FormParam("departure") String departure, @FormParam("arrival") String arrival,
			@FormParam("seat") int seat, @FormParam("date") String date) {
		List<String> result = null;
		try {
			BoolQueryBuilder searchBuilder = QueryBuilders.boolQuery();

			if (!departure.isEmpty()) {
				searchBuilder.must(QueryBuilders.wildcardQuery("departure", "*" + departure.toLowerCase() + "*"));
			}
			if (!date.isEmpty()) {
				searchBuilder.must(QueryBuilders.rangeQuery("date").from(LocalDateTime.now().toString()).to(date));
			}
			if (seat != 0)
				searchBuilder.must(QueryBuilders.rangeQuery("seat").from(seat));

			if (!arrival.isEmpty())
				searchBuilder.must(QueryBuilders.wildcardQuery("arrival", "*" + arrival.toLowerCase() + "*"));
			result = flightDao.search(searchBuilder);
			return result;
		} catch (Exception e) {
			registerException(e);
		}
		return result;
	}

	/**
	 * 
	 * search flights only with departure and date
	 * 
	 * @param departure
	 * @param date
	 * @return
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("application/x-www-form-urlencoded")
	@Path("/search")
	public List<String> searchDefault(@FormParam("departure") String departure, @FormParam("date") String date) {
		List<String> result = null;
		try {
			BoolQueryBuilder searchBuilder = QueryBuilders.boolQuery();
			if (!departure.isEmpty()) {
				searchBuilder.must(QueryBuilders.wildcardQuery("departure", "*" + departure.toLowerCase() + "*"));
			}
			if (!date.isEmpty()) {
				searchBuilder.must(QueryBuilders.rangeQuery("date").from(LocalDateTime.now().toString()).to(date));
			}
			searchBuilder.must(QueryBuilders.rangeQuery("seat").from(1));
			result = flightDao.search(searchBuilder);
			return result;
		} catch (Exception e) {
			registerException(e);
		}
		return result;
	}

	/**
	 * web services to get all my flights
	 * 
	 * @param id
	 * @return
	 */

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("application/x-www-form-urlencoded")
	@Path("/myflights")
	public List<String> searchMyFlights(@FormParam("id") String id) {

		List<String> result = null;
		try {
			BoolQueryBuilder searchBuilder = QueryBuilders.boolQuery();
			if (!id.isEmpty())
				searchBuilder.must(QueryBuilders.termQuery("pilotId", id));

			result = flightDao.search(searchBuilder);
			return result;
		} catch (Exception e) {
			registerException(e);
		}
		return result;
	}

	/**
	 * web service to get more detailed information about the flight
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getbyid/{id}")
	public String getById(@PathParam("id") String id) {
		String result = null;
		try {
			result = flightDao.getById(id);
		} catch (Exception e) {
			registerException(e);
		}
		if (result != null) {
			return result;
		} else {
			return "{\"result\":\"cant find !\"}";
		}
	}

	/**
	 * web service method to delete the flight that corresponds to id
	 * 
	 * @param id
	 * @return
	 */
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/delete/{id}")
	public String deleteById(@PathParam("id") String id) {
		if (!(IsLogged() && isPilot()))
			return "{\"result\":\"Please Login as Pilot!\"}";

		String result = null;

		try {
			List<String> res = reservationDao.getAll();
			// delete flight -> delete reservations ...
			for (int i = res.size() - 1; i >= 0; i--) {
				Reservation rt = (Reservation) ConvertObject.jsonToObject(res.get(i), ConvertObject.RESERVATION);
				if (rt.getFlightId().equals(id)) {
					reservationDao.delete(rt.getId());
					Passenger pa = null;
					try {
						pa = (Passenger) ConvertObject.jsonToObject(passengerDao.getById(rt.getPassengerId()),
								ConvertObject.PASSENGER);
					} catch (Exception e) {
						pa = (Passenger) ConvertObject.jsonToObject(pilotDao.getById(rt.getPassengerId()),
								ConvertObject.PILOT);
					}

					String body = "Hello Mr/Mrs/Ms " + pa.getLastName() + ",<br/><br/>Your booking for the flight "
							+ rt.getFlightId()
							+ " has been canceled by the pilot.Sorry for any inconvenience caused <br/><br/>Best regards.";

					Email.send(pa.getEmail(), "Flight booking", body);
				}
			}
			result = flightDao.delete(id);
		} catch (Exception e) {
			registerException(e);
		}

		if (result.equals("OK")) {
			return "{\"result\":\"success !\"}";
		} else {
			return "{\"result\":\"error \"}";
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/add")
	public String addFlight(Flight flight) throws Exception {
		if (!IsLogged())
			return "{\"result: \":\"Please Login !\"}";
		String result = "";
		result = flightDao.add(ConvertObject.objectToByte(flight), flight.getId());
		if (result.equals("CREATED")) {
			return "{\"addResult: \":\"success !\"}";
		} else {
			return "{\"addResult: \":\"error \"}";
		}

	}
}