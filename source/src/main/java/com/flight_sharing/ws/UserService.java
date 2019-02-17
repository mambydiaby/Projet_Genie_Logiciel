package com.flight_sharing.ws;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flight_sharing.dao.ActionDao;
import com.flight_sharing.dao.FactoryDao;
import com.flight_sharing.entities.Passenger;

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
				String userResult = userDAO.getByID(userid);

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
}
