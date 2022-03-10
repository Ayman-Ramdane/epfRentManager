package com.epf.rentmanager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.RentException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.validator.RentValidator;

@Service
public class ReservationService {

	private ReservationDao reservationDao;
	private RentValidator rentValidator;

	@Autowired
	private ReservationService(ReservationDao reservationDao, RentValidator rentValidator) {
		this.reservationDao = reservationDao;
		this.rentValidator = rentValidator;
	}

	public long create(Reservation reservation) throws RentException {
		List<Reservation> listOfRentsByClientId = new ArrayList<>();
		List<Reservation> listOfRentsByVehicleId = new ArrayList<>();
		int clientId = reservation.getClientId();
		int vehicleId = reservation.getVehicleId();

		try {
			listOfRentsByClientId = reservationDao.findResaByClientId(clientId);
			listOfRentsByVehicleId = reservationDao.findResaByVehicleId(vehicleId);
		} catch (DaoException e1) {
			e1.printStackTrace();
		}

		if (rentValidator.startDateIsValid(reservation) & rentValidator.endDateIsValid(reservation)
				& rentValidator.rentalIsPossible(reservation, listOfRentsByClientId)
				& rentValidator.rentalIsPossible(reservation, listOfRentsByVehicleId)
				& rentValidator.maxRentalTimeOfVehicleByOneUser(reservation, listOfRentsByClientId)
				& rentValidator.maxRentalTimeForOneVehicle(reservation, listOfRentsByVehicleId)) {
			try {
				return this.reservationDao.create(reservation);
			} catch (DaoException e) {
				e.printStackTrace();
			}
		}

		return 0;
	}

	public long update(Reservation reservation) throws RentException {
		List<Reservation> listOfRentsByClientId = null;
		List<Reservation> listOfRentsByVehicleId = null;
		int clientId = reservation.getClientId();
		int vehicleId = reservation.getVehicleId();
		try {
			listOfRentsByClientId = reservationDao.findResaByClientId(clientId);
			listOfRentsByVehicleId = reservationDao.findResaByVehicleId(vehicleId);
		} catch (DaoException e1) {
			e1.printStackTrace();
		}

		if (rentValidator.startDateIsValid(reservation) & rentValidator.endDateIsValid(reservation)
				& rentValidator.rentalIsPossible(reservation, listOfRentsByClientId)
				& rentValidator.rentalIsPossible(reservation, listOfRentsByVehicleId)
				& rentValidator.maxRentalTimeOfVehicleByOneUser(reservation, listOfRentsByClientId)
				& rentValidator.maxRentalTimeForOneVehicle(reservation, listOfRentsByVehicleId)) {
			try {
				return this.reservationDao.update(reservation);
			} catch (DaoException e) {
				e.printStackTrace();
			}
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

	public Reservation findResaById(int reservationId) throws ServiceException {
		try {
			return this.reservationDao.findResaById(reservationId);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
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

	public List<Integer> findClientIdByVehicleId(int vehicleId) throws ServiceException {
		try {
			return this.reservationDao.findClientIdByVehicleId(vehicleId);
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