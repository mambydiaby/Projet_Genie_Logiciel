package com.flight_sharing.entities;

public class Reservation {
	private String id;
	private String flightId;
	private String passengerId;
	private int seat;
	private boolean approved;

	public Reservation() {
	}

	public Reservation(String id, String flightId, String passengerId, int seat) {
		this.id = id;
		this.flightId = flightId;
		this.passengerId = passengerId;
		this.seat = seat;
		this.approved = false;
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public String getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(String passengerId) {
		this.passengerId = passengerId;
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
