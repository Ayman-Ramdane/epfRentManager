package com.epf.rentmanager.exception;

public class ServiceException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ServiceException() {
		super("Exception au niveau du service");
	}

}
