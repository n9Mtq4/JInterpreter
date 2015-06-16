package com.n9mtq4.jpswing;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;

import javax.swing.*;

/**
 * Created by will on 6/15/15 at 8:54 PM.
 */
public class VariableCreateListener extends ConsoleListener {
	
	/**
	 * 
	 * 1 = variable name<br>
	 * 2 = class name<br>
	 * 3... = arguments<br>
	 * */
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
		
		if (!consoleActionEvent.getCommand().getArg(0).equals("create")) return;
		
		String varName = consoleActionEvent.getCommand().getArg(1);
		String varClass = consoleActionEvent.getCommand().getArg(2);
		
		JFrame frame = ReflectionHelper.callConstructor(ReflectionHelper.getClassByFullName(varClass));
		JPSwingVariable<JFrame> var = new JPSwingVariable(varName, frame);
		
		JPSwing.instance.getRuntime().addVariable(var);
		
		baseConsole.println("Added variable " + var.getName() + " of class " + var.getValue().getClass().getName());
		
	}
	
}
