package com.example.jetty_jersey.ws;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



@Path("/home")
public class Flights {
	
	
	public static class FlightsList{
		public ArrayList<String> flightslist = new ArrayList<String>();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/flights")
	public FlightsList getFlightsList() {
		FlightsList flights = new FlightsList();
		flights.flightslist.add("Vol 1516");
		flights.flightslist.add("Vol 4460");
		flights.flightslist.add("Vol 5594");
		flights.flightslist.add("Vol 1500");
		return flights;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/flights")
	public void retrieveFlightsList(String list) {
		System.out.println(list);
	}
	
	public static class SearchFlights {
		public ArrayList<String> searchflightslist = new ArrayList<String>();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/flights/search")
	public SearchFlights getSearchFlightsList() {
		SearchFlights search = new SearchFlights();
		search.searchflightslist.add("Vol Angers - Tanger");
		search.searchflightslist.add("Vol Dakar - Quimper");
		search.searchflightslist.add("Vol Irak - Washington");
		search.searchflightslist.add("Vol Paris - La Tess");
		return search;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/flights/search")
	public void retrieveSearchFlightsList(String search) {
		System.out.println(search);
	}
}


