package com.sainsburys.consoleapp.exception;

public class ScraperException extends Exception{

	public static final long serialVersionUID = 1L;
	/**
	 * Custom exception class to throw application exception which something goes wrong.
	 * @param message
	 */
    public ScraperException(String message) {
        super(message);
    }
}
