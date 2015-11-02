package com.n9mtq4.jinterpreter.listener;

import com.n9mtq4.jinterpreter.JInterpreter;
import com.n9mtq4.jinterpreter.runtime.JIntVariable;
import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.events.ObjectEvent;
import com.n9mtq4.logwindow.listener.ObjectListener;
import com.n9mtq4.logwindow.utils.StringParser;

/**
 * Created by will on 6/24/15 at 10:13 PM.
 */
public class VariableValue implements ObjectListener {
	
	/**
	 * 0 = get
	 * 1 = variable name
	 * */
	@Override
	public void objectReceived(ObjectEvent objectEvent, BaseConsole baseConsole) {
		
		if (!objectEvent.isUserInputString()) return;
		StringParser stringParser = new StringParser(objectEvent);
		
		if (!stringParser.getArg(0).equalsIgnoreCase("get")) return;
		if (stringParser.getLength() != 2) return;
		
		String varName = stringParser.getArg(1);
		JIntVariable var = JInterpreter.instance.getRuntime().getVariableByName(varName);
		
		if (var == null) {
			baseConsole.println(varName + " doesn't exist!");
			return;
		}
		
		baseConsole.println(varName + " is an instance of class " + var.getValueType().getName() + " with a value of: " + var.getValue().toString());
		
	}
	
}
