package com.flight_sharing.entities;

public class Flight {
	private String id;
	private String pilotId;
	private int seat;
	private String departure;
	private String arrival;
	private String date;
	private String time;
	private String duration;
	private String price;

	public Flight() {
		// TODO Auto-generated constructor stub
	}

	public Flight(String id, String pilotId, int seat, String departure, String arrival, String date, String time,
			String duration, String price) {
		this.id = id;
		this.pilotId = pilotId;
		this.seat = seat;
		this.departure = departure;
		this.arrival = arrival;
		this.date = date;
		this.time = time;
		this.duration = duration;
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPilotId() {
		return pilotId;
	}

	public void setPilotId(String pilotId) {
		this.pilotId = pilotId;
	}

	@Override
	public String toString() {

		return id + " " + pilotId + " " + date;
	}
}
