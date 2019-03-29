package com.flight_sharing.entities;

public class Pilot extends Passenger {
	/** pilote's experience */
	private String experience;
	/** pilote's qualification */
	private String qualification;

	public Pilot() {
		super();
	}

	public Pilot(String id, String pwd, String firstName, String lastName, String email, String experience,
			String qualification) {
		super(id, pwd, firstName, lastName, email);
		this.experience = experience;
		this.qualification = qualification;
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
