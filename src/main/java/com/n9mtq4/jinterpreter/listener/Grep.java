package com.n9mtq4.jinterpreter.listener;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.events.AdditionActionEvent;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;
import com.n9mtq4.jinterpreter.utils.ArgParser;
import com.n9mtq4.jinterpreter.utils.NoSuchValueInArgumentException;

/**
 * Created by will on 7/7/15 at 11:04 PM.
 */
public class Grep extends ConsoleListener {
	
	@Override
	public void onAddition(AdditionActionEvent e) {
		super.onAddition(e);
	}
	
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
		
		if (!consoleActionEvent.getCommand().getArg(0).equalsIgnoreCase("grep")) return;
		
		ArgParser argParser = new ArgParser(consoleActionEvent.getCommand().getArgs());
		
		String seperation = "\n";
		if (argParser.contains("-s")) {
			seperation = " ";
		}else if ()
		try {
			seperation = argParser.getString("-s");
		}catch (NoSuchValueInArgumentException e) {
//			this is 100% ok, just revert to default
		}
		
		try {
			String find = argParser.getString("-f");
		}catch (NoSuchValueInArgumentException e) {
			baseConsole.println("Please add a -f for what you want to find");
		}
		
	}
	
}
