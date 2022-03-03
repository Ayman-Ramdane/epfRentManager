package com.epf.rentmanager.ui.servlets;

import java.io.IOException;
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
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.VehicleService;

@WebServlet(urlPatterns = "/cars")
public class VehicleListServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		try {
			List<Vehicle> vehicles = vehicleService.findAll();
			request.setAttribute("vehicles", vehicles);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/list.jsp");
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int vehicleId = -1;
		String vehicleIdString = request.getParameter("delete");
		try {
			if (vehicleIdString != null) {
				vehicleId = Integer.parseInt(vehicleIdString);
				Vehicle vehicule = new Vehicle(vehicleId, "", "", 0);
				vehicleService.delete(vehicule);
				response.sendRedirect("/rentmanager/cars");
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}