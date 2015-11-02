package com.n9mtq4.jinterpreter.listener;

import com.n9mtq4.jinterpreter.JInterpreter;
import com.n9mtq4.jinterpreter.runtime.JIntVariable;
import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.events.ObjectEvent;
import com.n9mtq4.logwindow.listener.ObjectListener;
import com.n9mtq4.logwindow.utils.StringParser;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by will on 6/25/15 at 9:06 PM.
 */
public class ListVariables implements ObjectListener {
	
	/**
	 * listvars
	 * */
	@Override
	public void objectReceived(ObjectEvent objectEvent, BaseConsole baseConsole) {
		
		if (!objectEvent.isUserInputString()) return;
		StringParser stringParser = new StringParser(objectEvent);
		
		if (!stringParser.eqt("listvars")) return;
		
		ArrayList<JIntVariable> vars = JInterpreter.instance.getRuntime().getVariables();
		String[] varNames = new String[vars.size()];
		
		for (int i = 0; i < vars.size(); i++) {
			varNames[i] = vars.get(i).getName();
		}
		
		baseConsole.println(Arrays.toString(varNames));
		
	}
	
}
