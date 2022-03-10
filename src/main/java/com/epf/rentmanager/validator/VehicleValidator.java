package com.epf.rentmanager.validator;

import org.springframework.stereotype.Repository;

import com.epf.rentmanager.model.Vehicle;

@Repository

public class VehicleValidator {

	private VehicleValidator() {
	}

	public Boolean constructorIsValid(Vehicle vehicle) {
		String vehicleConstructor = vehicle.getConstructor();
		return !vehicleConstructor.isBlank();
	}

	public Boolean modelIsValid(Vehicle vehicle) {
		String vehicleModel = vehicle.getModel();
		return !vehicleModel.isBlank();
	}

	public Boolean seatIsValid(Vehicle vehicle) {

		int vehicleSeats = vehicle.getNbPlaces();
		return vehicleSeats > 1 & vehicleSeats < 10;
	}

}