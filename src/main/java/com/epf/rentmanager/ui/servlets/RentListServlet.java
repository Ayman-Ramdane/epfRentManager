package com.epf.rentmanager.ui.servlets;

import java.io.IOException;
import java.time.LocalDate;
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
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ReservationService;

@WebServlet(urlPatterns = "/rents")

public class RentListServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		List<Reservation> rents;
		try {
			rents = reservationService.joinAll();
			request.setAttribute("rents", rents);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/rents/list.jsp");
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int rentId = -1;
		String rentIdString = request.getParameter("delete");
		try {
			if (rentIdString != null) {
				rentId = Integer.parseInt(rentIdString);
				Reservation reservation = new Reservation(rentId, 0, 0, LocalDate.now(), LocalDate.now());
				reservationService.delete(reservation);
				response.sendRedirect("/rentmanager/rents");
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}