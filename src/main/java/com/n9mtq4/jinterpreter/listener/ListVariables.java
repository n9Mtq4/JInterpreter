package com.n9mtq4.jinterpreter.listener;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;
import com.n9mtq4.jinterpreter.JInterpreter;
import com.n9mtq4.jinterpreter.runtime.JIntVariable;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by will on 6/25/15 at 9:06 PM.
 */
public class ListVariables extends ConsoleListener {
	
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
		
		if (!consoleActionEvent.getCommand().eqt("listvars")) return;
		
		ArrayList<JIntVariable> vars = JInterpreter.instance.getRuntime().getVariables();
		String[] varNames = new String[vars.size()];
		
		for (int i = 0; i < vars.size(); i++) {
			varNames[i] = vars.get(i).getName();
		}
		
		baseConsole.println(Arrays.toString(varNames));
		
	}
	
}
