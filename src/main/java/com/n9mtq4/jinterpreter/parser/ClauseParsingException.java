package com.n9mtq4.jinterpreter.parser;

/**
 * Created by will on 7/27/15 at 8:27 AM.
 */
public class ClauseParsingException extends Exception {
	
	public ClauseParsingException() {
	}
	
	public ClauseParsingException(String message) {
		super(message);
	}
	
	public ClauseParsingException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ClauseParsingException(Throwable cause) {
		super(cause);
	}
	
	public ClauseParsingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
}
