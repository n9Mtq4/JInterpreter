package com.n9mtq4.jpswing.listener;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;
import com.n9mtq4.reflection.ReflectionHelper;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by will on 7/6/15 at 2:00 AM.
 */
public class ListMethodsClass extends ConsoleListener {
	
	/**
	 * 0 = getmethods
	 * 1 = class name
	 * */
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
		
		if (!consoleActionEvent.getCommand().getArg(0).equalsIgnoreCase("getmethods")) return;
		if (consoleActionEvent.getCommand().getLength() != 2) return;
		
		try {
			
			Class clazz = ReflectionHelper.getClass(consoleActionEvent.getCommand().getArg(1));
			Method[] methods = ReflectionHelper.getAllDeclaredMethods(clazz);
			
			baseConsole.println(Arrays.toString(methods));
			
		}catch (Exception e) {
			
		}
		
	}
	
}
