package com.n9mtq4.jpswing.listener;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;
import com.n9mtq4.console.lib.utils.ReflectionHelper;
import com.n9mtq4.jpswing.JPSwing;
import com.n9mtq4.jpswing.runtime.JPSwingParseArg;
import com.n9mtq4.jpswing.runtime.JPSwingVariable;

import java.lang.reflect.Field;

/**
 * Created by will on 6/25/15 at 10:29 PM.
 */
public class SetField extends ConsoleListener {
	
	/**
	 * 0 = setfield
	 * 1 = variable name TODO: with {}?
	 * 2 = field name
	 * 3 = args... (check args.length == 1)
	 * */
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
		
		if (!consoleActionEvent.getCommand().getArg(0).equalsIgnoreCase("setfield")) return;
		if (consoleActionEvent.getCommand().getLength() < 4) return;
		
		String varName = consoleActionEvent.getCommand().getArg(1);
		String fieldName = consoleActionEvent.getCommand().getArg(2);
		Object[] args = JPSwingParseArg.parseArgs(3, consoleActionEvent.getCommand().getArgs(), consoleActionEvent.getCommand().getText());
		
		if (args.length != 1) {
			baseConsole.println("You can't set a variable to multiple values!");
			return;
		}
		
		JPSwingVariable var = JPSwing.instance.getRuntime().getVariableByName(varName);
		if (var == null) {
			baseConsole.println("Variable " + varName + " does not exist!");
			return;
		}
		
		try {
			
			Field field = ReflectionHelper.getAllDeclaredField(fieldName, var.getValueType());
			
			ReflectionHelper.setObject(args[0], field, var.getValue());
			baseConsole.println("Set " + var.getName() + "." + fieldName + " to " + args[0].toString());
			
		}catch (NoSuchFieldException e) {
			baseConsole.println("The field " + var.getName() + "." + fieldName + " does not exist!");
		}
		
	}
	
}
