package com.n9mtq4.jinterpreter.listener;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.events.AdditionActionEvent;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;
import com.n9mtq4.console.lib.events.SentObjectEvent;
import com.n9mtq4.jinterpreter.JInterpreter;
import com.n9mtq4.jinterpreter.runtime.JIntRuntime;
import com.n9mtq4.jinterpreter.utils.SocketHandler;

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
		
		if (JInterpreter.RUN_SERVER) e.getBaseConsole().addGui(new SocketHandler());
		
	}
	
	/**
	 * Handles displaying results of invoking / getting to the user
	 * When an object is pushed to the BaseConsole this will display it
	 * */
	@Override
	public void objectReceived(SentObjectEvent e, BaseConsole baseConsole) {
		
//		object.toString, but also handles arrays
		
		if (e.getMessage().equals("output")) {
			baseConsole.println(JIntRuntime.getString(e.getObject()));
		}
		
		String value = JIntRuntime.getString(e.getObject());
		
		baseConsole.println(e.getMessage() + " has value: " + value);
		
	}
	
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {}
	
}
