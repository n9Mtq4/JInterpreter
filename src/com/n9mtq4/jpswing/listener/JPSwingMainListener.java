package com.n9mtq4.jpswing.listener;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.events.AdditionActionEvent;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;

/**
 * Created by will on 6/14/15 at 9:05 PM.
 */
public class JPSwingMainListener extends ConsoleListener {
	
	@Override
	public void onAddition(AdditionActionEvent e) {
		
		BaseConsole bc = e.getBaseConsole();
		bc.addListener(new VariableCreator());
		bc.addListener(new MethodExecutor());
		
	}
	
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
	}
	
}
