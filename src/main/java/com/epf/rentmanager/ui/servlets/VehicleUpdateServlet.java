package com.epf.rentmanager.ui.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.exception.VehicleException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;

@WebServlet(urlPatterns = "/cars/updates")
public class VehicleUpdateServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	ClientService clientService;
	@Autowired
	VehicleService vehicleService;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int vehicleId = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("vehicleId", vehicleId);
		try {
			Vehicle vehicle = vehicleService.findById(vehicleId);
			request.setAttribute("Manufacturer", vehicle.getConstructor());
			request.setAttribute("Modele", vehicle.getModel());
			request.setAttribute("Seats", vehicle.getNbPlaces());
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/update.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int vehicleId = Integer.parseInt(request.getParameter("id"));
		String marque = request.getParameter("manufacturer");
		String model = request.getParameter("modele");
		String seats = request.getParameter("seats");
		int nbPlace = Integer.parseInt(seats);
		Vehicle vehicle = new Vehicle(vehicleId, marque, model, nbPlace);
		try {
			vehicleService.update(vehicle);
		} catch (VehicleException e) {
			e.printStackTrace();
		}
		response.sendRedirect("/rentmanager/cars");
	}
}