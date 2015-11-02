package com.n9mtq4.jinterpreter.listener;

import com.n9mtq4.jinterpreter.runtime.JIntParseArg;
import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.events.ObjectEvent;
import com.n9mtq4.logwindow.listener.ObjectListener;
import com.n9mtq4.logwindow.utils.StringParser;
import com.n9mtq4.reflection.ReflectionHelper;

import java.lang.reflect.Field;

/**
 * Created by will on 6/25/15 at 9:36 PM.
 */
public class SetStaticField implements ObjectListener {
	
	/**
	 * 0 = setstaticfield
	 * 1 = class name
	 * 2 = field name
	 * 3 = args... (check args.length == 1)
	 * */
	@Override
	public void objectReceived(ObjectEvent objectEvent, BaseConsole baseConsole) {
		
		if (!objectEvent.isUserInputString()) return;
		StringParser stringParser = new StringParser(objectEvent);
		
		if (!stringParser.getArg(0).equalsIgnoreCase("setstaticfield")) return;
		if (stringParser.getLength() < 4) return;
		
		String className = stringParser.getArg(1);
		String fieldName = stringParser.getArg(2);
		Object[] args = JIntParseArg.parseArgs(3, stringParser.getArgs(), stringParser.getText());
		
		if (args.length != 1) {
			baseConsole.println("You can't set a variable to multiple values!");
			return;
		}
		
		try {
			
			Field field = ReflectionHelper.getAllDeclaredField(fieldName, ReflectionHelper.getClass(className));
			
			ReflectionHelper.setStaticObject(args[0], field);
			baseConsole.println("Set " + className + "." + fieldName + " to " + args[0].toString());
			
		}catch (NoSuchFieldException e) {
			baseConsole.println("The field " + className + "." + fieldName + " does not exist!");
		}
		
	}
	
}
