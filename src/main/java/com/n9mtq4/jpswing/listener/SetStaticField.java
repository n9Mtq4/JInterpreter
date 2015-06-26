package com.n9mtq4.jpswing.listener;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;
import com.n9mtq4.console.lib.utils.ReflectionHelper;
import com.n9mtq4.jpswing.runtime.JPSwingParseArg;

import java.lang.reflect.Field;

/**
 * Created by will on 6/25/15 at 9:36 PM.
 */
public class SetStaticField extends ConsoleListener {
	
	/**
	 * 0 = setstaticfield
	 * 1 = class name
	 * 2 = field name
	 * 3 = args... (check args.length == 1)
	 * */
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
		
		if (!consoleActionEvent.getCommand().getArg(0).equalsIgnoreCase("setstaticfield")) return;
		if (consoleActionEvent.getCommand().getLength() < 4) return;
		
		String className = consoleActionEvent.getCommand().getArg(1);
		String fieldName = consoleActionEvent.getCommand().getArg(2);
		Object[] args = JPSwingParseArg.parseArgs(3, consoleActionEvent.getCommand().getArgs(), consoleActionEvent.getCommand().getText());
		
		if (args.length != 1) {
			baseConsole.println("You can't set a variable to multiple values!");
			return;
		}
		
		try {
			
			Field field = ReflectionHelper.getAllDeclaredField(fieldName, ReflectionHelper.getClass(className));
			
			ReflectionHelper.setStaticObject(args[0], field);
			baseConsole.println("Set " + className + "." + fieldName + " to " + args[0].toString());
			
		}catch (NoSuchFieldException e) {
			baseConsole.println("The field " + className + "." + fieldName + " does not exist!");
		}
		
	}
	
}