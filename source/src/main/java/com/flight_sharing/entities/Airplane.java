package com.flight_sharing.entities;

public class Airplane {
	private String id;
	private String description;

	public Airplane() {
		// TODO Auto-generated constructor stub
	}

	public Airplane(String id, String description) {
		this.id = id;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
