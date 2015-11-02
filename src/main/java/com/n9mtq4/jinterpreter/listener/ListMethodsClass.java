package com.n9mtq4.jinterpreter.listener;

import com.n9mtq4.jinterpreter.JInterpreter;
import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.events.ObjectEvent;
import com.n9mtq4.logwindow.listener.ObjectListener;
import com.n9mtq4.logwindow.utils.StringParser;
import com.n9mtq4.reflection.ReflectionHelper;

import java.lang.reflect.Method;

/**
 * Created by will on 7/6/15 at 2:00 AM.
 */
public class ListMethodsClass implements ObjectListener {
	
	/**
	 * 0 = listmethods
	 * 1 = class name
	 * */
//	TODO: BAD - figure out a way to tell if class or variable, so it can print a failed message
	@Override
	public void objectReceived(ObjectEvent objectEvent, BaseConsole baseConsole) {
		
		if (!objectEvent.isUserInputString()) return;
		StringParser stringParser = new StringParser(objectEvent);
		
		if (!stringParser.getArg(0).equalsIgnoreCase("listmethods")) return;
		if (stringParser.getLength() != 2) return;
		
		try {
			
			Class clazz = ReflectionHelper.getClass(stringParser.getArg(1));
			if (clazz == null) return;
			
			Method[] methods = ReflectionHelper.getAllDeclaredMethods(clazz);
			
			JInterpreter.instance.getRuntime().output(methods, baseConsole);
			
		}catch (Exception e) {
			
		}
		
	}
	
}
