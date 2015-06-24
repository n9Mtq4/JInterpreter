package com.n9mtq4.jpswing.listener;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.events.AdditionActionEvent;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;
import com.n9mtq4.jpswing.JPSwing;

/**
 * Created by will on 6/23/15 at 8:51 PM.
 */
public class JPSwingListener extends ConsoleListener {
	
	@Override
	public void onAddition(AdditionActionEvent e) {
		
		JPSwing.main(new String[]{});
		
		BaseConsole bc = e.getBaseConsole();
		
		bc.addListener(new VariableCreator());
		bc.addListener(new MethodExecutor());
		
	}
	
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {}
	
}
