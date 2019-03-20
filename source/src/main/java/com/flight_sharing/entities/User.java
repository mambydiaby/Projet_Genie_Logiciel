package com.flight_sharing.entities;

public class User {
	/** username */
	protected String id;
	/** user password */
	protected String pwd;
	/** user's first name */
	protected String firstName;
	/** user's last name */
	protected String lastName;
	/** user's email address */
	protected String email;
	/** user's adress */
	protected String address;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String id, String pwd, String firstName, String lastName, String email/* , String address */) {
		this.id = id;
		this.pwd = pwd;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		// this.address = address;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
