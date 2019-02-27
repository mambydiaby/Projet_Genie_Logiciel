package com.flight_sharing.ws;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flight_sharing.dao.ActionDao;
import com.flight_sharing.dao.FactoryDao;
import com.flight_sharing.entities.Passenger;
import com.flight_sharing.json.ConvertObject;

@Path("/user")
public class UserService {
	ActionDao userDAO = FactoryDao.createDAO(FactoryDao.PASSENGER);
	@Context
	HttpServletRequest request;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("application/x-www-form-urlencoded")
	@Path("login")
	public String login(@FormParam("id") String userid, @FormParam("pwd") String userpwd) {
		try {
			System.out.println(userid + "  ===  " + userpwd);

			if (userid == null || userpwd == null)
				return "{\"result\":\"error\"}";

			else {
				String userResult = userDAO.getById(userid);

				if (userResult == null)
					return "{\"result\":\"account or password incorrect :(\"}";

				System.out.println(userResult);
				ObjectMapper mapper = new ObjectMapper();
				Passenger p = mapper.readValue(userResult, Passenger.class);
				System.out.println(p.getPwd());
				if (p.getPwd().equals(userpwd)) {
					request.getSession().setAttribute("userid", p.getId());
					return "{\"result\":\"ok\"}";
				} else {
					return "{\"result\":\"account or password incorrect :(\"}";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{\"result\":\"login error500\"}";
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("application/x-www-form-urlencoded")
	@Path("reg")
	public String reg(@FormParam("id") String userid, @FormParam("pwd") String userpwd, @FormParam("fname") String fn,
			@FormParam("lname") String ln, @FormParam("email") String em) {
		System.out.println(userid + "  ===  " + userpwd);
		try {
			Passenger p = new Passenger(userid, userpwd, fn, ln, em);
			String id = p.getId();
			String result = userDAO.add(ConvertObject.ObjectToByte(p), id);
			System.out.println("addresult : " + result);
			Thread.sleep(1000);
			if (result.equals("OK")) {
				System.out.println("update success");
			} else if (result.equals("CREATED")) {
				System.out.println("add success");
			}
			return "{\"result\":\"ok\"}";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{\"result\":\"reg error500\"}";
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public List<String> passenger() throws Exception {
		List<String> results=userDAO.getAll();
		for (String string : results) {
			System.out.println(string);
		}
		
		return results;
	}
}
