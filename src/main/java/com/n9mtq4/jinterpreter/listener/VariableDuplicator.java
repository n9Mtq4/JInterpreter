package com.n9mtq4.jinterpreter.listener;

import com.n9mtq4.jinterpreter.JInterpreter;
import com.n9mtq4.jinterpreter.runtime.JIntVariable;
import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.events.ObjectEvent;
import com.n9mtq4.logwindow.listener.ObjectListener;
import com.n9mtq4.logwindow.utils.StringParser;

/**
 * Created by will on 6/25/15 at 8:48 PM.
 */
public class VariableDuplicator implements ObjectListener {
	
	/**
	 * 0 = set
	 * 1 = new variable name
	 * 2 = old variable name
	 * */
	@Override
	public void objectReceived(ObjectEvent objectEvent, BaseConsole baseConsole) {
		
		if (!objectEvent.isUserInputString()) return;
		StringParser stringParser = new StringParser(objectEvent);
		
		if (!stringParser.getArg(0).equalsIgnoreCase("set")) return;
		if (stringParser.getLength() != 3) return;
		if (!stringParser.getArg(2).startsWith("{") || !stringParser.getArg(2).endsWith("}")) return;
		
		String newVarName = stringParser.getArg(1);
		String oldVarName = stringParser.getArg(2).substring(1, stringParser.getArg(2).length() - 1);
		
		JIntVariable oldVar = JInterpreter.instance.getRuntime().getVariableByName(oldVarName);
		if (oldVar == null) {
			baseConsole.println("No variable with name: " + oldVarName);
			return;
		}
		
		JIntVariable<Object> newVar = new JIntVariable<Object>(newVarName, oldVar.getValue());
		JInterpreter.instance.getRuntime().addVariable(newVar);
		baseConsole.println("Added variable: " + newVarName + " which is a duplicate of " + oldVarName);
		
	}
	
}
