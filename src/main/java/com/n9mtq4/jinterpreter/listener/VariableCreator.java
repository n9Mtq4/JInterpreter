package com.n9mtq4.jinterpreter.listener;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;
import com.n9mtq4.jinterpreter.JInterpreter;
import com.n9mtq4.jinterpreter.runtime.JIntParseArg;
import com.n9mtq4.jinterpreter.runtime.JIntVariable;
import com.n9mtq4.reflection.ReflectionHelper;

/**
 * Created by will on 6/15/15 at 8:54 PM.
 */
public class VariableCreator extends ConsoleListener {
	
	/**
	 * 0 = set
	 * 1 = variable name<br>
	 * 2 = class name<br>
	 * 3... = arguments<br>
	 * */
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
		
		if (!consoleActionEvent.getCommand().getArg(0).equals("set")) return;
		if (consoleActionEvent.getCommand().getLength() < 3) return;
//		support for VariableDuplicator
		if (consoleActionEvent.getCommand().getArg(2).startsWith("{") && consoleActionEvent.getCommand().getArg(2).endsWith("}")) return;
		
		String varName = consoleActionEvent.getCommand().getArg(1);
		String varClass = consoleActionEvent.getCommand().getArg(2);
		
		Object[] sArgs = JIntParseArg.parseArgs(3, consoleActionEvent.getCommand().getArgs(), consoleActionEvent.getCommand().getText());
		
		Object varValue = ReflectionHelper.callConstructor(ReflectionHelper.getClass(varClass), sArgs);
		JIntVariable<Object> variable = new JIntVariable<Object>(varName, varValue);
		
		JInterpreter.instance.getRuntime().addVariable(variable);
		
		baseConsole.println("Added variable " + variable.getName() + " of class " + variable.getValue().getClass().getName());
		
	}
	
}