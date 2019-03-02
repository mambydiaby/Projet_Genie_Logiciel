package com.flight_sharing.entities;

import java.util.List;

public class Flight {
	private String id;
	private String piloteId;
	private List<String> passengerId;
	private int seat;
	private String departure;
	private String arrival;
	private String date;
	private String time;
	private String duration;

	public Flight() {
		// TODO Auto-generated constructor stub
	}

	public Flight(String id, String piloteId, List<String> passengerId, int seat, String departure, String arrival,
			String date, String time, String duration) {
		this.id = id;
		this.piloteId = piloteId;
		this.passengerId = passengerId;
		this.seat = seat;
		this.departure = departure;
		this.arrival = arrival;
		this.date = date;
		this.time = time;
		this.duration = duration;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPiloteId() {
		return piloteId;
	}

	public void setPiloteId(String piloteId) {
		this.piloteId = piloteId;
	}

	public List<String> getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(List<String> passengerId) {
		this.passengerId = passengerId;
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
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

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
	
}
