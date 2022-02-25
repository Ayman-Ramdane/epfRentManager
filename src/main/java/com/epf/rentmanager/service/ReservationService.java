package com.epf.rentmanager.service;

import java.util.List;

import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;

public class ReservationService {

	private ReservationDao reservationDao;
	public static ReservationService instance;

	private ReservationService() {
		this.reservationDao = ReservationDao.getInstance();
	}

	public static ReservationService getInstance() {
		if (instance == null) {
			instance = new ReservationService();
		}

		return instance;
	}

	public long create(Reservation reservation) throws ServiceException {
		try {
			return this.reservationDao.create(reservation);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public long delete(Reservation reservation) throws ServiceException {
		try {
			return this.reservationDao.delete(reservation);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<Reservation> findResaByClientId(int clientId) throws ServiceException {
		try {
			return this.reservationDao.findResaByClientId(clientId);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Reservation> findResaByVehicleId(int vehicleId) throws ServiceException {
		try {
			return this.reservationDao.findResaByVehicleId(vehicleId);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Reservation> findAll() throws ServiceException {
		try {
			return this.reservationDao.findAll();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Reservation> joinAll() throws ServiceException {
		try {
			return this.reservationDao.joinAll();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int count() throws ServiceException {
		int nbResa = 0;

		try {
			nbResa = this.reservationDao.count();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return nbResa;
	}

}
