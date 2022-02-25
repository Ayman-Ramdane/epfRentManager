package com.epf.rentmanager.ui.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

@WebServlet(urlPatterns = "/rents/create")

public class RentCreateServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Vehicle> vehicles;
		List<Client> clients;
		try {
			vehicles = VehicleService.getInstance().findAll();
			clients = ClientService.getInstance().findAll();
			request.setAttribute("listClients", clients);
			request.setAttribute("listVehicles", vehicles);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/rents/create.jsp");
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		int clientId = Integer.parseInt(request.getParameterValues("client")[0]);
		int vehicleId = Integer.parseInt(request.getParameterValues("car")[0]);

		String startDateString = request.getParameter("begin");
		LocalDate startDate = LocalDate.parse(startDateString, formatter);

		String endDateString = request.getParameter("end");
		LocalDate endDate = LocalDate.parse(endDateString, formatter);

		Reservation reservation = new Reservation(0, clientId, vehicleId, startDate, endDate);
		try {
			ReservationService.getInstance().create(reservation);
			response.sendRedirect("/rentmanager/rents");
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

}