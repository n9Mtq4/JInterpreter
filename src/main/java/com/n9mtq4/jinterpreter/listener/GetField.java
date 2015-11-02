package com.n9mtq4.jinterpreter.listener;

import com.n9mtq4.jinterpreter.JInterpreter;
import com.n9mtq4.jinterpreter.runtime.JIntVariable;
import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.events.ObjectEvent;
import com.n9mtq4.logwindow.listener.ObjectListener;
import com.n9mtq4.logwindow.utils.StringParser;
import com.n9mtq4.reflection.ReflectionHelper;

import java.lang.reflect.Field;

/**
 * Created by will on 6/25/15 at 10:25 PM.
 */
public class GetField implements ObjectListener {
	
	/**
	 * 0 = getfield
	 * 1 = variable TODO: with {}?
	 * 2 = field
	 * */
	@Override
	public void objectReceived(ObjectEvent objectEvent, BaseConsole baseConsole) {
		
		if (!objectEvent.isUserInputString()) return;
		StringParser stringParser = new StringParser(objectEvent);
		
		if (!stringParser.getArg(0).equalsIgnoreCase("getfield")) return;
		if (stringParser.getLength() != 3) return;
		
		String varName = stringParser.getArg(1);
		String fieldName = stringParser.getArg(2);
		
		JIntVariable var = JInterpreter.instance.getRuntime().getVariableByName(varName);
		if (var == null) {
			baseConsole.println("Variable " + varName + " does not exist!");
			return;
		}
		
		try {
			
			Field field = ReflectionHelper.getAllDeclaredField(fieldName, var.getValueType());
			Object result = ReflectionHelper.getObject(field, var.getValue());
			
			if (result != null) {
				baseConsole.push(result, "field " + varName + " " + fieldName);
				JInterpreter.instance.getRuntime().updateResult(result);
			}
			
		}catch (NoSuchFieldException e) {
			baseConsole.println("The field " + varName + "." + fieldName + " does not exist!");
		}
		
		
	}
	
}
