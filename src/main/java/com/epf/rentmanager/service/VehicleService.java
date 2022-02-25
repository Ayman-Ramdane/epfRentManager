package com.epf.rentmanager.service;

import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.dao.VehicleDao;

public class VehicleService {

	private VehicleDao vehicleDao;
	public static VehicleService instance;

	private VehicleService() {
		this.vehicleDao = VehicleDao.getInstance();
	}

	public static VehicleService getInstance() {
		if (instance == null) {
			instance = new VehicleService();
		}

		return instance;
	}

	public long create(Vehicle vehicle) throws ServiceException {
		long numberCreated = 0;

		try {
			numberCreated = this.vehicleDao.create(vehicle);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return numberCreated;
	}

	public long delete(Vehicle vehicle) throws ServiceException {
		long numberDeleted = 0;

		try {
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

}