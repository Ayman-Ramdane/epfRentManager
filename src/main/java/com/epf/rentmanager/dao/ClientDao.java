package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.persistence.ConnectionManager;

@Repository
public class ClientDao {

	private ClientDao() {
	}

	private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
	private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
	private static final String FIND_CLIENT_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client WHERE id=?;";
	private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";
	private static final String COUNT_CLIENTS_QUERY = "SELECT COUNT(*) FROM Client;";

	public int create(Client client) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();

			PreparedStatement pstmt = conn.prepareStatement(CREATE_CLIENT_QUERY);

			pstmt.setString(1, client.getFirstname());
			pstmt.setString(2, client.getLastname());
			pstmt.setString(3, client.getEmail());
			pstmt.setDate(4, Date.valueOf(client.getBirthdate()));

			int row = pstmt.executeUpdate();
			conn.close();
			return row;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public long delete(Client client) throws DaoException {
		long numberDeleted = 0;
		try {
			Connection conn = ConnectionManager.getConnection();

			PreparedStatement pstmt = conn.prepareStatement(DELETE_CLIENT_QUERY);
			pstmt.setInt(1, client.getId());

			numberDeleted = pstmt.executeUpdate();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numberDeleted;
	}

	public Optional<Client> findById(int id) throws DaoException {

		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_CLIENT_QUERY);

			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			rs.next();

			int clientId = rs.getInt("id");
			String clientName = rs.getString("nom");
			String clientLastName = rs.getString("prenom");
			String clientEmail = rs.getString("email");
			LocalDate clientBirthDate = rs.getDate("naissance").toLocalDate();

			Client client = new Client(clientId, clientName, clientLastName, clientEmail, clientBirthDate);
			conn.close();

			return Optional.of(client); // Or Optional.ofNullable(client)

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Optional.empty();
	}

	public List<Client> findAll() throws DaoException {

		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_CLIENTS_QUERY);

			ResultSet rs = pstmt.executeQuery();

			List<Client> clients = new ArrayList<Client>();
			while (rs.next()) {
				int clientId = rs.getInt("id");
				String clientName = rs.getString("nom");
				String clientLastName = rs.getString("prenom");
				String clientEmail = rs.getString("email");
				LocalDate clientBirthDate = rs.getDate("naissance").toLocalDate();

				Client client = new Client(clientId, clientName, clientLastName, clientEmail, clientBirthDate);

				clients.add(client);

			}
			conn.close();
			return clients;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Collections.emptyList();
	}

	public int count() throws DaoException {
		int nbClients = 0;
		try {
			Connection cm = ConnectionManager.getConnection();
			PreparedStatement pstmt = cm.prepareStatement(COUNT_CLIENTS_QUERY);
			ResultSet rs = pstmt.executeQuery();

			rs.last();

			nbClients = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return nbClients;
	}
}