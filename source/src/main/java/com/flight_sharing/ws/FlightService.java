package com.flight_sharing.ws;

import java.util.List;

import javax.ws.rs.Consumes;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.flight_sharing.entities.Flight;
import com.flight_sharing.json.ConvertObject;

@Path("/flight")
public class FlightService extends Service {

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
				searchBuilder.must(QueryBuilders.rangeQuery("date").from(date).to(date + "T00:59:00"));
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
			return "{\"result: \":\"cant find !\"}";
		}
	}

	/**
	 * web service method to delete the flight that corresponds to id
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/delete/{id}")
	public String deleteById(@PathParam("id") String id) {
		if (!(IsLogged() && isPilot()))
			return "{\"result: \":\"Please Login as Pilot!\"}";

		String result = null;

		try {
			result = flightDao.delete(id);
		} catch (Exception e) {
			registerException(e);
		}

		if (result.equals("OK")) {
			return "{\"deleteResult: \":\"success !\"}";
		} else {
			return "{\"deleteResult: \":\"error \"}";
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("add")
	public String addFlight(Flight flight) throws Exception {

		if (!IsLogged())
			return "{\"result: \":\"Please Login !\"}";

		String result = "";
		try {
			result = flightDao.add(ConvertObject.objectToByte(flight), flight.getId());
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