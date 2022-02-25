package com.epf.rentmanager.ui.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

@WebServlet(urlPatterns = "/home")

public class HomeServlet extends HttpServlet {                         

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nbClients = 0;
		int nbVehicles = 0;
		int nbResa = 0;
		try {
			nbClients = ClientService.getInstance().count();
			nbVehicles = VehicleService.getInstance().count();
			nbResa = ReservationService.getInstance().count();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		request.setAttribute("nbClients", nbClients);
		request.setAttribute("nbVehicles", nbVehicles);
		request.setAttribute("nbResa", nbResa);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp");
		dispatcher.forward(request, response);
	}
                        
}