package source;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;

public class ServiceTest {
	
	@Test
	public void givenCreateFruit_whenFormContainsNullParam_thenResponseCodeIsBadRequest() {
	    Form form = new Form();
	    form.param("id", "gtx15z");
	    form.param("pwd", null);
	    Response response = target("user/login").request(MediaType.APPLICATION_FORM_URLENCODED)
	        .post(Entity.form(form));
	 
	    assertEquals("Http Response should be 400 ", 400, response.getStatus());
	    assertThat(response.readEntity(String.class), containsString("Fruit colour must not be null"));
	 }
}
