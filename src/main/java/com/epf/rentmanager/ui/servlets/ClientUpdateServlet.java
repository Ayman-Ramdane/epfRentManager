package com.epf.rentmanager.ui.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ClientException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;

@WebServlet(urlPatterns = "/users/updates")
public class ClientUpdateServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	ClientService clientService;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int clientId = Integer.parseInt(request.getParameter("id"));
		LocalDate maxBirthDate = LocalDate.now().minus( 18, ChronoUnit.YEARS);
		request.setAttribute("maxBirthDate", maxBirthDate);
		request.setAttribute("clientId", clientId);
		try {
			Client client = clientService.findById(clientId);
			request.setAttribute("lastName", client.getLastname());
			request.setAttribute("firstName", client.getFirstname());
			request.setAttribute("email", client.getEmail());
			request.setAttribute("birthDate", client.getBirthdate());
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/users/update.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		int clientId = Integer.parseInt(request.getParameter("id"));
		String nom = request.getParameter("lastname");
		String prenom = request.getParameter("firstname");
		String email = request.getParameter("email");
		String birthDateString = request.getParameter("birthdate");

		LocalDate birthDate = LocalDate.parse(birthDateString, formatter);

		Client client = new Client(clientId, nom, prenom, email, birthDate);
		try {
			clientService.update(client);

		} catch (ClientException e) {
			e.printStackTrace();
		}
		response.sendRedirect("/rentmanager/users");
	}

}
