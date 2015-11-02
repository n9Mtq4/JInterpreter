package com.n9mtq4.jinterpreter.listener;

import com.n9mtq4.jinterpreter.JInterpreter;
import com.n9mtq4.jinterpreter.runtime.JIntRuntime;
import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.events.AdditionEvent;
import com.n9mtq4.logwindow.events.ObjectEvent;
import com.n9mtq4.logwindow.listener.AdditionListener;
import com.n9mtq4.logwindow.listener.ObjectListener;

/**
 * Created by will on 6/23/15 at 8:51 PM.
 */
public class JInterpreterListener implements AdditionListener, ObjectListener {
	
	@Override
	public void onAddition(AdditionEvent e) {
		
		JInterpreter.main(new String[]{});
		e.getBaseConsole().println("Successfully created a JInterpreter Runtime");
		e.getBaseConsole().print("> ");
		
		JInterpreter.instance.setServer(e.getBaseConsole());
		
//		if (JInterpreter.RUN_SERVER) e.getBaseConsole().addConsoleUi(new SocketHandler());
		
	}
	
	/**
	 * Handles displaying results of invoking / getting to the user
	 * When an object is pushed to the BaseConsole this will display it
	 * */
	@Override
	public void objectReceived(ObjectEvent objectEvent, BaseConsole baseConsole) {
		
//		object.toString, but also handles arrays
		
		if (objectEvent.getMessage().equals("output")) {
			baseConsole.println(JIntRuntime.getString(objectEvent.getObject()));
		}
		
		String value = JIntRuntime.getString(objectEvent.getObject());
		
		baseConsole.println(objectEvent.getMessage() + " has value: " + value);
		
	}
	
}
