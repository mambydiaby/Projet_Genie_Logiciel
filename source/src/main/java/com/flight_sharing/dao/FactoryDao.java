package com.flight_sharing.dao;

public class FactoryDao {
	public static final int FLIGHT=1;
	public static final int PILOTE=2;
	public static final int PASSENGER=3;
	public static final int OTHER=4;

	public static ActionDao createDAO(int id){
		switch (id) {
		case FLIGHT:
			return new ActionDao("flight");
		case PILOTE:
			return new ActionDao("pilote");
		case PASSENGER:
			return new ActionDao("passenger");
		case OTHER:
			return new ActionDao("other");
		default:
			return null;
		}
	}
}
