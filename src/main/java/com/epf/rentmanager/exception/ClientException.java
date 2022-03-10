package com.epf.rentmanager.exception;

public class ClientException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClientException() {
		super("Exception, les données du client ne sont pas valides");
	}

}
