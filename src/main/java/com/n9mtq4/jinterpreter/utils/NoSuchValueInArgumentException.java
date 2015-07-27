package com.n9mtq4.jinterpreter.utils;

/**
 * Created by Will on 1/5/15.
 */
public class NoSuchValueInArgumentException extends Throwable {
	
	public NoSuchValueInArgumentException(String arg, String key) {
		super("No such value for " + key + " in " + arg + ".");
	}
	
}
