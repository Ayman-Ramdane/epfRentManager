package com.epf.rentmanager.exception;

public class VehicleException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VehicleException() {
		super("Exception, les données du véhicule ne sont pas valides");
	}

}
