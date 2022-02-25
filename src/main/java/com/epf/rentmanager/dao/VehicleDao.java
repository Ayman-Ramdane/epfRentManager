package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;

public class VehicleDao {

	private static VehicleDao instance = null;

	private VehicleDao() {
	}

	public static VehicleDao getInstance() {
		if (instance == null) {
			instance = new VehicleDao();
		}
		return instance;
	}

	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, modele, nb_places) VALUES(?, ?, ?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, modele, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, modele, nb_places FROM Vehicle;";
	private static final String COUNT_VEHICLES_QUERY = "SELECT COUNT(*) FROM Vehicle;";

	public long create(Vehicle vehicle) throws DaoException {
		long numberCreated = 0;
		try {
			Connection conn = ConnectionManager.getConnection();

			PreparedStatement pstmt = conn.prepareStatement(CREATE_VEHICLE_QUERY);
			pstmt.setString(1, vehicle.getConstructor());
			pstmt.setString(2, vehicle.getModel());
			pstmt.setInt(3, vehicle.getNbPlaces());

			numberCreated = pstmt.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numberCreated;

	}

	public long delete(Vehicle vehicle) throws DaoException {
		long numberDeleted = 0;
		try {
			Connection conn = ConnectionManager.getConnection();

			PreparedStatement pstmt = conn.prepareStatement(DELETE_VEHICLE_QUERY);
			pstmt.setInt(1, vehicle.getId());

			numberDeleted = pstmt.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numberDeleted;

	}

	public Optional<Vehicle> findById(int id) throws DaoException {

		try {
			Connection cm = ConnectionManager.getConnection();
			PreparedStatement pstmt = cm.prepareStatement(FIND_VEHICLE_QUERY);

			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();
			rs.next();

			String vehicleConstructor = rs.getString("constructeur");
			String vehicleModel = rs.getString("modele");
			int vehicleNbPlaces = rs.getInt("nb_places");

			Vehicle vehicle = new Vehicle(id, vehicleConstructor, vehicleModel, vehicleNbPlaces);
			cm.close();

			return Optional.of(vehicle);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Optional.empty();
	}

	public List<Vehicle> findAll() throws DaoException {
		List<Vehicle> vehicleList = new ArrayList<>();

		try {
			Connection cm = ConnectionManager.getConnection();
			PreparedStatement pstmt = cm.prepareStatement(FIND_VEHICLES_QUERY);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int vehicleId = rs.getInt("id");
				String vehicleConstructor = rs.getString("constructeur");
				String vehicleModel = rs.getString("modele");
				int vehicleNbPlaces = rs.getInt("nb_places");

				Vehicle vehicle = new Vehicle(vehicleId, vehicleConstructor, vehicleModel, vehicleNbPlaces);

				vehicleList.add(vehicle);
			}
			cm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vehicleList;
	}

	public int count() throws DaoException {
		int nbVehicles = 0;
		try {
			Connection cm = ConnectionManager.getConnection();
			PreparedStatement pstmt = cm.prepareStatement(COUNT_VEHICLES_QUERY);
			ResultSet rs = pstmt.executeQuery();

			rs.last();

			nbVehicles = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return nbVehicles;
	}

}
