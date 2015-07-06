package com.n9mtq4.jinterpreter.listener;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;
import com.n9mtq4.jinterpreter.JInterpreter;
import com.n9mtq4.reflection.ReflectionHelper;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by will on 7/6/15 at 2:01 AM.
 */
public class ListMethodsVariable extends ConsoleListener {
	
	/**
	 * 0 = listmethods
	 * 1 = variable name
	 * */
//	TODO: BAD - figure out a way to tell if class or variable, so it can print a failed message
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
		
		if (!consoleActionEvent.getCommand().getArg(0).equalsIgnoreCase("listmethods")) return;
		if (consoleActionEvent.getCommand().getLength() != 2) return;
		
		try {
			
			Class clazz = JInterpreter.instance.getRuntime().getVariableByName(consoleActionEvent.getCommand().getArg(1)).getValueType();
			Method[] methods = ReflectionHelper.getAllDeclaredMethods(clazz);
			
			baseConsole.println(Arrays.toString(methods));
			
		}catch (Exception e) {
			
		}
		
	}
	
}
