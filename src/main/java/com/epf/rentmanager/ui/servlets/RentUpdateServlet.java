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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

@WebServlet(urlPatterns = "/rents/updates")

public class RentUpdateServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	ClientService clientService;
	@Autowired
	VehicleService vehicleService;
	@Autowired
	ReservationService reservationService;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Vehicle> vehicles;
		List<Client> clients;
		int rentId = Integer.parseInt(request.getParameter("id"));
		try {
			vehicles = vehicleService.findAll();
			request.setAttribute("listVehicles", vehicles);
			clients = clientService.findAll();
			request.setAttribute("listClients", clients);

			Reservation reservation = reservationService.findResaById(rentId);
			request.setAttribute("rentId", reservation.getId());
			request.setAttribute("clientId", reservation.getClientId());
			request.setAttribute("vehicleId", reservation.getVehicleId());
			request.setAttribute("startDate", reservation.getDateStart());
			request.setAttribute("endDate", reservation.getDateEnd());

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/rents/update.jsp");
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		int rentId = Integer.parseInt(request.getParameter("id"));
		int clientId = Integer.parseInt(request.getParameterValues("client")[0]);
		int vehicleId = Integer.parseInt(request.getParameterValues("car")[0]);

		String startDateString = request.getParameter("begin");
		LocalDate startDate = LocalDate.parse(startDateString, formatter);

		String endDateString = request.getParameter("end");
		LocalDate endDate = LocalDate.parse(endDateString, formatter);

		Reservation reservation = new Reservation(rentId, clientId, vehicleId, startDate, endDate);
		try {
			reservationService.update(reservation);
			response.sendRedirect("/rentmanager/rents");
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}