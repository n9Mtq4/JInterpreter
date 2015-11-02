package com.n9mtq4.jinterpreter.listener;

import com.n9mtq4.jinterpreter.JInterpreter;
import com.n9mtq4.jinterpreter.runtime.JIntParseArg;
import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.events.ObjectEvent;
import com.n9mtq4.logwindow.listener.ObjectListener;
import com.n9mtq4.logwindow.utils.StringParser;
import com.n9mtq4.reflection.ReflectionHelper;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by will on 6/24/15 at 10:13 PM.
 */
public class StaticMethodExecutor implements ObjectListener {
	
	/**
	 * 0 = invokestatic
	 * 1 = class name
	 * 2 = method
	 * 3... = params
	 * */
	@Override
	public void objectReceived(ObjectEvent objectEvent, BaseConsole baseConsole) {
		
		if (!objectEvent.isUserInputString()) return;
		StringParser stringParser = new StringParser(objectEvent);
		
		if (!stringParser.getArg(0).equalsIgnoreCase("invokestatic")) return;
		if (stringParser.getLength() < 3) return;
		
		String className = stringParser.getArg(1);
		String methodName = stringParser.getArg(2);
		Object[] args = JIntParseArg.parseArgs(3, stringParser.getArgs(), stringParser.getText());
		
		Class clazz = ReflectionHelper.getClass(className);
		
		try {
			
			Method method = ReflectionHelper.getAllDeclaredMethod(methodName, ReflectionHelper.getClassParams(args), clazz);
			Object result = ReflectionHelper.callStaticObjectMethod(method, args);
			
			baseConsole.println("invoked method " + className + "." + method.getName() + " with params " + Arrays.toString(method.getParameterTypes()));
			
			if (result != null) {
				baseConsole.push(result, "result method " + clazz.getName() + " " + methodName);
				JInterpreter.instance.getRuntime().updateResult(result);
			}
			
		}catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		
	}
	
}
