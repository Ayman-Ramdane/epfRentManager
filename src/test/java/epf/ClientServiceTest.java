package epf;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Period;

import javax.servlet.ServletException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;

public class ClientServiceTest {

	@Autowired
	ClientService clientService;

	public void init() throws ServletException, InterruptedException {
		super.wait();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Test
	public void isLegal_should_return_true_when_age_is_over_18() {
		// Given

		Client client = new Client(0, "lastName", "firstname", "email", LocalDate.parse("2000-02-12"));
		LocalDate birthDate = client.getBirthdate();

		Period period = Period.between(birthDate, LocalDate.now());
		int clientAge = period.getYears();

		// Then
		assertTrue("Le client a 18 ans !", clientAge >= 18);
	}

	@Test
	public void isNotLegal_should_return_true_when_age_is_over_18() {
		// Given

		Client client = new Client(0, "lastName", "firstname", "email", LocalDate.parse("2021-02-12"));
		LocalDate birthDate = client.getBirthdate();

		Period period = Period.between(birthDate, LocalDate.now());
		int clientAge = period.getYears();

		// Then
		assertTrue("Le client a 18 ans !", clientAge < 18);
	}

	@Test
	public void firstNameValid_should_return_true_when_first_name_length_is_over_3() {
		// Given
		Client client = new Client(5, "lastName", "firstName", "email", null);

		// Then
		assertTrue("Le prénom du client doit faire plus de 3 caractères",
				client.getFirstname().length() > 2 & !client.getFirstname().isBlank());
	}

	@Test
	public void lastNameValid_should_return_true_when_last_name_length_is_over_3() {
		// Given
		Client client = new Client(5, "lastName", "firstName", "email", null);

		// Then
		assertTrue("Le nom du client doit faire plus de 3 caractères",
				client.getLastname().length() > 2 & !client.getLastname().isBlank());
	}
}
