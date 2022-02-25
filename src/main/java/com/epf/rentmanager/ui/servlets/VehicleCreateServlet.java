package com.epf.rentmanager.ui.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.VehicleService;

@WebServlet(urlPatterns = "/cars/create")
public class VehicleCreateServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/create.jsp");
		dispatcher.forward(request, response);
	}
                        
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   String marque = request.getParameter("manufacturer");
		   String model = request.getParameter("modele");
		   String seats = request.getParameter("seats");
		   int nbPlace = Integer.parseInt(seats);
		   Vehicle vehicle = new Vehicle(0, marque, model, nbPlace);
		   try {
			VehicleService.getInstance().create(vehicle);
			
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		   response.sendRedirect("/rentmanager/cars");
	}

}
