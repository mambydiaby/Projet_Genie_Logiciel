package com.flight_sharing.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.flight_sharing.entities.*;
import com.flight_sharing.json.ConvertObject;
/**
 *  
 *  add Data to database
 *
 */
public class FillData {

	public static void addPassenger() throws JsonProcessingException, Exception {
		ActionDao passengerDao = FactoryDao.createDAO(FactoryDao.PASSENGER);
		Passenger[] p = new Passenger[5];
		p[0] = new Passenger("azerty", "544", "Balde", "mamadou", "mybalde@test.com");
		p[1] = new Passenger("jhuy7x", "544", "Jules", "cesar", "415447764@qq.com");
		p[2]=new Passenger("wqe", "32", "curry", "cesar", "aabbcc@a.fr");
		p[3]=new Passenger("eqw", "3", "stephen", "james", "pass.er@hot.fr");
		p[4]=new Passenger("all", "9", "kobe", "harden", "lingjiarongaini@gmail.com");

		for (Passenger passenger : p) {
			passengerDao.add(ConvertObject.objectToByte(passenger), passenger.getId());
		}
		
	}
	public static void addPilot() throws JsonProcessingException, Exception {
		ActionDao pilotDao = FactoryDao.createDAO(FactoryDao.PILOT);
		Pilot[] pilot=new Pilot[7];
		pilot[0]=new Pilot("Flower","0","ling","JIARONG","lingjiarong@gmail.com","3 years","best pilot in the world! ");
		pilot[1]=new Pilot("Flighter","0","curry","STEPHEN","le@gmail.com","22 years","very great");
		pilot[2]=new Pilot("003","0","jeremy","ARKI","jarkid@gmail.com","1 year","super ");
		pilot[3]=new Pilot("009","0","eminem","SHAR","shar.kid@gmail.com","2 months","high skill");
		pilot[4]=new Pilot("032","0","venom","LUCY","lucy.oldman@gmail.com","13 years","the best ");
		pilot[5]=new Pilot("Superman","avengers","Peter","parker","super@man.fr","yesterday","so far so good ");
		pilot[6]=new Pilot("Batman","Robin","Bat","Man","me@Batmail.com","Bat years","the bat-pilote ");

		for (Pilot pilot1 : pilot) {
			pilotDao.add(ConvertObject.objectToByte(pilot1), pilot1.getId());
		}
	}
	public static void addAirport() throws JsonProcessingException, Exception {
		ActionDao airportDao = FactoryDao.createDAO(FactoryDao.AIRPORT);
		Airport[] airport=new Airport[13];
		airport[0]=new Airport("LFBL","Limoges","Limoges - Bellegarde Airport");
		airport[1]=new Airport("LFLY","Lyon","Lyon - Bron Airport");
		airport[2]=new Airport("LFSN","Nancy","Nancy - Essey Airport");
		airport[3]=new Airport("LFPG","Paris","Paris-Charles de Gaulle Airport");
		airport[4]=new Airport("LFST","Strasbourg","Strasbourg Airport");
		airport[5]=new Airport("LFBO","Toulouse","Toulouse - Blagnac Airport");
		airport[6]=new Airport("LFQQ","Lille", "Lille - Lille Airport");
		airport[7]=new Airport("LFRS","Nantes", "Aéroport Nantes Atlantique");
		airport[8]=new Airport("LFBO","Toulouse", "Aéroport Toulouse-Blagnac");
		airport[9]=new Airport("LFML","Marseille", "Aéroport Marseille Provence");
		airport[10]=new Airport("LFMN","Nice", "Aéroport Nice Côte d'Azur");
		airport[11]= new Airport("LFMT","Montpellier", "Aéroport Montpellier Méditerranée");
		airport[12]= new Airport("LFSB","Mulhouse", "Aéroport international de Bâle-Mulhouse-Fribourg");
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
				"The Airbus A380 is the world's largest passenger airliner, a wide-body aircraft manufactured by Airbus. Airbus studies started in 1988 and the project was announced in 1990 to challenge the dominance of the Boeing 747 in the long haul market. The A3XX project was presented in 1994; Airbus launched the â‚¬9.5 billion ($10.7 billion) A380 programme on 19 December 2000. The first prototype was unveiled in Toulouse on 18 January 2005, with its first flight on 27 April 2005. It obtained its EASA and FAA type certificates on 12 December 2006. Difficulties in electrical wiring caused a two-year delay and the development cost ballooned to â‚¬18 billion. ");
		airplane[3]=new Airplane("Airbus_city","The Airbus CityAirbus is a multinational project by Airbus Helicopters to produce an electrically-powered VTOL aircraft demonstrator. It is intended for the air taxi role, to avoid ground traffic congestion.[");
		for(Airplane a:airplane) {
			airplaneDao.add(ConvertObject.objectToByte(a),a.getId());
		}	
	}
	
	public static void addFlight() throws JsonProcessingException, Exception {
		ActionDao flightDao = FactoryDao.createDAO(FactoryDao.FLIGHT);
		Flight[] flight =new Flight[26];
		flight[0]= new Flight("GW902","009",9,null,"Paris","Lyon","2019-08-22","23:30","3h","96","more infos","rdv at 3:00","Paris->Lyon->Lille->Lyon");
		flight[1]= new Flight("WE666","009",2,null,"Lyon", "Paris", "2019-05-08","20:30","3h", "200","welcome 2 my flight","rdv at 3:00 12/04/2019","Lyon->Lille->Paris"); 	
		flight[2]= new Flight("PWO996","032",12,null,"Toulouse","Lyon", "2019-12-22", "13:30","4h","166"); 		
		flight[3]= new Flight("GP902", "032",19, null, "Nancy", "Lyon", "2020-03-31", "23:30", "3h", "166"); 		
		flight[4]= new Flight("GW999", "032", 29, null, "Paris", "Limoges", "2019-05-01", "23:30", "2.5h", "160"); 	
		flight[5]= new Flight("GW908", "032", 45, null, "Limoges", "Lyon", "2029-07-01", "21:30", "3h", "196"); 		
		flight[6]= new Flight("GO932", "003",19, null,"Strasbourg", "Lyon", "2019-06-02", "23:30", "3h", "196"); 		
		flight[7]= new Flight("GW552","032",99, null, "Toulouse", "Lyon", "2019-06-21", "13:30", "3h", "166");
		flight[8]= new Flight("GW913","032",49,null,"Lyon","Lyon","2019-06-21","23:30","3h","166");
		flight[9]= new Flight("GW002","009",39,null,"Paris","Paris","2019-12-31","23:30","3h","126");
		flight[10]=new Flight("GGWP123","009",11,null,"Paris","Nancy","2019-09-01","23:30","3h","93");	
		flight[11]=new Flight("GPP142","009",2,null,"Nancy","Paris","2019-10-01","23:30","3h","88");	
		flight[12]=new Flight("GPOE12","009",31,null,"Nancy","Limoges","2020-09-01","23:30","3h","99");	
		flight[13]=new Flight("QNM213","009",41,null,"Paris","Lyon","2020-11-01","23:30","3h","199");	
		flight[14]=new Flight("NMSL213","003",51,null,"Paris","Limoges","2020-12-01","23:30","3h","106");	
		flight[15]=new Flight("NMSl21","003",16,null,"Paris","Nancy","2022-01-01","23:30","3h","126");	
		flight[16]=new Flight("WARl96","Batman",83,null,"Montpellier","Nice","2019-05-09","10:20","5h","94");	
		flight[17]=new Flight("NMSl21","Superman",16,null,"Paris","Nancy","2019-05-10","05:30","3h","126");	
		flight[18]=new Flight("CLKl21","Flower",17,null,"Nantes","Nancy","2019-06-09","12:45","2h","52");	
		flight[19]=new Flight("GP162","003",16,null,"Marseille","Lille","2019-07-07","11:30","7h","32");	
		flight[20]=new Flight("ZRI321","Flighter",2,null,"Strasbourg","Toulouse","2019-08-07","6h16","4h","49");	
		flight[21]=new Flight("CLS75","Batman",5,null,"Paris","Marseille","2020-01-04","16:30","2h45","93");	
		flight[22]=new Flight("LNA904","Flighter",8,null,"Limoges","Nancy","2019-05-30","23:30","3h","76");	
		flight[23]=new Flight("FAC315","Superman",21,null,"Paris","Nancy","2021-01-01","14:30","3h","72");	
		flight[24]=new Flight("ESQ096","003",41,null,"Paris","Nancy","2020-04-06","20:07","1h30","75");	
		flight[25]=new Flight("TYQ42","009",20,null,"Paris","Nancy","2022-01-01","14:30","1h48","3");	
		for(Flight f:flight) {
			flightDao.add(ConvertObject.objectToByte(f), f.getId());
		}
	}
	
	public static void addReservation() throws JsonProcessingException, Exception {
		ActionDao reservationDao = FactoryDao.createDAO(FactoryDao.RESERVATION);	
		Reservation[] r=new Reservation[10];
		r[0]=new Reservation("R1234","GW913","jhuy7x",1);
		r[1]=new Reservation("R1235","GW913","all",2);
		r[2]=new Reservation("R1245","GW552","all",1);
		r[3]=new Reservation("R1345","GW552","009",4);
		r[4]=new Reservation("R1343","GW552","jhuy7x",4);
		r[5]=new Reservation("R1341","GW552","jhuy7x",5);
		r[6]=new Reservation("R1305","GW552","jhuy7x",6);
		r[7]=new Reservation("R1385","GW552","jhuy7x",12);
		r[8]=new Reservation("R1045","GW552","jhuy7x",13);
		r[9]=new Reservation("R10045","GW552","jhuy7x",14);

		
		for(Reservation a:r) {
			reservationDao.add(ConvertObject.objectToByte(a),a.getId());
		}	
	}

	public static void main(String[] args) throws JsonProcessingException, Exception {
		addPassenger();
		addAirplane();
		addAirport();
		addFlight();
		addPilot();
		addReservation();
	}
}
