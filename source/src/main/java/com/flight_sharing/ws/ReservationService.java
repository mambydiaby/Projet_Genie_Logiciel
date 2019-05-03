package com.flight_sharing.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import com.flight_sharing.entities.Flight;
import com.flight_sharing.entities.Passenger;
import com.flight_sharing.entities.Pilot;
import com.flight_sharing.entities.Reservation;
import com.flight_sharing.json.ConvertObject;
import com.flight_sharing.mail.Email;

@Path("reservation")
public class ReservationService extends Service {
	/**
	 *  web service to create a new reservation
	 * @param r
	 * @return
	 * @throws Exception
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/new")
	public String add(Reservation r) throws Exception {
		if (!IsLogged())
			return "{\"result\":\"Please Login !\"}";
		Flight flight = (Flight) ConvertObject.jsonToObject(flightDao.getById(r.getFlightId()), ConvertObject.FLIGHT);
		if (flight == null)
			return "{\"result\":\"error\"}";
		Pilot pilot = (Pilot) ConvertObject.jsonToObject(pilotDao.getById(flight.getPilotId()), ConvertObject.PILOT);
		if (pilot == null)
			return "{\"result\":\"error\"}";

		String body = "Hello Mr/Mrs/Ms " + pilot.getLastName() + " ,<br/><br/>You have a new  booking for "
				+ r.getSeat() + " seats regarding the flight  <b>" + r.getFlightId()
				+ "</b>. <br/>Please log in to accept or reject it as soon as possible.<br/><br/>Best regards.";
		Email.send(pilot.getEmail(), "Flight booking", body);

		String result = reservationDao.add(ConvertObject.objectToByte(r), r.getId());

		if (result.equals("OK")||result.equals("CREATED")) {
			return "{\"result\":\"success !\"}";
		} else {
			return "{\"result\":\"error \"}";
		}
	}

	/**
	 * web service to approve a reservation
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("application/x-www-form-urlencoded")
	@Path("/approve/{id}")
	public String approve(@PathParam("id") String id) throws Exception {
		System.out.println("approve");

		if (!(IsLogged() && isPilot()))
			return "{\"result\":\"Please Login !\"}";
		Reservation rt = (Reservation) ConvertObject.jsonToObject(reservationDao.getById(id),
				ConvertObject.RESERVATION);
		if (rt == null || rt.isApproved())
			return "{\"result\":\"error\"}";
		Flight flight = (Flight) ConvertObject.jsonToObject(flightDao.getById(rt.getFlightId()), ConvertObject.FLIGHT);
		if (flight == null)
			return "{\"result\":\"error\"}";
		flight.setSeat(flight.getSeat() - rt.getSeat());
		System.out.println("pass"+flight.getPassengerId()+",id"+rt.getPassengerId());
		if(flight.getPassengerId()==null)
			flight.getPassengerId();
		flight.getPassengerId().add(rt.getPassengerId());
		flightDao.add(ConvertObject.objectToByte(flight), flight.getId());
		reservationDao.update(id, "approved", "true");
		Passenger pa=null;
		try {
			pa = (Passenger) ConvertObject.jsonToObject(passengerDao.getById(rt.getPassengerId()),
					ConvertObject.PASSENGER);
		}catch(Exception e) {
			pa=(Passenger) ConvertObject.jsonToObject(pilotDao.getById(rt.getPassengerId()),
					ConvertObject.PILOT);
		}


		String body = "Hello Mr/Mrs/Ms " + pa.getLastName() + ",<br/><br/>Your booking for the flight "
				+ rt.getFlightId()
				+ "  has been approved by the pilot. You will receive an email containing the essential information the "
				+ "day before the flight.<br/><br/>Best regards.";
		Email.send(pa.getEmail(), "Flight booking", body);
		reservationDao.delete(id);
		return "{\"result\":\"ok\"}";
	}
	/**
	 * disapprove a reservation
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/disapprove/{id}")
	public String disapprove(@PathParam("id") String id) throws Exception {

		if (!(IsLogged() && isPilot()))
			return "{\"result\":\"Please Login !\"}";

		Reservation rt = (Reservation) ConvertObject.jsonToObject(reservationDao.getById(id),
				ConvertObject.RESERVATION);
		if (rt == null || rt.isApproved())
			return "{\"result\":\"error\"}";
		Passenger pa=null;
		try {
			pa = (Passenger) ConvertObject.jsonToObject(passengerDao.getById(rt.getPassengerId()),
					ConvertObject.PASSENGER);
		}catch(Exception e) {
			pa=(Passenger) ConvertObject.jsonToObject(pilotDao.getById(rt.getPassengerId()),
					ConvertObject.PILOT);
		}

		String body = "Hello Mr/Mrs/Ms " + pa.getLastName() + ",<br/><br/>Your booking for the flight "
				+ rt.getFlightId() + " has been rejected by the pilot. <br/><br/>Best regards.";
		Email.send(pa.getEmail(), "Flight booking", body);
		reservationDao.delete(id);
		return "{\"result\":\"ok \"}";
	}

	/**
	 * get my reservations 
	 * @param id
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("application/x-www-form-urlencoded")
	@Path("/myreservations/{id}")
	public List<String> searchMyReservations(@PathParam("id") String id) {
		if (!(IsLogged() ))
			return null;
		List<String> result = null;
		try {
			BoolQueryBuilder searchBuilder = QueryBuilders.boolQuery();
			if (!id.isEmpty()) {
				searchBuilder.must(QueryBuilders.termQuery("passengerId",id));
			}
			result = reservationDao.search(searchBuilder);
			return result;
		} catch (Exception e) {
			registerException(e);
		}
		return result;
	}

	/**
	 * get a reservation by its id
	 * @param id
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("application/x-www-form-urlencoded")
	@Path("/getbyid/{id}")
	public String getById(@PathParam("id") String id) {
		if (!(IsLogged()))
			return null;
		String result=null;
		try {
			result = reservationDao.getById(id);	
		} catch (Exception e) {
			registerException(e);
		}
		return result;
	}

	/**
	 *  
	 *  get all the reservations to be approved
	 * @param piloteId
	 * @return
	 * @throws Exception
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("application/x-www-form-urlencoded")
	@Path("/toapprove/{piloteId}")
	public List<String> toApprove(@PathParam("piloteId") String piloteId) throws Exception {
		if (!(IsLogged() && isPilot()))
			return null;
		List<String> rtList = reservationDao
				.search(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("approved", "false"))
						.mustNot(QueryBuilders.termQuery("approved", "true")));

		if (rtList!=null&&!rtList.isEmpty())
			for (int i =rtList.size()-1; i >=0; i--) {
				Reservation rt = (Reservation) ConvertObject.jsonToObject(rtList.get(i), ConvertObject.RESERVATION);
				if (rt == null)
					return null;
				Flight flight = (Flight) ConvertObject.jsonToObject(flightDao.getById(rt.getFlightId()),
						ConvertObject.FLIGHT);
				if (flight != null && !flight.getPilotId().equals(piloteId))
					rtList.remove(i);
				
			}

		return rtList;
	}
}
