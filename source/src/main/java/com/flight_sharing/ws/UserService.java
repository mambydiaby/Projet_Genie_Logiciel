package com.flight_sharing.ws;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flight_sharing.dao.ActionDao;
import com.flight_sharing.dao.FactoryDao;
import com.flight_sharing.entities.Passenger;
import com.flight_sharing.entities.Pilot;
import com.flight_sharing.json.ConvertObject;

@Path("/user")
public class UserService {
	ActionDao passengerDao = FactoryDao.createDAO(FactoryDao.PASSENGER);
	ActionDao pilotDao = FactoryDao.createDAO(FactoryDao.PILOTE);

	@Context
	HttpServletRequest request;

	private boolean IsLogged() {
		return request.getSession().getAttribute("userId") != null;
	}

	/**
	 * web service for passenger and pilot login
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
		ObjectMapper mapper = new ObjectMapper();
		try {
			if (userId == null || userPwd == null) {
				return "{\"result\":\"empty fields\"}";
			}

			else {

				String user = passengerDao.getById(userId);

				if (user == null) {
					user = pilotDao.getById(userId);
					if (user == null) {
						return "{\"result\":\"username incorrect!\"}";
					}

					Pilot p = mapper.readValue(user, Pilot.class);

					if (p.getPwd().equals(userPwd)) {
						request.getSession().setAttribute("userId", p.getId());
						request.getSession().setAttribute("type", "pilote");
						return "{\"result\":\"okp\"}";
					} else {
						return "{\"result\":\"password incorrect!\"}";
					}
				}

				Passenger p = mapper.readValue(user, Passenger.class);

				if (p.getPwd().equals(userPwd)) {
					request.getSession().setAttribute("userId", p.getId());
					request.getSession().setAttribute("type", "passenger");
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
	@Produces(MediaType.APPLICATION_JSON)
	@Path("reg")
	public String addPassenger(Passenger p) {

		try {
			String id = p.getId();
			passengerDao.add(ConvertObject.ObjectToByte(p), id);
			return "{\"result\":\"ok\"}";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{\"result\":\"registration error500\"}";
	}

	/**
	 * webservice for pilote registration
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
	@Produces(MediaType.APPLICATION_JSON)
	@Path("regpilote")
	public String addPilote(Pilot p) {

		try {
			String id = p.getId();
			pilotDao.add(ConvertObject.ObjectToByte(p), id);
			return "{\"result\":\"ok\"}";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{\"result\":\"registration error500\"}";
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("application/x-www-form-urlencoded")
	@Path("profile/{id}")
	public String profile(@PathParam("id") String userId) {

		if (!IsLogged())
			return "{\"result: \":\"Please Login !\"}";
		try {
			String user = passengerDao.getById(userId);

			if (user == null)
				user = pilotDao.getById(userId);
			//System.out.print(user);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("application/x-www-form-urlencoded")
	@Path("{type}/profile/{id}")
	public String delete(@PathParam("type") String uType, @PathParam("id") String userId) {
		System.out.print(uType);
		try {
			if (uType.equals("Pilote"))
				return pilotDao.delete(userId);
			else if (uType.equals("Passenger"))
				return passengerDao.delete(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

}
