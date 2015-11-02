package com.n9mtq4.jinterpreter.listener;

import com.n9mtq4.jinterpreter.JInterpreter;
import com.n9mtq4.jinterpreter.runtime.JIntParseArg;
import com.n9mtq4.jinterpreter.runtime.JIntVariable;
import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.events.ObjectEvent;
import com.n9mtq4.logwindow.listener.ObjectListener;
import com.n9mtq4.logwindow.utils.StringParser;
import com.n9mtq4.reflection.ReflectionHelper;

/**
 * Created by will on 6/15/15 at 8:54 PM.
 */
public class VariableCreator implements ObjectListener {
	
	/**
	 * 0 = set
	 * 1 = variable name<br>
	 * 2 = class name<br>
	 * 3... = arguments<br>
	 * */
	@Override
	public void objectReceived(ObjectEvent objectEvent, BaseConsole baseConsole) {
		
		if (!objectEvent.isUserInputString()) return;
		StringParser stringParser = new StringParser(objectEvent);
		
		if (!stringParser.getArg(0).equals("set")) return;
		if (stringParser.getLength() < 3) return;
//		support for VariableDuplicator
		if (stringParser.getArg(2).startsWith("{") && stringParser.getArg(2).endsWith("}")) return;
		
		String varName = stringParser.getArg(1);
		String varClass = stringParser.getArg(2);
		
		Object[] sArgs = JIntParseArg.parseArgs(3, stringParser.getArgs(), stringParser.getText());
		
		Object varValue = ReflectionHelper.callConstructor(ReflectionHelper.getClass(varClass), sArgs);
		JIntVariable<Object> variable = new JIntVariable<Object>(varName, varValue);
		
		JInterpreter.instance.getRuntime().addVariable(variable);
		
		baseConsole.println("Added variable " + variable.getName() + " of class " + variable.getValue().getClass().getName());
		
		
	}
	
}
