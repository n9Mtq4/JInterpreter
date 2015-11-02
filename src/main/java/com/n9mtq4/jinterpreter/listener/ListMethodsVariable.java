package com.n9mtq4.jinterpreter.listener;

import com.n9mtq4.jinterpreter.JInterpreter;
import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.events.ObjectEvent;
import com.n9mtq4.logwindow.listener.ObjectListener;
import com.n9mtq4.logwindow.utils.StringParser;
import com.n9mtq4.reflection.ReflectionHelper;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by will on 7/6/15 at 2:01 AM.
 */
public class ListMethodsVariable implements ObjectListener {
	
	/**
	 * 0 = listmethods
	 * 1 = variable name
	 * */
//	TODO: BAD - figure out a way to tell if class or variable, so it can print a failed message
	@Override
	public void objectReceived(ObjectEvent objectEvent, BaseConsole baseConsole) {
		
		if (!objectEvent.isUserInputString()) return;
		StringParser stringParser = new StringParser(objectEvent);
		
		if (!stringParser.getArg(0).equalsIgnoreCase("listmethods")) return;
		if (stringParser.getLength() != 2) return;
		
		try {
			
			Class clazz = JInterpreter.instance.getRuntime().getVariableByName(stringParser.getArg(1)).getValueType();
			Method[] methods = ReflectionHelper.getAllDeclaredMethods(clazz);
			
			baseConsole.println(Arrays.toString(methods));
			
		}catch (Exception e) {
			
		}
		
	}
	
}
