package com.flight_sharing.entities;

public class Pilot {
	/** pilote's username */
	private String id;
	/** pilote's password */
	private String pwd;
	/** pilote's first name */
	private String firstName;
	/** pilote's last name */
	private String lastName;
	/** pilote's email address */
	private String email;
	/** pilote's experience */
	private String experience;
	/** pilote's qualification */
	private String qualification;

	public Pilot() {
		// TODO Auto-generated constructor stub
	}

	public Pilot(String id, String pwd, String firstName, String lastName, String email, String experience,
			String qualification) {
		this.id = id;
		this.pwd = pwd;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.experience = experience;
		this.qualification = qualification;
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

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

}
