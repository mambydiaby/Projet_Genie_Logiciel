package source;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.flight_sharing.dao.ActionDao;
import com.flight_sharing.dao.FactoryDao;
import com.flight_sharing.entities.Passenger;
import com.flight_sharing.entities.Pilot;
import com.flight_sharing.json.ConvertObject;

public class DaoTest {

	private ActionDao passengerDao = FactoryDao.createDAO(FactoryDao.PASSENGER);
	private ActionDao pilotDao = FactoryDao.createDAO(FactoryDao.PILOT);

	private Passenger p;
	private Pilot pl;

	@Before
	public void initialization() {
		p = new Passenger("azerty0", "544", "Balde", "mamadou", "mybalde@test.com");
		pl = new Pilot("007", "fr021", "james", "bond", "test@junit.com", "test", "teesst");
	}

	@Test
	public void addTest() throws JsonProcessingException, Exception {
		assertEquals("CREATED", passengerDao.add(ConvertObject.objectToByte(p), p.getId()));
		assertEquals("CREATED", pilotDao.add(ConvertObject.objectToByte(pl), pl.getId()));
	}

	@Test
	public void getTest() throws Exception {
		assertNotNull(passengerDao.getById(p.getId()));
		assertNotNull(pilotDao.getById(pl.getId()));
		assertNull(passengerDao.getById("echec"));
		assertNull(pilotDao.getById("aaa"));
		assertNotNull(passengerDao.getAll());
		assertNotNull(pilotDao.getAll());
	}

	@Test
	public void updateTest() throws Exception {
		// password before
		assertTrue(p.getPwd().equals("544"));
		// changing the password in the database
		passengerDao.update(p.getId(), "pwd", "kjd23547x");
		String user = passengerDao.getById(p.getId());
		p = (Passenger) ConvertObject.jsonToObject(user, ConvertObject.PASSENGER);
		// new password
		assertTrue(p.getPwd().equals("kjd23547x"));
	}

	@Test
	public void deleteTest() throws Exception {
		assertEquals("OK", passengerDao.delete(p.getId()));
		assertEquals("OK", pilotDao.delete(pl.getId()));
		assertEquals("NOT_FOUND", passengerDao.delete("echec"));
	}

}
