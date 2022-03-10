package com.epf.rentmanager.validator;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.epf.rentmanager.model.Reservation;

@Repository

public class RentValidator {

	private RentValidator() {
	}

	public Boolean rentalIsPossible(Reservation rentToCheck, List<Reservation> listOfRentsByVehicleOrClientId) {
		LocalDate startingDateToCheck = rentToCheck.getDateStart();
		for (Reservation rent : listOfRentsByVehicleOrClientId) {
			LocalDate startingDate = rent.getDateStart();
			LocalDate endingDate = rent.getDateEnd();
			if (startingDateToCheck.isAfter(startingDate) & startingDateToCheck.isBefore(endingDate)
					| startingDateToCheck.isEqual(startingDate) | startingDateToCheck.isEqual(endingDate)) {
				return false;
			}
		}
		return true;
	}

	public Boolean startDateIsValid(Reservation rentToCheck) {
		LocalDate startingDateToCheck = rentToCheck.getDateStart();
		LocalDate Today = LocalDate.now();
		if (startingDateToCheck.isAfter(Today) | startingDateToCheck.isEqual(Today)) {
			return true;
		}
		return false;
	}

	public Boolean endDateIsValid(Reservation rentToCheck) {
		LocalDate startingDateToCheck = rentToCheck.getDateStart();
		LocalDate endingDateToCheck = rentToCheck.getDateEnd();
		LocalDate limitEndingDate = startingDateToCheck.plus(8, ChronoUnit.DAYS);

		if (endingDateToCheck.isAfter(startingDateToCheck) & endingDateToCheck.isBefore(limitEndingDate)) {
			return true;
		}
		return false;
	}

	public Boolean maxRentalTimeOfVehicleByOneUser(Reservation rentToCheck, List<Reservation> listOfRentsByClientId) {

		Comparator<Reservation> compareByStartDate = new Comparator<Reservation>() {
			@Override
			public int compare(Reservation rent1, Reservation rent2) {
				return rent1.getDateStart().compareTo(rent2.getDateStart());
			}
		};

		int numberOfRents = listOfRentsByClientId.size();
		if (numberOfRents != 0) {
			int vehicleId = rentToCheck.getVehicleId();
			Collections.sort(listOfRentsByClientId, compareByStartDate);

			LocalDate startDate = rentToCheck.getDateStart();
			LocalDate endDate = rentToCheck.getDateEnd();
			long totalSuccessiveRentDuration = ChronoUnit.DAYS.between(startDate, endDate);

			Reservation lastRent = listOfRentsByClientId.get(numberOfRents - 1);
			LocalDate lastStartDate = lastRent.getDateStart();
			LocalDate lastEndDate = lastRent.getDateEnd();

			if (startDate.isEqual(lastEndDate.plus(1, ChronoUnit.DAYS)) & vehicleId == lastRent.getVehicleId()) {
				totalSuccessiveRentDuration = totalSuccessiveRentDuration
						+ ChronoUnit.DAYS.between(lastStartDate, lastEndDate);

				for (int i = numberOfRents - 1; i > 0; i--) {
					Reservation rent1 = listOfRentsByClientId.get(i);
					LocalDate startDate1 = rent1.getDateStart();

					Reservation rent2 = listOfRentsByClientId.get(i - 1);
					LocalDate startDate2 = rent2.getDateStart();
					LocalDate endDate2 = rent2.getDateEnd();
					long rentDuration2 = ChronoUnit.DAYS.between(startDate2, endDate2);

					if (startDate1.isEqual(endDate2.plus(1, ChronoUnit.DAYS)) & vehicleId == rent2.getVehicleId()) {
						totalSuccessiveRentDuration = totalSuccessiveRentDuration + rentDuration2;
					} else {
						break;
					}
				}
				System.out.println(totalSuccessiveRentDuration);
				return totalSuccessiveRentDuration <= 7;
			}
		}
		return true;
	}

	public Boolean maxRentalTimeForOneVehicle(Reservation rentToCheck, List<Reservation> listOfRentsByVehicleId) {

		Comparator<Reservation> compareByStartDate = new Comparator<Reservation>() {
			@Override
			public int compare(Reservation rent1, Reservation rent2) {
				return rent1.getDateStart().compareTo(rent2.getDateStart());
			}
		};

		int numberOfRents = listOfRentsByVehicleId.size();

		if (numberOfRents != 0) {
			Collections.sort(listOfRentsByVehicleId, compareByStartDate);

			LocalDate startDate = rentToCheck.getDateStart();
			LocalDate endDate = rentToCheck.getDateEnd();
			long totalSuccessiveRentDuration = ChronoUnit.DAYS.between(startDate, endDate);

			Reservation lastRent = listOfRentsByVehicleId.get(numberOfRents - 1);
			LocalDate lastStartDate = lastRent.getDateStart();
			LocalDate lastEndDate = lastRent.getDateEnd();

			if (startDate.isEqual(lastEndDate.plus(1, ChronoUnit.DAYS))) {
				totalSuccessiveRentDuration = totalSuccessiveRentDuration
						+ ChronoUnit.DAYS.between(lastStartDate, lastEndDate);

				for (int i = numberOfRents - 1; i > 0; i--) {
					Reservation rent1 = listOfRentsByVehicleId.get(i);
					LocalDate startDate1 = rent1.getDateStart();

					Reservation rent2 = listOfRentsByVehicleId.get(i - 1);
					LocalDate startDate2 = rent2.getDateStart();
					LocalDate endDate2 = rent2.getDateEnd();
					long rentDuration2 = ChronoUnit.DAYS.between(startDate2, endDate2);

					if (startDate1.isEqual(endDate2.plus(1, ChronoUnit.DAYS))) {
						totalSuccessiveRentDuration = totalSuccessiveRentDuration + rentDuration2;

					} else {
						break;
					}
				}
				return totalSuccessiveRentDuration <= 30;
			}
		}
		return true;
	}

}