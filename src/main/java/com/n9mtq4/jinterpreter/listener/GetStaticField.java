package com.n9mtq4.jinterpreter.listener;

import com.n9mtq4.jinterpreter.JInterpreter;
import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.events.ObjectEvent;
import com.n9mtq4.logwindow.listener.ObjectListener;
import com.n9mtq4.logwindow.utils.StringParser;
import com.n9mtq4.reflection.ReflectionHelper;

import java.lang.reflect.Field;

/**
 * Created by will on 6/25/15 at 9:27 PM.
 */
public class GetStaticField implements ObjectListener {
	
	/**
	 * 0 = getstaticfield
	 * 1 = class name
	 * 2 = field name
	 * */
	@Override
	public void objectReceived(ObjectEvent objectEvent, BaseConsole baseConsole) {
		
		if (!objectEvent.isUserInputString()) return;
		StringParser stringParser = new StringParser(objectEvent);
		
		if (!stringParser.getArg(0).equalsIgnoreCase("getstaticfield")) return;
		if (stringParser.getLength() != 3) return;
		
		String className = stringParser.getArg(1);
		String fieldName = stringParser.getArg(2);
		
		try {
			
			Field field = ReflectionHelper.getAllDeclaredField(fieldName, ReflectionHelper.getClass(className));
			
			Object result = ReflectionHelper.getStaticObject(field);
			
			if (result != null) {
				baseConsole.push(result, "field " + className + " " + fieldName);
				JInterpreter.instance.getRuntime().updateResult(result);
			}
			
		}catch (NoSuchFieldException e) {
			baseConsole.println("The field " + className + "." + fieldName + " does not exist!");
		}
		
	}
	
}
