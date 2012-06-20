package com.trs.smas.tracking.exception;

public class SMASDataTrackingException extends Exception {

	/**
	 * generated serial version id
	 */
	private static final long serialVersionUID = -8249407076238586657L;
	
	public SMASDataTrackingException(String message){
		super(message);
	}
	
	public SMASDataTrackingException(String message, Throwable cause){
		super(message, cause);
	}
	
	public SMASDataTrackingException(Throwable cause){
		super(cause);
	}
	
	public Throwable getRootCause(){
		Throwable rootCause = null;
		Throwable cause = getCause();
		while(cause != null && cause != rootCause){
			rootCause = cause;
			cause = cause.getCause();
		}
		return rootCause;
	}
	
	public Throwable getMostSpecificCause(){
		Throwable rootCause = getRootCause();
		return (rootCause != null)? rootCause : this;
	}

}
