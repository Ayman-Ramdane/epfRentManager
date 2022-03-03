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
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;

@WebServlet(urlPatterns = "/users")
public class ClientListServlet extends HttpServlet {

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
		List<Client> clients;
		try {
			clients = clientService.findAll();
			request.setAttribute("clients", clients);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/users/list.jsp");
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int clientId = -1;
		String clientIdString = request.getParameter("delete");
		try {
			if (clientIdString != null) {
				clientId = Integer.parseInt(clientIdString);
				Client client = new Client(clientId, "", "", "", LocalDate.now());
				clientService.delete(client);
				response.sendRedirect("/rentmanager/users");
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}
