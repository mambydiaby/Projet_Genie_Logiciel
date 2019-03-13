package com.flight_sharing.dao;

public class FactoryDao {
	public static final int FLIGHT=1;
	public static final int PILOT=2;
	public static final int PASSENGER=3;

	public static ActionDao createDAO(int id){
		switch (id) {
		case FLIGHT:
			return new ActionDao("flight");
		case PILOT:
			return new ActionDao("pilote");
		case PASSENGER:
			return new ActionDao("passenger");
		default:
			return null;
		}
	}
}
