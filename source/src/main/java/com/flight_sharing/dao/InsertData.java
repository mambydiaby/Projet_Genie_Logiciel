package com.flight_sharing.dao;

import java.util.ArrayList;
import java.util.List;

import com.flight_sharing.entities.Flight;
import com.flight_sharing.entities.Passenger;
import com.flight_sharing.entities.Pilote;
import com.flight_sharing.json.ConvertObject;

public class InsertData {

	public static void addPa() {
		ActionDao planeDao = FactoryDao.createDAO(FactoryDao.PASSENGER);
		Passenger[] p = new Passenger[4];
		p[0] = new Passenger("azerty", "544", "Balde", "mamadou", "mamadouyero95@gmail.com");
		p[1] = new Passenger("jhuy7x", "544", "Jules", "césar", "mybalde@outlook.com");
		for (int i = 0; i < 5; i++) {
			try {
				String id = p[i].getId();
				String result = planeDao.add(ConvertObject.ObjectToByte(p[i]), id);
				System.out.println("addresult : " + result);
				Thread.sleep(1000);
				if (result.equals("OK")) {
					System.out.println("update success");
				} else if (result.equals("CREATED")) {
					System.out.println("add success");
				}

			} catch (Exception e) {
				e.toString();
			}

		}
	}

	public static void addPl() {
		ActionDao planeDao = FactoryDao.createDAO(FactoryDao.PILOTE);
		Pilote p = new Pilote("gtx15z", "20147", "Mamadou Yéro", "BALDE", "mamadouyerobalde89@gmail.com", "test", "test");

		try {
			String id = p.getId();
			String result = planeDao.add(ConvertObject.ObjectToByte(p), id);
			System.out.println("addresult : " + result);
			Thread.sleep(1000);
			if (result.equals("OK")) {
				System.out.println("update success");
			} else if (result.equals("CREATED")) {
				System.out.println("add success");
			}

		} catch (Exception e) {
			e.toString();
		}

	}

	public static void addFl() throws Exception {
		ActionDao flightDao = FactoryDao.createDAO(FactoryDao.FLIGHT);
		List<String> p = new ArrayList<String>();
		p.add("azerty");
		p.add("jhuy7x");

		Flight[] f = new Flight[5];
		f[0] = new Flight("flight1", "bourget", "bourget", "2019-02-24", "20:00", "gtx15z", 4, p);
		f[1] = new Flight("f2", "cdg", "orly", "2019-02-25", "09:15", "gtx15z", 3, p);

		//mapper.writeValue(System.out, f);
		for (int i = 0; i < 2; i++) {
			
				System.out.println("add before");
				String id = f[i].getId();
				System.out.println(ConvertObject.ObjectToByte(f[i]));

				String result = flightDao.add(ConvertObject.ObjectToByte(f[i]), id);
				System.out.println("addresult : " + result);
				Thread.sleep(1000);
				if (result.equals("OK")) {
					System.out.println("update success");
				} else if (result.equals("CREATED")) {
					System.out.println("add success");
				}

			

		}
	}
}
