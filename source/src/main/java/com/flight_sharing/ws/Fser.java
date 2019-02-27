package com.flight_sharing.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.elasticsearch.index.query.QueryBuilders;

import com.flight_sharing.dao.ActionDao;
import com.flight_sharing.dao.FactoryDao;

@Path("/flight")
public class Fser {

	ActionDao fDao = FactoryDao.createDAO(1);
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("application/x-www-form-urlencoded")
	@Path("search")
	public List<String> login(@FormParam("departure") String departure, @FormParam("date") String date) throws Exception {
		return fDao.searchByPages(QueryBuilders.termQuery("departure", departure));
	}
}
