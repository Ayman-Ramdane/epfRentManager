package com.epf.rentmanager.validator;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Repository;

import com.epf.rentmanager.model.Client;

@Repository

public class ClientValidator {

	private ClientValidator() {
	}

	public Boolean isOver18(Client client) {

		LocalDate minBirthDate = LocalDate.now().minus(18, ChronoUnit.YEARS);
		minBirthDate = minBirthDate.plus(1, ChronoUnit.DAYS);
		LocalDate clientBirthDate = client.getBirthdate();
		return minBirthDate.isAfter(clientBirthDate);
	}

	public Boolean firstNameIsValid(Client client) {

		String clientFirstName = client.getFirstname();
		int firstNameLength = clientFirstName.replace(" ", "").length();
		Boolean notStartingWithSpace = clientFirstName == clientFirstName.trim();
		return firstNameLength > 2 & !clientFirstName.isBlank() & notStartingWithSpace;
	}

	public Boolean lastNameIsValid(Client client) {

		String clientLastName = client.getLastname();
		int lastNameLength = clientLastName.replace(" ", "").length();
		Boolean notStartingWithSpace = clientLastName == clientLastName.trim();

		return lastNameLength > 2 & !clientLastName.isBlank() & notStartingWithSpace;
	}

	public Boolean emailIsValid(Client client) {
		String email = client.getEmail();
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";

		Pattern pat = Pattern.compile(emailRegex);
		if (email == null)
			return false;
		return pat.matcher(email).matches();
	}

	public Boolean emailNotTaken(Client clientToCheck, List<Client> listClients) {

		String emailToCheck = clientToCheck.getEmail();
		int clientToCheckId = clientToCheck.getId();
		for (Client client : listClients) {
			int clientId = client.getId();
			String email = client.getEmail();

			if (emailToCheck.equals(email) & clientToCheckId != clientId) {
				return false;
			}
		}
		return true;
	}

}