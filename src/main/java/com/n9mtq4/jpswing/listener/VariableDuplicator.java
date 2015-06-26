package com.n9mtq4.jpswing.listener;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;
import com.n9mtq4.jpswing.JPSwing;
import com.n9mtq4.jpswing.runtime.JPSwingVariable;

/**
 * Created by will on 6/25/15 at 8:48 PM.
 */
public class VariableDuplicator extends ConsoleListener {
	
	/**
	 * 0 = set
	 * 1 = new variable name
	 * 2 = old variable name
	 * */
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
		
		if (!consoleActionEvent.getCommand().getArg(0).equalsIgnoreCase("set")) return;
		if (consoleActionEvent.getCommand().getLength() != 3) return;
		if (!consoleActionEvent.getCommand().getArg(2).startsWith("{") || !consoleActionEvent.getCommand().getArg(2).endsWith("}")) return;
		
		String newVarName = consoleActionEvent.getCommand().getArg(1);
		String oldVarName = consoleActionEvent.getCommand().getArg(2).substring(1, consoleActionEvent.getCommand().getArg(2).length() - 1);
		
		JPSwingVariable oldVar = JPSwing.instance.getRuntime().getVariableByName(oldVarName);
		if (oldVar == null) {
			baseConsole.println("No variable with name: " + oldVarName);
			return;
		}
		
		JPSwingVariable<Object> newVar = new JPSwingVariable<Object>(newVarName, oldVar.getValue());
		JPSwing.instance.getRuntime().addVariable(newVar);
		baseConsole.println("Added variable: " + newVarName + " which is a duplicate of " + oldVarName);
		
	}
	
}
