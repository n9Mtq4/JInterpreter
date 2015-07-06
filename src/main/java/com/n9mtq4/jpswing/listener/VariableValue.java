package com.n9mtq4.jpswing.listener;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;
import com.n9mtq4.jpswing.JInterpreter;
import com.n9mtq4.jpswing.runtime.JIntVariable;

/**
 * Created by will on 6/24/15 at 10:13 PM.
 */
public class VariableValue extends ConsoleListener {
	
	/**
	 * 0 = get
	 * 1 = variable name
	 * */
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
		
		if (!consoleActionEvent.getCommand().getArg(0).equalsIgnoreCase("get")) return;
		if (consoleActionEvent.getCommand().getLength() != 2) return;
		
		String varName = consoleActionEvent.getCommand().getArg(1);
		JIntVariable var = JInterpreter.instance.getRuntime().getVariableByName(varName);
		
		if (var == null) {
			baseConsole.println(varName + " doesn't exist!");
			return;
		}
		
		baseConsole.println(varName + " is an instance of class " + var.getValueType().getName() + " with a value of: " + var.getValue().toString());
		
	}
	
}
