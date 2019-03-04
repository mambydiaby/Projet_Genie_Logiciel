package com.flight_sharing.ws;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
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
	ActionDao passengerDao = FactoryDao.createDAO(FactoryDao.PASSENGER);
	ActionDao piloteDao = FactoryDao.createDAO(FactoryDao.PILOTE);

	@Context
	HttpServletRequest request;

	/**
	 * web service for passenger login
	 * 
	 * @param userId
	 * @param userPwd
	 * @return
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("application/x-www-form-urlencoded")
	@Path("login")
	public String login(@FormParam("id") String userId, @FormParam("pwd") String userPwd) {
		try {
			if (userId == null || userPwd == null) {
				return "{\"result\":\"error\"}";
			}

			else {

				String user = passengerDao.getById(userId);

				if (user == null) {
					return "{\"result\":\"username incorrect!\"}";
				}

				ObjectMapper mapper = new ObjectMapper();
				Passenger p = mapper.readValue(user, Passenger.class);

				if (p.getPwd().equals(userPwd)) {
					request.getSession().setAttribute("userId", p.getId());
					return "{\"result\":\"ok\"}";
				} else {
					return "{\"result\":\"password incorrect!\"}";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{\"result\":\"login error500\"}";
	}

	/**
	 * webservice for passenger registration
	 * 
	 * @param userId
	 * @param userPwd
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @return
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	// @Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.APPLICATION_JSON)
	@Path("reg")
//	public String registration(@FormParam("id") String userId, @FormParam("pwd") String userPwd,
//			@FormParam("fname") String firstName, @FormParam("lname") String lastName,
//			@FormParam("email") String email) {
	public String addPassenger(Passenger p) {
		
		System.out.println("registration");
		try {
			// Passenger p = new Passenger(userId, userPwd, firstName, lastName, email);
			String id = p.getId();
			String result = passengerDao.add(ConvertObject.ObjectToByte(p), id);
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
		return "{\"result\":\"registration error500\"}";
	}

}
