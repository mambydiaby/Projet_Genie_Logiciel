package com.example.jetty_jersey.ws;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/home")
public class Users {
	
	public static class UsersClass {
		public ArrayList<String> list = new ArrayList<String>();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/userslist")
	public UsersClass getUsers() {
		UsersClass instance = new UsersClass();
		instance.list.add("Charle");
		instance.list.add("Xavier");
		instance.list.add("Mick");
		instance.list.add("Jean");
		instance.list.add("Karl");
		return instance;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/userslist")
	public void retrieveUsers(String instance) {
		System.out.println(instance);
	}
}
