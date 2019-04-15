package source;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import com.flight_sharing.entities.Passenger;
import com.flight_sharing.entities.Pilot;
import com.flight_sharing.ws.UserService;

public class ServiceTest {
	private UserService userService;
	private Passenger p;
	private Pilot pl;

	@Before
	public void initialization() {
		p = new Passenger("azerty0", "544", "Balde", "mamadou", "mybalde@test.com");
		pl = new Pilot("007", "fr021", "james", "bond", "test@junit.com", "test", "teesst");
	}

	@Test
	public void addUserTest() {
		System.out.println(userService.addPassenger(p));
		JSONObject j1 = new JSONObject(userService.addPassenger(p)), j2 = new JSONObject(userService.addPilote(pl));
		System.out.println("********************************"+j1.get("result")+"**********************************");
		assertTrue(j1.get("result").equals("ok"));
		assertTrue(j2.get("result").equals("ok"));
	}
	
	@Test
	public void deleteTest() {
		assertEquals("OK", userService.delete(p.getId()));
		assertEquals("OK", userService.delete(pl.getId()));
	}
}
