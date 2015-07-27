package com.n9mtq4.jinterpreter.utils;

import java.util.Arrays;

/**
 * Created by Will on 1/5/15.<br>
 * Taken from old code used in
 * https://github.com/n9Mtq4/Life-Dot-Jar/
 */
@SuppressWarnings("unused")
public class ArgParser {
	
	private String[] args;
	
	public ArgParser(String[] args) {
		this.args = args;
	}
	
	public boolean contains(String s) {
		for (String s1 : args) {
			if (s1.equals(s)) return true;
		}
		return false;
	}
	
	public boolean containsIgnoreCase(String s) {
		for (String s1 : args) {
			if (s1.equalsIgnoreCase(s)) return true;
		}
		return false;
	}
	
	public String getString(String key) throws NoSuchValueInArgumentException {
		if (!containsIgnoreCase(key)) {
			throw new NoSuchValueInArgumentException(Arrays.toString(args), key);
		}
		int i = 0;
		for (; i < args.length; i++) {
			if (args[i].equalsIgnoreCase(key)) break;
		}
		return args[++i];
	}
	
	public boolean getBoolean(String key) throws NoSuchValueInArgumentException {
		String value = getString(key);
		if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("yes")) return true;
		if (value.equalsIgnoreCase("false") || value.equalsIgnoreCase("no")) return false;
		throw new NoSuchValueInArgumentException(Arrays.toString(args), key);
	}
	
	public int getInt(String key) throws NoSuchValueInArgumentException, NumberFormatException {
		return Integer.parseInt(getString(key));
	}
	
	public double getDouble(String key) throws NoSuchValueInArgumentException, NumberFormatException {
		return Double.parseDouble(getString(key));
	}
	
	public String[] getArgs() {
		return args;
	}
	
}
