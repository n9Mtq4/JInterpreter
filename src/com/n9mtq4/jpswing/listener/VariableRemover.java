package com.n9mtq4.jpswing.listener;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;
import com.n9mtq4.jpswing.JPSwing;
import com.n9mtq4.jpswing.runtime.JPSwingVariable;

/**
 * Created by will on 6/24/15 at 10:45 PM.
 */
public class VariableRemover extends ConsoleListener {
	
	/**
	 * 0 = unset
	 * 1 = variable name
	 * */
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
		
		if (!consoleActionEvent.getCommand().getArg(0).equalsIgnoreCase("unset")) return;
		if (consoleActionEvent.getCommand().getLength() != 2) return;
		
		String varName = consoleActionEvent.getCommand().getArg(1);
		JPSwingVariable var = JPSwing.instance.getRuntime().getVariableByName(varName);
		
		if (var == null) {
			baseConsole.println("No variable with name: " + varName);
			return;
		}
		
		JPSwing.instance.getRuntime().removeVariable(var);
		baseConsole.println("Unset variable " + var.getName());
		
	}
	
}
