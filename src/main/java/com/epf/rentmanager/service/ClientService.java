package com.epf.rentmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.exception.ClientException;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.validator.ClientValidator;

@Service
public class ClientService {

	private ClientDao clientDao;
	private ReservationDao reservationDao;
	private ClientValidator clientValidator;

	private ClientService(ClientDao clientDao, ClientValidator clientValidator, ReservationDao reservationDao) {
		this.clientDao = clientDao;
		this.reservationDao = reservationDao;
		this.clientValidator = clientValidator;
	}

	public int create(Client client) throws ClientException {
		List<Client> listClients = null;
		try {
			listClients = this.clientDao.findAll();
		} catch (DaoException e1) {
			e1.printStackTrace();
		}

		if (clientValidator.isOver18(client) & clientValidator.firstNameIsValid(client)
				& clientValidator.lastNameIsValid(client) & clientValidator.emailNotTaken(client, listClients)
				& clientValidator.emailIsValid(client)) {
			try {
				return this.clientDao.create(client);
			} catch (DaoException e) {
				e.printStackTrace();
			}
		} else {
			throw new ClientException();
		}
		return 0;
	}

	public int update(Client client) throws ClientException {
		List<Client> listClients = null;
		try {
			listClients = this.clientDao.findAll();
		} catch (DaoException e1) {
			e1.printStackTrace();
		}

		if (clientValidator.isOver18(client) & clientValidator.firstNameIsValid(client)
				& clientValidator.lastNameIsValid(client) & clientValidator.emailNotTaken(client, listClients)
				& clientValidator.emailIsValid(client)) {
			try {
				return this.clientDao.update(client);
			} catch (DaoException e) {
				e.printStackTrace();
			}
		} else {
			throw new ClientException();
		}
		return 0;
	}

	public long delete(Client client) throws ServiceException {
		int clietnId = client.getId();
		try {
			List<Reservation> listReservations = reservationDao.findResaByClientId(clietnId);
			for (Reservation reservation : listReservations) {
				reservationDao.delete(reservation);
			}
			return this.clientDao.delete(client);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public Client findById(int id) throws ServiceException {
		try {
			return this.clientDao.findById(id).get();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Client> findAll() throws ServiceException {
		try {
			return this.clientDao.findAll();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int count() throws ServiceException {
		int nbClient = 0;
		try {
			nbClient = this.clientDao.count();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return nbClient;
	}

}