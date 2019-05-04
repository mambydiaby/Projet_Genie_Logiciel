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

import org.json.JSONObject;

import com.flight_sharing.entities.Passenger;
import com.flight_sharing.entities.Pilot;
import com.flight_sharing.mapping.ConvertObject;

/**
 * this class contains all web services related to users which are login,
 * registration and profile retrieval
 */
@Path("/user")
public class UserService extends Service {
	/**
	 * this method checks whether the id's available
	 * 
	 * @param id user id
	 * @return true or false
	 * @throws Exception
	 */
	boolean userExists(String id) throws Exception {
		return pilotDao.getById(id) != null && passengerDao.getById(id) != null;
	}

	/**
	 * web service for passenger and pilot login
	 * 
	 * @param userId  user id
	 * @param userPwd user password
	 * @return string in json format
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("application/x-www-form-urlencoded")
	@Path("login")
	public String login(@FormParam("id") String userId, @FormParam("pwd") String userPwd) {
		try {
			// empty fields
			if (userId == null || userPwd == null) {
				return "{\"result\":\"empty fields\"}";
			}

			else {
				// looking for the user in passenger's table
				String user = passengerDao.getById(userId);
				// if we've got nothing, we look for the user in pilot's table
				if (user == null) {
					user = pilotDao.getById(userId);
					// if we got nothing again that means the id's wrong
					if (user == null) {
						return "{\"result\":\"username incorrect!\"}";
					}
					// convert user from json to pilot object
					Pilot p = (Pilot) ConvertObject.jsonToObject(user, ConvertObject.PILOT);
					// comparison between the password given in the front and the one in pilot table
					if (p.getPwd().equals(userPwd)) {
						// if they match, we create a new session
						request.getSession().setAttribute("userId", p.getId());
						request.getSession().setAttribute("type", "pilot");
						return "{\"result\":\"okp\"}";
					} else {
						return "{\"result\":\"password incorrect!\"}";
					}
				}
				// convert user from json to passenger object
				Passenger p = (Passenger) ConvertObject.jsonToObject(user, ConvertObject.PASSENGER);
				// comparison between the password given in the front and the one in passenger
				// table
				if (p.getPwd().equals(userPwd)) {
					// if they match, we create a new session
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
	 * web service for passenger registration
	 * 
	 * @param userId    passenger id
	 * @param userPwd   passenger password
	 * @param firstName passenger firstname
	 * @param lastName  passenger lastname
	 * @param email     passanger email
	 * @return string in json format
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("reg")
	public String addPassenger(Passenger p) {

		try {
			String id = p.getId();
			// check if the username available
			if (userExists(id))
				return "{\"result\":\"username not available\"}";
			// register in the database
			passengerDao.add(ConvertObject.objectToByte(p), id);
			return "{\"result\":\"ok\"}";

		} catch (Exception e) {
			registerException(e);
		}
		return "{\"result\":\"registration error500\"}";
	}

	/**
	 * web service for pilot registration
	 * 
	 * @param userId    pilot id
	 * @param userPwd   pilot password
	 * @param firstName pilot first name
	 * @param lastName  pilot last name
	 * @param email     pilot email
	 * @return string in json format
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("regpilote")
	public String addPilote(Pilot p) {

		try {
			String id = p.getId();
			// check if the username available
			if (userExists(id))
				return "{\"result\":\"username not available\"}";
			// register in the database
			pilotDao.add(ConvertObject.objectToByte(p), id);
			return "{\"result\":\"ok\"}";

		} catch (Exception e) {
			registerException(e);
		}
		return "{\"result\":\"registration error500\"}";
	}

	/**
	 * web service for user profile
	 * 
	 * @param userId user id
	 * @return string in json format
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("application/x-www-form-urlencoded")
	@Path("profile/{id}")
	public String profile(@PathParam("id") String userId) {
		// check if user is connected
		/*if (!IsLogged())
			return "{\"result\":\"Please Login !\"}";*/
		try {
			// look for user data in passenger
			String user = passengerDao.getById(userId);
			// look for user data in pilot
			if (user == null)
				user = pilotDao.getById(userId);
			// remove the password
			if (!user.isEmpty()) {
				JSONObject json = new JSONObject(user);
				json.remove("pwd");
				return json.toString();
			}
		} catch (Exception e) {
			registerException(e);
		}
		return "{\"result\":\"error\"}";
	}

	/**
	 * web service to delete an account
	 * 
	 * @param userId
	 * @return
	 */
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("application/x-www-form-urlencoded")
	@Path("/profile/{id}")
	public String delete(@PathParam("id") String userId) {
		try {
			if (isPilot())
				return pilotDao.delete(userId);
			else if (isPassenger())
				return passengerDao.delete(userId);
		} catch (Exception e) {
			registerException(e);
		}
		return "";
	}

}