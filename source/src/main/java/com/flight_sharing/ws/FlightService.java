package com.flight_sharing.ws;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.flight_sharing.dao.ActionDao;
import com.flight_sharing.dao.FactoryDao;

@Path("flight")
public class FlightService {

	ActionDao flightDao = FactoryDao.createDAO(FactoryDao.FLIGHT);

	@Context
	HttpServletRequest request;

	private boolean getLoginState() {
		return request.getSession().getAttribute("userid") == null
				|| request.getSession().getAttribute("userid").toString() == "";
	}
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getByID/{id}")
    public String getByID(@PathParam("id") String id) {
		
        String result = null;
        try {
            result = flightDao.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result != null) {
            return result;
        } else {
            return "{\"result: \":\"cant find !\"}";
        }
    }
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/delete/{id}")
    public String deleteByID(@PathParam("id") String id) {
        if (!getLoginState()) {
            return "{\"result: \":\"Please Login !\"}";
        }
        
        String result = null;
        System.out.println("=========" + id);
        try {
            result = flightDao.delete(id);
            System.out.println("execute delete");
            System.out.println("result   " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result.equals("OK")) {
            return "{\"deleteResult: \":\"ok\"}";
        } else {
            return "{\"deleteResult: \":\"error :(\"}";
        }
    }
	
	
}
