package com.n9mtq4.jinterpreter.listener;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.annotation.Async;
import com.n9mtq4.console.lib.events.AdditionActionEvent;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;
import com.n9mtq4.console.lib.events.SentObjectEvent;
import com.n9mtq4.jinterpreter.runtime.JIntRuntime;
import com.n9mtq4.reflection.ReflectionHelper;

/**
 * Created by will on 7/7/15 at 10:45 PM.
 */
public class PipeHandler extends ConsoleListener {
	
	private boolean isListening;
	private String nextCommand;
	
	@Override
	public void onAddition(AdditionActionEvent e) {
		this.isListening = false;
		this.nextCommand = "";
	}
	
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
		
		if (!consoleActionEvent.getCommand().contains(" | ")) return;
		if (consoleActionEvent.getCommand().getText().indexOf(" | ") != consoleActionEvent.getCommand().getText().lastIndexOf(" | ")) return;
		
		String[] commands = consoleActionEvent.getCommand().getText().split(" | ");
		
		isListening = true;
		nextCommand = commands[1];
		
		consoleActionEvent.getInitiatingBaseConsole().push(commands[0]);
		
//		stop it from going onto other listeners
//		THIS IS VERY IMPORTANT! IT WILL CAUSE LOTS OF ERRORS IF IT DOESN'T STOP
		ReflectionHelper.setBoolean(true, "canceled", consoleActionEvent);
		
	}
	
	@Async
	@Override
	public void objectReceived(SentObjectEvent e, BaseConsole baseConsole) {
		
		if (!isListening) return;
		if (!e.getMessage().equals("output")) return;
		
//		we have received the output from the first command.
//		now run the second command while sending it this output
		
//		TODO: will be changed to getInitiatingBaseConsole
//		TODO: [HIGH] change to sending other listener the object rather than a string!
		e.getBaseConsole().push(nextCommand + JIntRuntime.getString(e.getObject()));
		
//		stop listening for other objects
		this.isListening = false;
		this.nextCommand = "";
		
	}
	
}
