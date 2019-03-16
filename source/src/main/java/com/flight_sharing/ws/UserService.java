package com.flight_sharing.ws;

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

import com.flight_sharing.entities.Passenger;
import com.flight_sharing.entities.Pilot;
import com.flight_sharing.json.ConvertObject;

@Path("/user")
public class UserService extends Service {
	

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

					Pilot p =  (Pilot) ConvertObject.jsonToObject(user, ConvertObject.PILOT);

					if (p.getPwd().equals(userPwd)) {
						request.getSession().setAttribute("userId", p.getId());
						request.getSession().setAttribute("type", "pilot");
						return "{\"result\":\"okp\"}";
					} else {
						return "{\"result\":\"password incorrect!\"}";
					}
				}

				Passenger p = (Passenger) ConvertObject.jsonToObject(user, ConvertObject.PASSENGER);

				if (p.getPwd().equals(userPwd)) {
					request.getSession().setAttribute("userId", p.getId());
					request.getSession().setAttribute("type", "passenger");
					return "{\"result\":\"ok\"}";
				} else {
					return "{\"result\":\"password incorrect!\"}";
				}
			}
		} catch (Exception e) {
			registerException(e);
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
			passengerDao.add(ConvertObject.objectToByte(p), id);
			return "{\"result\":\"ok\"}";

		} catch (Exception e) {
			registerException(e);
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
			pilotDao.add(ConvertObject.objectToByte(p), id);
			return "{\"result\":\"ok\"}";

		} catch (Exception e) {
			registerException(e);
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
			registerException(e);
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
			registerException(e);
		}
		return "";
	}

}
