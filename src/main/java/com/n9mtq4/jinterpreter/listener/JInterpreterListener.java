package com.n9mtq4.jinterpreter.listener;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.events.AdditionActionEvent;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;
import com.n9mtq4.console.lib.events.SentObjectEvent;
import com.n9mtq4.jinterpreter.JInterpreter;
import com.n9mtq4.jinterpreter.runtime.JIntRuntime;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by will on 6/23/15 at 8:51 PM.
 */
public class JInterpreterListener extends ConsoleListener {
	
	@Override
	public void onAddition(AdditionActionEvent e) {
		
		JInterpreter.main(new String[]{});
		e.getBaseConsole().println("Successfully created a JInterpreter Runtime");
		e.getBaseConsole().print("> ");
		
		JInterpreter.instance.setServer(e.getBaseConsole());
		
	}
	
	/**
	 * Handles displaying results of invoking / getting to the user
	 * When an object is pushed to the BaseConsole this will display it
	 * */
	@Override
	public void objectReceived(SentObjectEvent e, BaseConsole baseConsole) {
		
//		object.toString, but also handles arrays
		String value = JIntRuntime.getString(e.getObject());
		
		baseConsole.println(e.getMessage() + " has value: " + value);
		
	}
	
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {}
	
}
