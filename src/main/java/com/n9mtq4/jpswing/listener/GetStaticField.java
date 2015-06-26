package com.n9mtq4.jpswing.listener;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;
import com.n9mtq4.console.lib.utils.ReflectionHelper;
import com.n9mtq4.jpswing.JPSwing;

import java.lang.reflect.Field;

/**
 * Created by will on 6/25/15 at 9:27 PM.
 */
public class GetStaticField extends ConsoleListener {
	
	/**
	 * 0 = getstaticfield
	 * 1 = class name
	 * 2 = field name
	 * */
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
		
		if (!consoleActionEvent.getCommand().getArg(0).equalsIgnoreCase("getstaticfield")) return;
		if (consoleActionEvent.getCommand().getLength() != 3) return;
		
		String className = consoleActionEvent.getCommand().getArg(1);
		String fieldName = consoleActionEvent.getCommand().getArg(2);
		
		try {
			
			Field field = ReflectionHelper.getAllDeclaredField(fieldName, ReflectionHelper.getClass(className));
			
			Object result = ReflectionHelper.getStaticObject(field);
			
			if (result != null) {
				baseConsole.pushObject(result, "field " + className + " " + fieldName);
				JPSwing.instance.getRuntime().updateResult(result);
			}
			
		}catch (NoSuchFieldException e) {
			baseConsole.println("The field " + className + "." + fieldName + " does not exist!");
		}
		
	}
	
}
