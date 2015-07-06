package com.n9mtq4.jinterpreter.listener;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;
import com.n9mtq4.jinterpreter.JInterpreter;
import com.n9mtq4.jinterpreter.runtime.JIntParseArg;
import com.n9mtq4.reflection.ReflectionHelper;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by will on 6/24/15 at 10:13 PM.
 */
public class StaticMethodExecutor extends ConsoleListener {
	
	/**
	 * 0 = invokestatic
	 * 1 = class name
	 * 2 = method
	 * 3... = params
	 * */
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
		
		if (!consoleActionEvent.getCommand().getArg(0).equalsIgnoreCase("invokestatic")) return;
		if (consoleActionEvent.getCommand().getLength() < 3) return;
		
		String className = consoleActionEvent.getCommand().getArg(1);
		String methodName = consoleActionEvent.getCommand().getArg(2);
		Object[] args = JIntParseArg.parseArgs(3, consoleActionEvent.getCommand().getArgs(), consoleActionEvent.getCommand().getText());
		
		Class clazz = ReflectionHelper.getClass(className);
		
		try {
			
			Method method = ReflectionHelper.getAllDeclaredMethod(methodName, ReflectionHelper.getClassParams(args), clazz);
			Object result = ReflectionHelper.callStaticObjectMethod(method, args);
			
			baseConsole.println("invoked method " + className + "." + method.getName() + " with params " + Arrays.toString(method.getParameterTypes()));
			
			if (result != null) {
				baseConsole.pushObject(result, "result method " + clazz.getName() + " " + methodName);
				JInterpreter.instance.getRuntime().updateResult(result);
			}
			
		}catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		
	}
	
}
