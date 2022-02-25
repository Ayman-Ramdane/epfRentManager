package com.epf.rentmanager.ui.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.VehicleService;

@WebServlet(urlPatterns = "/cars")
public class VehicleListServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<Vehicle> vehicles = VehicleService.getInstance().findAll();
			request.setAttribute("vehicles", vehicles);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/list.jsp");
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

}
