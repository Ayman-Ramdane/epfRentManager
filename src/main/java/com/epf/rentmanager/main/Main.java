package com.epf.rentmanager.main;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {

//	public static void main(String[] args) {
//		try {
//			System.out.println(ClientService.getInstance().findAll());
//		} catch (ServiceException e) {
//			e.printStackTrace();
//		}
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//		LocalDate date = LocalDate.now();
//		System.out.println(date);
//		LocalDate date1 = LocalDate.parse("23/02/2022", formatter);
//		System.out.println(date1);
//	}

	public static void main(String[] args) {
		try {
//			Reservation reservation1 = new Reservation(1, 2, 1, LocalDate.of(2022,2,14), LocalDate.of(2022,2,16));
//			System.out.println(ReservationService.getInstance().create(reservation1));
//			Reservation reservation2 = new Reservation(2, 3, 2, LocalDate.of(2022,3,22), LocalDate.of(2022,4,16));
//			System.out.println(ReservationService.getInstance().create(reservation2));
//			Reservation reservation3 = new Reservation(3, 3, 1, LocalDate.of(2022,2,14), LocalDate.of(2022,2,16));
//			System.out.println(ReservationService.getInstance().create(reservation3));
//			Reservation reservation4 = new Reservation(4, 5, 2, LocalDate.of(2022,1,14), LocalDate.of(2022,2,16));
//			System.out.println(ReservationService.getInstance().create(reservation4));
//			Reservation reservation5 = new Reservation(5, 1, 1, LocalDate.of(2022,2,14), LocalDate.of(2022,10,16));
//			System.out.println(ReservationService.getInstance().create(reservation5));
//			Reservation reservation6 = new Reservation(6, 1, 4, LocalDate.of(2022,10,14), LocalDate.of(2022,9,16));
//			System.out.println(ReservationService.getInstance().create(reservation6));
//			System.out.println(ReservationService.getInstance().delete(reservation6));
//			System.out.println(ReservationService.getInstance().findResaByVehicleId(2));
//			System.out.println(ReservationService.getInstance().findResaByClientId(3));
//			System.out.println(ReservationService.getInstance().findAll());
			System.out.println(ReservationService.getInstance().joinAll());
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

//	public static void main(String[] args) {
//		try {
//			Vehicle vehicle1 = new Vehicle(0, "Tesla", 2);
//			System.out.println(VehicleService.getInstance().create(vehicle1));
//			System.out.println(VehicleService.getInstance().findAll());
//			System.out.println(VehicleService.getInstance().delete(vehicle1));
//			System.out.println(VehicleService.getInstance().findById(2));
//			List<Vehicle> vehicles = VehicleService.getInstance().findAll();
//			System.out.println(vehicles);
//
//			System.out.println(VehicleService.getInstance().count());
//		} catch (ServiceException e) {
//			e.printStackTrace();
//		}
//	}

}
