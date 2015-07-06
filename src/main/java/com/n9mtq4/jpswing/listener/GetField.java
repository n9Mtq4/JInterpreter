package com.n9mtq4.jpswing.listener;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;
import com.n9mtq4.jpswing.JInterpreter;
import com.n9mtq4.jpswing.runtime.JIntVariable;
import com.n9mtq4.reflection.ReflectionHelper;

import java.lang.reflect.Field;

/**
 * Created by will on 6/25/15 at 10:25 PM.
 */
public class GetField extends ConsoleListener {
	
	/**
	 * 0 = getfield
	 * 1 = variable TODO: with {}?
	 * 2 = field
	 * */
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
		
		if (!consoleActionEvent.getCommand().getArg(0).equalsIgnoreCase("getfield")) return;
		if (consoleActionEvent.getCommand().getLength() != 3) return;
		
		String varName = consoleActionEvent.getCommand().getArg(1);
		String fieldName = consoleActionEvent.getCommand().getArg(2);
		
		JIntVariable var = JInterpreter.instance.getRuntime().getVariableByName(varName);
		if (var == null) {
			baseConsole.println("Variable " + varName + " does not exist!");
			return;
		}
		
		try {
			
			Field field = ReflectionHelper.getAllDeclaredField(fieldName, var.getValueType());
			Object result = ReflectionHelper.getObject(field, var.getValue());
			
			if (result != null) {
				baseConsole.pushObject(result, "field " + varName + " " + fieldName);
				JInterpreter.instance.getRuntime().updateResult(result);
			}
			
		}catch (NoSuchFieldException e) {
			baseConsole.println("The field " + varName + "." + fieldName + " does not exist!");
		}
		
	}
	
}
