package com.flight_sharing.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.flight_sharing.entities.Airplane;
import com.flight_sharing.json.ConvertObject;

@Path("airplane")
public class AirplaneService extends Service {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/add")
	public String add(Airplane ap) throws Exception {
		if (!IsLogged())
			return "{\"result: \":\"Please Login !\"}";
		String result = "";
		result = airplaneDao.add(ConvertObject.objectToByte(ap), ap.getId());

		if (result.equals("OK"))
			return "{\"addResult: \":\"success !\"}";
		else
			return "{\"addResult: \":\"error \"}";
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getbyid/{id}")
	public String getById(@PathParam("id") String id) {
		String result = null;
		try {
			result = airplaneDao.getById(id);
		} catch (Exception e) {
			registerException(e);
		}
		if (result != null) {
			return result;
		} else {
			return "{\"result: \":\"cant find !\"}";
		}
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/delete/{id}")
	public String deleteById(@PathParam("id") String id) {
		if (!(IsLogged() && isPilot()))
			return "{\"result: \":\"Please Login as Pilot!\"}";
		String result = null;

		try {
			result = airplaneDao.delete(id);
		} catch (Exception e) {
			registerException(e);
		}

		if (result.equals("OK")) {
			return "{\"deleteResult: \":\"success !\"}";
		} else {
			return "{\"deleteResult: \":\"error \"}";
		}
	}

}
