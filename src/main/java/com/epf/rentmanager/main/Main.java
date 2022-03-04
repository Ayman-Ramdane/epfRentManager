package com.epf.rentmanager.main;

import com.epf.rentmanager.configuration.AppConfiguration;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

public class Main {

	@Autowired
	ClientService clientService;
	@Autowired
	VehicleService vehicleService;
	@Autowired 
	ReservationService reservationService;
	
	public void init() throws ServletException, InterruptedException {
		super.wait();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
		ClientService clientService = context.getBean(ClientService.class);
		VehicleService vehicleService = context.getBean(VehicleService.class);
		ReservationService reservationService = context.getBean(ReservationService.class);
		try {
			Client client = new Client(6,"AlGhul","Talia", "talia.ghul@gmail.com", LocalDate.parse("1988-10-15"));
			List<Client> clients = clientService.findAll();
			System.out.println(clients);
			clientService.update(client);
			List<Client> rents = clientService.findAll();
			System.out.println(rents);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}
