package com.n9mtq4.jinterpreter.listener;

import com.n9mtq4.jinterpreter.JInterpreter;
import com.n9mtq4.jinterpreter.runtime.JIntParseArg;
import com.n9mtq4.jinterpreter.runtime.JIntVariable;
import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.events.ObjectEvent;
import com.n9mtq4.logwindow.listener.ObjectListener;
import com.n9mtq4.logwindow.utils.StringParser;
import com.n9mtq4.reflection.ReflectionHelper;

import java.lang.reflect.Field;

/**
 * Created by will on 6/25/15 at 10:29 PM.
 */
public class SetField implements ObjectListener {
	
	/**
	 * 0 = setfield
	 * 1 = variable name TODO: with {}?
	 * 2 = field name
	 * 3 = args... (check args.length == 1)
	 * */
	@Override
	public void objectReceived(ObjectEvent objectEvent, BaseConsole baseConsole) {
		
		if (!objectEvent.isUserInputString()) return;
		StringParser stringParser = new StringParser(objectEvent);
		
		if (!stringParser.getArg(0).equalsIgnoreCase("setfield")) return;
		if (stringParser.getLength() < 4) return;
		
		String varName = stringParser.getArg(1);
		String fieldName = stringParser.getArg(2);
		Object[] args = JIntParseArg.parseArgs(3, stringParser.getArgs(), stringParser.getText());
		
		if (args.length != 1) {
			baseConsole.println("You can't set a variable to multiple values!");
			return;
		}
		
		JIntVariable var = JInterpreter.instance.getRuntime().getVariableByName(varName);
		if (var == null) {
			baseConsole.println("Variable " + varName + " does not exist!");
			return;
		}
		
		try {
			
			Field field = ReflectionHelper.getAllDeclaredField(fieldName, var.getValueType());
			
			ReflectionHelper.setObject(args[0], field, var.getValue());
			baseConsole.println("Set " + var.getName() + "." + fieldName + " to " + args[0].toString());
			
		}catch (NoSuchFieldException e) {
			baseConsole.println("The field " + var.getName() + "." + fieldName + " does not exist!");
		}
		
	}
	
}
