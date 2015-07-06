package com.n9mtq4.jinterpreter.listener;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;
import com.n9mtq4.jinterpreter.JInterpreter;
import com.n9mtq4.jinterpreter.runtime.JIntParseArg;
import com.n9mtq4.jinterpreter.runtime.JIntVariable;
import com.n9mtq4.reflection.ReflectionHelper;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by will on 6/15/15 at 9:42 PM.
 */
public class MethodExecutor extends ConsoleListener {
	
	/**
	 * 0 = invoke
	 * 1 = variable name
	 * 2 = method name to run
	 * 3... = arguments
	 * */
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
		
		if (!consoleActionEvent.getCommand().getArg(0).equals("invoke")) return;
		if (consoleActionEvent.getCommand().getLength() < 3) return;
		
		String varName = consoleActionEvent.getCommand().getArg(1);
		JIntVariable var = JInterpreter.instance.getRuntime().getVariableByName(varName);
		
		String methodName = consoleActionEvent.getCommand().getArg(2);
		
		Object[] args = JIntParseArg.parseArgs(3, consoleActionEvent.getCommand().getArgs(), consoleActionEvent.getCommand().getText());
		
		try {
			
			Method m = ReflectionHelper.getAllDeclaredMethod(methodName, ReflectionHelper.getClassParams(args), var.getValueType());
			Object result = ReflectionHelper.callObjectMethod(m, var.getValue(), args);
			
			baseConsole.println("invoked method " + var.getName() + "." + m.getName() + " with params " + Arrays.toString(m.getParameterTypes()));
			
			if (result != null) {
				baseConsole.pushObject(result, "result method " + var.getName() + methodName);
				JInterpreter.instance.getRuntime().updateResult(result);
			}
			
		}catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		
	}
	
}
