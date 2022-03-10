package com.epf.rentmanager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.exception.VehicleException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.validator.VehicleValidator;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.dao.VehicleDao;

@Service
public class VehicleService {

	private VehicleDao vehicleDao;
	private VehicleValidator vehicleValidator;
	private ReservationDao reservationDao;

	private VehicleService(VehicleDao vehicleDao, VehicleValidator vehicleValidator, ReservationDao reservationDao) {
		this.vehicleDao = vehicleDao;
		this.reservationDao = reservationDao;
		this.vehicleValidator = vehicleValidator;
	}

	public long create(Vehicle vehicle) throws VehicleException {
		long numberCreated = 0;
		if (vehicleValidator.constructorIsValid(vehicle) & vehicleValidator.modelIsValid(vehicle)
				& vehicleValidator.seatIsValid(vehicle)) {
			try {
				numberCreated = this.vehicleDao.create(vehicle);
			} catch (DaoException e) {
				e.printStackTrace();
			}
		}
		return numberCreated;
	}

	public long update(Vehicle vehicle) throws VehicleException {
		long numberCreated = 0;
		if (vehicleValidator.constructorIsValid(vehicle) & vehicleValidator.modelIsValid(vehicle)
				& vehicleValidator.seatIsValid(vehicle)) {
			try {
				numberCreated = this.vehicleDao.update(vehicle);
			} catch (DaoException e) {
				e.printStackTrace();
			}
		}
		return numberCreated;
	}

	public long delete(Vehicle vehicle) throws ServiceException {
		long numberDeleted = 0;
		int vehicleId = vehicle.getId();
		try {
			List<Reservation> listReservations = reservationDao.findResaByVehicleId(vehicleId);
			for (Reservation reservation : listReservations) {
				reservationDao.delete(reservation);
			}
			numberDeleted = this.vehicleDao.delete(vehicle);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return numberDeleted;
	}

	public Vehicle findById(int id) throws ServiceException {
		try {
			return this.vehicleDao.findById(id).get();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;

	}

	public List<Vehicle> findAll() throws ServiceException {
		List<Vehicle> vehicleList = new ArrayList<>();

		try {
			vehicleList = this.vehicleDao.findAll();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return vehicleList;
	}

	public int count() throws ServiceException {
		int nbVehicle = 0;

		try {
			nbVehicle = this.vehicleDao.count();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return nbVehicle;
	}

	public List<Vehicle> findVehicleByClientId(int clientId) throws ServiceException {
		List<Vehicle> vehicleList = new ArrayList<>();

		try {
			vehicleList = this.vehicleDao.findVehicleByClientId(clientId);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return vehicleList;
	}
}