package com.flight_sharing.dao;

import com.flight_sharing.entities.Passenger;
import com.flight_sharing.json.ConvertObject;

public class InsertData {

	public static void add() {
		ActionDao planeDao = FactoryDao.createDAO(FactoryDao.PASSENGER);
		Passenger[] p = new Passenger[4];
		p[0] = new Passenger("aznjt", "544", "fff", "dfgh", "dvbn,");
		p[1] =new Passenger("jhuy", "544", "fff", "dfgh", "dvbn,");
		p[2] =new Passenger("jhuf", "544", "fff", "dfgh", "dvbn,");
		p[3] =new Passenger("ljbc", "544", "fff", "dfgh", "dvbn,");
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
}
