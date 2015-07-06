package com.n9mtq4.jinterpreter.listener;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.events.AdditionActionEvent;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;
import com.n9mtq4.console.lib.events.SentObjectEvent;
import com.n9mtq4.jinterpreter.JInterpreter;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by will on 6/23/15 at 8:51 PM.
 */
public class JPSwingListener extends ConsoleListener {
	
	@Override
	public void onAddition(AdditionActionEvent e) {
		
		JInterpreter.main(new String[]{});
		e.getBaseConsole().println("Successfully created a JInterpreter Runtime");
		e.getBaseConsole().print("> ");
		
	}
	
	/**
	 * Handles displaying results of invoking to the user
	 * */
	@Override
	public void objectReceived(SentObjectEvent e, BaseConsole baseConsole) {
		
		Object o = e.getObject();
		
		String value;
		if (o instanceof Object[]) {
			value = Arrays.toString((Object[]) o);
		}else if (o instanceof Collection) {
			value = Arrays.toString(((Collection) o).toArray());
		}else {
			value = o.toString();
		}
		
		baseConsole.println(e.getMessage() + " has value: " + value);
		
	}
	
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {}
	
}