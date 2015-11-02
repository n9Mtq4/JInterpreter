package com.n9mtq4.jinterpreter.listener;

import com.n9mtq4.jinterpreter.JInterpreter;
import com.n9mtq4.jinterpreter.runtime.JIntParseArg;
import com.n9mtq4.jinterpreter.runtime.JIntVariable;
import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.events.ObjectEvent;
import com.n9mtq4.logwindow.listener.ObjectListener;
import com.n9mtq4.logwindow.utils.StringParser;
import com.n9mtq4.reflection.ReflectionHelper;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by will on 6/15/15 at 9:42 PM.
 */
public class MethodExecutor implements ObjectListener {
	
	/**
	 * 0 = invoke
	 * 1 = variable name
	 * 2 = method name to run
	 * 3... = arguments
	 * */
	@Override
	public void objectReceived(ObjectEvent objectEvent, BaseConsole baseConsole) {
		
		if (!objectEvent.isUserInputString()) return;
		StringParser stringParser = new StringParser(objectEvent);
		
		if (!stringParser.getArg(0).equals("invoke")) return;
		if (stringParser.getLength() < 3) return;
		
		String varName = stringParser.getArg(1);
		JIntVariable var = JInterpreter.instance.getRuntime().getVariableByName(varName);
		
		String methodName = stringParser.getArg(2);
		
		Object[] args = JIntParseArg.parseArgs(3, stringParser.getArgs(), stringParser.getText());
		
		try {
			
			Method m = ReflectionHelper.getAllDeclaredMethod(methodName, ReflectionHelper.getClassParams(args), var.getValueType());
			Object result = ReflectionHelper.callObjectMethod(m, var.getValue(), args);
			
			baseConsole.println("invoked method " + var.getName() + "." + m.getName() + " with params " + Arrays.toString(m.getParameterTypes()));
			
			if (result != null) {
				baseConsole.push(result, "result method " + var.getName() + "."+ methodName);
				JInterpreter.instance.getRuntime().updateResult(result);
			}
			
		}catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		
	}
	
}
