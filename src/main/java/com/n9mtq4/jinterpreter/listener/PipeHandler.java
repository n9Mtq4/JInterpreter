package com.n9mtq4.jinterpreter.listener;

import com.n9mtq4.jinterpreter.runtime.JIntRuntime;
import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.annotation.Async;
import com.n9mtq4.logwindow.events.AdditionEvent;
import com.n9mtq4.logwindow.events.ObjectEvent;
import com.n9mtq4.logwindow.listener.AdditionListener;
import com.n9mtq4.logwindow.listener.ObjectListener;
import com.n9mtq4.logwindow.utils.StringParser;

/**
 * Created by will on 7/7/15 at 10:45 PM.
 */
public class PipeHandler implements AdditionListener, ObjectListener {
	
	private boolean isListening;
	private String nextCommand;
	
	@Override
	public void onAddition(AdditionEvent additionEvent) {
		this.isListening = false;
		this.nextCommand = "";
	}
	
	@Async
	@Override
	public void objectReceived(ObjectEvent objectEvent, BaseConsole baseConsole) {
		
		if (!objectEvent.isUserInputString()) object(objectEvent, baseConsole);
		else string(objectEvent, baseConsole);
		
	}
	
	private void object(ObjectEvent objectEvent, BaseConsole baseConsole) {
		
		if (!isListening) return;
		if (!objectEvent.getMessage().equals("output")) return;
		
//		we have received the output from the first command.
//		now run the second command while sending it this output
		
//		TODO: will be changed to getInitiatingBaseConsole
//		TODO: [HIGH] change to sending other listener the object rather than a string!
		objectEvent.getInitiatingBaseConsole().push(nextCommand + JIntRuntime.getString(objectEvent.getObject()));
		
//		stop listening for other objects
		this.isListening = false;
		this.nextCommand = "";
		
	}
	
	private void string(ObjectEvent objectEvent, BaseConsole baseConsole) {
		
		StringParser stringParser = new StringParser(objectEvent);
		
		if (!stringParser.contains(" | ")) return;
		if (stringParser.getText().indexOf(" | ") != stringParser.getText().lastIndexOf(" | ")) return;
		
		String[] commands = stringParser.getText().split(" | ");
		
		isListening = true;
		nextCommand = commands[1];
		
		objectEvent.getInitiatingBaseConsole().push(commands[0]);
		
//		stop it from going onto other listeners
//		THIS IS VERY IMPORTANT! IT WILL CAUSE LOTS OF ERRORS IF IT DOESN'T STOP
//		ReflectionHelper.setBoolean(true, "canceled", consoleActionEvent);
		objectEvent.cancel();
		
	}
	
}
