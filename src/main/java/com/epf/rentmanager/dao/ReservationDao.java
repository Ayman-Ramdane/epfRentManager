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

import org.springframework.stereotype.Repository;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.persistence.ConnectionManager;

@Repository
public class ReservationDao {

	private ReservationDao() {
	}

	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, constructeur, modele, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
	private static final String COUNT_RESERVATIONS_QUERY = "SELECT COUNT(*) FROM Reservation;";
	private static final String FIND_RESERVATIONS_CLIENTS_VEHICLES_QUERY = "SELECT R.id, nom, prenom, constructeur, modele, debut, fin FROM Reservation AS R INNER JOIN Vehicle AS V ON R.vehicle_id = V.id INNER JOIN Client AS C ON C.id = R.client_id;";
	private static final String FIND_RESERVATIONS_VEHICLE_BY_CLIENT_QUERY = "SELECT R.id, constructeur, modele, debut, fin FROM Reservation AS R INNER JOIN Vehicle AS V ON R.vehicle_id = V.id WHERE R.client_id=?;";
	private static final String COUNT_RESERVATIONS_BY_CLIENT_QUERY = "SELECT COUNT(*) FROM Reservation WHERE client_id=?;";

	public long create(Reservation reservation) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();

			PreparedStatement pstmt = conn.prepareStatement(CREATE_RESERVATION_QUERY);

			pstmt.setInt(1, reservation.getClientId());
			pstmt.setInt(2, reservation.getVehicleId());
			pstmt.setDate(3, Date.valueOf(reservation.getDateStart()));
			pstmt.setDate(4, Date.valueOf(reservation.getDateEnd()));

			int row = pstmt.executeUpdate();
			conn.close();
			return row;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;

	}

	public long delete(Reservation reservation) throws DaoException {
		long numberDeleted = 0;
		try {
			Connection conn = ConnectionManager.getConnection();

			PreparedStatement pstmt = conn.prepareStatement(DELETE_RESERVATION_QUERY);
			pstmt.setInt(1, reservation.getId());

			numberDeleted = pstmt.executeUpdate();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numberDeleted;

	}

	public List<Reservation> findResaByClientId(int clientId) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);

			pstmt.setInt(1, clientId);

			ResultSet rs = pstmt.executeQuery();

			List<Reservation> reservations = new ArrayList<Reservation>();
			while (rs.next()) {

				int reservationId = rs.getInt("id");
				int vehicleId = rs.getInt("vehicle_id");
				LocalDate dateStart = rs.getDate("debut").toLocalDate();
				LocalDate dateEnd = rs.getDate("fin").toLocalDate();

				Reservation reservation = new Reservation(reservationId, clientId, vehicleId, dateStart, dateEnd);
				reservations.add(reservation);
			}
			conn.close();
			return reservations;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Collections.emptyList();

	}

	public List<Reservation> findResaByVehicleId(int vehicleId) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY);

			pstmt.setInt(1, vehicleId);

			ResultSet rs = pstmt.executeQuery();

			List<Reservation> reservations = new ArrayList<Reservation>();
			while (rs.next()) {

				int reservationId = rs.getInt("id");
				int ClientId = rs.getInt("client_id");
				LocalDate dateStart = rs.getDate("debut").toLocalDate();
				LocalDate dateEnd = rs.getDate("fin").toLocalDate();

				Reservation reservation = new Reservation(reservationId, ClientId, vehicleId, dateStart, dateEnd);
				reservations.add(reservation);
			}
			conn.close();
			return reservations;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Collections.emptyList();

	}

	public List<Reservation> findAll() throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATIONS_QUERY);

			ResultSet rs = pstmt.executeQuery();

			List<Reservation> reservations = new ArrayList<Reservation>();
			while (rs.next()) {
				int reservationId = rs.getInt("id");
				int clientId = rs.getInt("client_id");
				int vehicleId = rs.getInt("vehicle_id");
				LocalDate dateStart = rs.getDate("debut").toLocalDate();
				LocalDate dateEnd = rs.getDate("fin").toLocalDate();

				Reservation reservation = new Reservation(reservationId, clientId, vehicleId, dateStart, dateEnd);

				reservations.add(reservation);
			}
			conn.close();
			return reservations;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Collections.emptyList();
	}

	public int count() throws DaoException {
		int nbResa = 0;
		try {
			Connection cm = ConnectionManager.getConnection();
			PreparedStatement pstmt = cm.prepareStatement(COUNT_RESERVATIONS_QUERY);
			ResultSet rs = pstmt.executeQuery();

			rs.last();

			nbResa = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return nbResa;
	}

	public List<Reservation> joinAll() throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATIONS_CLIENTS_VEHICLES_QUERY);

			ResultSet rs = pstmt.executeQuery();

			List<Reservation> reservations = new ArrayList<Reservation>();
			while (rs.next()) {
				int reservationId = rs.getInt("id");
				String client = rs.getString("nom") + " " + rs.getString("prenom");
				String vehicle = rs.getString("constructeur") + " " + rs.getString("modele");
				LocalDate dateStart = rs.getDate("debut").toLocalDate();
				LocalDate dateEnd = rs.getDate("fin").toLocalDate();

				Reservation reservation = new Reservation(reservationId, client, vehicle, dateStart, dateEnd);

				reservations.add(reservation);
			}
			conn.close();
			return reservations;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Collections.emptyList();
	}

	public List<Reservation> findResaAndVehicleByClientId(int clientId) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATIONS_VEHICLE_BY_CLIENT_QUERY);

			pstmt.setInt(1, clientId);

			ResultSet rs = pstmt.executeQuery();

			List<Reservation> reservations = new ArrayList<Reservation>();
			while (rs.next()) {

				int reservationId = rs.getInt("id");
				String vehicle = rs.getString("constructeur") + " " + rs.getString("modele");
				LocalDate dateStart = rs.getDate("debut").toLocalDate();
				LocalDate dateEnd = rs.getDate("fin").toLocalDate();

				Reservation reservation = new Reservation(reservationId, clientId, vehicle, dateStart, dateEnd);
				reservations.add(reservation);
			}
			conn.close();
			return reservations;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Collections.emptyList();
	}

	public int countByClientId(int clientId) throws DaoException {
		int nbResa = 0;
		try {
			Connection cm = ConnectionManager.getConnection();
			PreparedStatement pstmt = cm.prepareStatement(COUNT_RESERVATIONS_BY_CLIENT_QUERY);
			pstmt.setInt(1, clientId);
			ResultSet rs = pstmt.executeQuery();

			rs.last();

			nbResa = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nbResa;
	}
}
