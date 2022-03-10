package com.epf.rentmanager.exception;

public class RentException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RentException() {
		super("Exception, les données du véhicule ne sont pas valides");
	}

}
