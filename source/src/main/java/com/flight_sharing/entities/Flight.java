package com.flight_sharing.entities;

import java.util.LinkedList;
import java.util.List;

public class Flight {
	private String id;
	private String pilotId;
	private int seat;
	private List<String> passengerId;
	private String departure;
	private String arrival;
	private String date;
	private String time;
	private String duration;
	private String price;
	private String info;

	public Flight() {
		// TODO Auto-generated constructor stub
	}

	public Flight(String id, String pilotId, int seat, List<String> passengerId, String departure, String arrival,
			String date, String time, String duration, String price) {
		this.id = id;
		this.pilotId = pilotId;
		this.seat = seat;
		if(passengerId==null) 
			this.passengerId=new LinkedList<>();
		else 
			this.passengerId = passengerId;
		this.departure = departure;
		this.arrival = arrival;
		this.date = date;
		this.time = time;
		this.duration = duration;
		this.price = price;
		this.info="Pilot has nothing to add.";
	}

	
	public Flight(String id, String pilotId, int seat, List<String> passengerId, String departure, String arrival,
			String date, String time, String duration, String price, String info) {
		super();
		this.id = id;
		this.pilotId = pilotId;
		this.seat = seat;
		this.passengerId = passengerId;
		this.departure = departure;
		this.arrival = arrival;
		this.date = date;
		this.time = time;
		this.duration = duration;
		this.price = price;
		if(info==null)
			this.info = "Pilot has nothing to add.";
		else
			this.info=info;
	}

	public String getInfo() {
		return info;
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

	public List<String> getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(List<String> passengerId) {
		this.passengerId = passengerId;
	}

	@Override
	public String toString() {

		return id + " " + pilotId + " " + date;
	}
}
