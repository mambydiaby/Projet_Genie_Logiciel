package com.flight_sharing.entities;

public class Passenger {
	/** passenger's username */
	private String id;
	/** passenger's password */
	private String pwd;
	/** passenger's first name */
	private String firstName;
	/** passenger's last name */
	private String lastName;
	/** passenger's email address */
	private String email;
	public Passenger() {
		// TODO Auto-generated constructor stub
	}

	public Passenger(String id, String pwd, String firstName, String lastName, String email) {
		this.id = id;
		this.pwd = pwd;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
