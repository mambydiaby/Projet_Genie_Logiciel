package com.flight_sharing.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.flight_sharing.entities.*;
import com.flight_sharing.json.ConvertObject;

public class FillData {

	public static void addPassenger() throws JsonProcessingException, Exception {
		ActionDao passengerDao = FactoryDao.createDAO(FactoryDao.PASSENGER);
		Passenger[] p = new Passenger[5];
		p[0] = new Passenger("azerty", "544", "Balde", "mamadou", "mybalde@test.com");
		p[1] = new Passenger("jhuy7x", "544", "Jules", "cesar", "alexandre.dai@live.fr");
		p[2]=new Passenger("wqe", "32", "curry", "cesar", "aabbcc@a.fr");
		p[3]=new Passenger("eqw", "3", "stephen", "james", "pass.er@hot.fr");
		p[4]=new Passenger("all", "9", "kobe", "harden", "free.dd@lf.fr");

		for (Passenger passenger : p) {
			passengerDao.add(ConvertObject.objectToByte(passenger), passenger.getId());
		}
		
	}
	public static void addPilot() throws JsonProcessingException, Exception {
		ActionDao pilotDao = FactoryDao.createDAO(FactoryDao.PILOT);
		Pilot[] pilot=new Pilot[5];
		pilot[0]=new Pilot("FLower","0","ling","jiarong","lingjiarong@gmail.com","3 ans","bon");
		pilot[1]=new Pilot("Flighter","0","curry","stephen","le@gmail.com","22 ans","bon");
		pilot[2]=new Pilot("003","0","jeremy","arki","jarkid@gmail.com","1 ans","bon");
		pilot[3]=new Pilot("009","0","eminem","shar","shar.kid@gmail.com","2 ans","bon");
		pilot[4]=new Pilot("032","0","venom","lucy","lucy.oldman@gmail.com","13 ans","bon");
		

		for (Pilot pilot1 : pilot) {
			pilotDao.add(ConvertObject.objectToByte(pilot1), pilot1.getId());
		}
	}
	public static void addAirport() throws JsonProcessingException, Exception {
		ActionDao airportDao = FactoryDao.createDAO(FactoryDao.AIRPORT);
		Airport[] airport=new Airport[6];
	
		
		
		airport[0]=new Airport("LFBL","Limoges","Limoges - Bellegarde Airport");
		airport[1]=new Airport("LFLY","Lyon","Lyon - Bron Airport");
		airport[2]=new Airport("LFSN","Nancy","Nancy - Essey Airport");
		airport[3]=new Airport("LFPG","Paris","Paris-Charles de Gaulle Airport");
		airport[4]=new Airport("LFST","Strasbourg","Strasbourg Airport");
		airport[5]=new Airport("LFBO","Toulouse","Toulouse - Blagnac Airportt");
		for(Airport a:airport) {
			airportDao.add(ConvertObject.objectToByte(a),a.getId());
		}
		
	}
	public static void addAirplane() throws JsonProcessingException, Exception {
		ActionDao airplaneDao = FactoryDao.createDAO(FactoryDao.AIRPLANE);
		
		
		
		Airplane[] airplane=new Airplane[4];
	
	
		airplane[0]=new Airplane("Airbus_A220","The Airbus A220, previously known as Bombardier CSeries (or C Series), is a family of narrow-body, twin-engine, medium-range jet airliners marketed by Airbus but designed and originally built by the Canadian manufacturer Bombardier Aerospace. Following Airbus involvement the aircraft are built by CSeries Aircraft Limited Partnership (CSALP)."); 
		airplane[1]=new Airplane("Airbus_A330","The Airbus A330 is a medium- to long-range wide-body twin-engine jet airliner made by Airbus. Versions of the A330 have a range of 5,000 to 13,430 kilometres (2,700 to 7,250 nmi; 3,110 to 8,350 mi) and can accommodate up to 335 passengers in a two-class layout or carry 70 tonnes (154,000 lb) of cargo. ");
		airplane[2]=new Airplane("Airbus_A380",
				"The Airbus A380 is the world's largest passenger airliner, a wide-body aircraft manufactured by Airbus. Airbus studies started in 1988 and the project was announced in 1990 to challenge the dominance of the Boeing 747 in the long haul market. The A3XX project was presented in 1994; Airbus launched the €9.5 billion ($10.7 billion) A380 programme on 19 December 2000. The first prototype was unveiled in Toulouse on 18 January 2005, with its first flight on 27 April 2005. It obtained its EASA and FAA type certificates on 12 December 2006. Difficulties in electrical wiring caused a two-year delay and the development cost ballooned to €18 billion. ");
		airplane[3]=new Airplane("Airbus_city","The Airbus CityAirbus is a multinational project by Airbus Helicopters to produce an electrically-powered VTOL aircraft demonstrator. It is intended for the air taxi role, to avoid ground traffic congestion.[");
		for(Airplane a:airplane) {
			airplaneDao.add(ConvertObject.objectToByte(a),a.getId());
		}
		
		
	}
	
	public static void addFlight() throws JsonProcessingException, Exception {
		ActionDao flightDao = FactoryDao.createDAO(FactoryDao.FLIGHT);

		Flight[] flight =new Flight[11];
		flight[0]= new Flight("GW902","009",9,null,"Paris","Lyon","2019-03-31","23:30","3h","96");
		flight[1]= new Flight("WE666","009",2,null,"Lyon", "Paris", "2019-05-08","20:30","3h", "200"); 	
		flight[2]= new Flight("PWO996","032",12,null,"Toulouse","Lyon", "2019-12-22", "13:30","4h","166"); 		
		flight[3]= new Flight("GP902", "032",19, null, "Nancy", "Lyon", "2020-03-31", "23:30", "3h", "166"); 		
		flight[4]= new Flight("GW999", "032", 29, null, "Paris", "Limoges", "2019-05-01", "23:30", "2.5h", "160"); 	
		flight[5]= new Flight("GW908", "032", 5, null, "Limoges", "Lyon", "2029-03-31", "21:30", "3h", "196"); 		
		flight[6]= new Flight("GO932", "003",19, null,"Strasbourg", "Lyon", "2019-03-31", "23:30", "3h", "196"); 		
		flight[7]= new Flight("GW552","032",9, null, "Toulouse", "Lyon", "2019-03-31", "13:30", "3h", "166");
		flight[8]= new Flight("GW913","032",9,null,"Lyon","Lyon","2019-03-31","23:30","3h","166");
		flight[9]= new Flight("GW002","009",9,null,"Paris","Paris","2019-03-31","23:30","3h","126");
		flight[10]=new Flight("GW112","003",11,null,"Nancy","Nancy","2019-09-01","23:30","3h","196");	
		for(Flight f:flight) {
			flightDao.add(ConvertObject.objectToByte(f), f.getId());
		}
			
		
	}

	public static void main(String[] args) throws JsonProcessingException, Exception {
		addPassenger();
		addAirplane();
		addAirport();
		addFlight();
		addPilot();
	}
}
