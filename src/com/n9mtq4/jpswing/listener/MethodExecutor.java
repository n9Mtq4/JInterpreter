package com.n9mtq4.jpswing.listener;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;
import com.n9mtq4.jpswing.JPSwing;
import com.n9mtq4.jpswing.runtime.JPSwingParseArg;
import com.n9mtq4.jpswing.runtime.JPSwingVariable;
import com.n9mtq4.jpswing.utils.ReflectionHelper;

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
		
		String varName = consoleActionEvent.getCommand().getArg(1);
		JPSwingVariable var = JPSwing.instance.getRuntime().getVariableByName(varName);
		
		String methodName = consoleActionEvent.getCommand().getArg(2);
		
		Object[] args = JPSwingParseArg.parseArgs(3, consoleActionEvent.getCommand().getArgs(), consoleActionEvent.getCommand().getText());
		
//		TODO: two way communication. Send response back to python
		Object response = ReflectionHelper.callObjectMethod(methodName, var.getValue(), var.getValueType(), args);
		
	}
	
}
