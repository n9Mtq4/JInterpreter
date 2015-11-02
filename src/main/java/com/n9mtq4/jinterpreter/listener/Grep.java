package com.n9mtq4.jinterpreter.listener;

import com.n9mtq4.jinterpreter.utils.ArgParser;
import com.n9mtq4.jinterpreter.utils.NoSuchValueInArgumentException;
import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.events.ObjectEvent;
import com.n9mtq4.logwindow.listener.ObjectListener;
import com.n9mtq4.logwindow.utils.StringParser;

/**
 * Created by will on 7/7/15 at 11:04 PM.
 */
public class Grep implements ObjectListener {
	
	@Override
	public void objectReceived(ObjectEvent objectEvent, BaseConsole baseConsole) {
		
		if (!objectEvent.isUserInputString()) return;
		StringParser stringParser = new StringParser(objectEvent);
		
		if (!stringParser.getArg(0).equalsIgnoreCase("grep")) return;
		
		ArgParser argParser = new ArgParser(stringParser.getArgs());
		
		String seperation = "\n";
		if (argParser.contains("-s")) {
			seperation = " ";
		}
		try {
			seperation = argParser.getString("-s");
		}catch (NoSuchValueInArgumentException e) {
//			this is 100% ok, just revert to default
		}
		
		try {
			String find = argParser.getString("-f");
		}catch (NoSuchValueInArgumentException e) {
			baseConsole.println("Please add a -f for what you want to find");
		}
		
	}
}
