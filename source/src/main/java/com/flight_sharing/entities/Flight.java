package com.flight_sharing.entities;

import java.util.List;

public class Flight {
	private String id;
	private String departure;
	private String arrival;
	private String date;
	private String time;
	private String piloteId;
	private int seat;
	private List<String> passengerId;

	public Flight() {
		// TODO Auto-generated constructor stub
	}

	public Flight(String id, String departure, String arrival, String date, String time, String piloteId, int seat,
			List<String> passengerId) {
		this.id = id;
		this.departure = departure;
		this.arrival = arrival;
		this.date = date;
		this.time = time;
		this.piloteId = piloteId;
		this.seat = seat;
		this.passengerId = passengerId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPiloteId() {
		return piloteId;
	}

	public void setPiloteId(String piloteId) {
		this.piloteId = piloteId;
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	public List<String> getPassengerId() {
		return passengerId;
	}

	public void setPassenger(List<String> passengerId) {
		this.passengerId = passengerId;
	}

}
