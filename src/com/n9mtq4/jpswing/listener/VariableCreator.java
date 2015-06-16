package com.n9mtq4.jpswing.listener;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;
import com.n9mtq4.jpswing.JPSwing;
import com.n9mtq4.jpswing.runtime.JPSwingVariable;
import com.n9mtq4.jpswing.utils.ReflectionHelper;

import java.util.ArrayList;

/**
 * Created by will on 6/15/15 at 8:54 PM.
 */
public class VariableCreator extends ConsoleListener {
	
	/**
	 * 1 = variable name<br>
	 * 2 = class name<br>
	 * 3... = arguments<br>
	 * */
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
		
		if (!consoleActionEvent.getCommand().getArg(0).equals("create")) return;
		
		String varName = consoleActionEvent.getCommand().getArg(1);
		String varClass = consoleActionEvent.getCommand().getArg(2);
		
		ArrayList<Object> args = new ArrayList<>();
		
//		TODO: MIGRATE TO JPSwingParseArg =============START=============================================================
		for (int i = 3; i < consoleActionEvent.getCommand().getLength(); i++) {
			
			/*
			* "" = string | more than one word
			* {} = variable | 1 word
			* nothing = parse to double or int, based off of contains("."); | 1 word
			* or if throws NumberFormatException, try parsing to boolean
			* */
			
			if (consoleActionEvent.getCommand().getArg(i).startsWith("\"")) {
//				string, but remember may be more than one word
				
//				first, just in case the string is one word
				if (consoleActionEvent.getCommand().getArg(i).endsWith("\"")) {
					
				}
				
			}else if (consoleActionEvent.getCommand().getArg(i).startsWith("{") && consoleActionEvent.getCommand().getArg(i).endsWith("}")) {
//				variable
				
				
				
			}else {
//				number or boolean
//				I know this is an ugly mess of nested conditions in no human readable format, but it should work... for now.
				
//				try a double
				if (consoleActionEvent.getCommand().getArg(i).contains(".")) {
					try {
						double arg = Double.parseDouble(consoleActionEvent.getCommand().getArg(i));
						args.add(arg); //ADDS ARGUMENT
					}catch (NumberFormatException e) {
						e.printStackTrace();
						throw e; //bubble
					}
				}else {
					
//					try an int
					try {
						int arg = Integer.parseInt(consoleActionEvent.getCommand().getArg(i));
						args.add(arg); //ADDS ARGUMENT
					}catch (NumberFormatException e) {
						
						if (consoleActionEvent.getCommand().getArg(i).equalsIgnoreCase("true")) {
							args.add(true); //ADDS ARGUMENT
						}else if (consoleActionEvent.getCommand().getArg(i).equalsIgnoreCase("false")) {
							args.add(false); //ADDS ARGUMENT
						}else {
//							not a boolean, so some other mistake
							e.printStackTrace();
							throw e;
						}
						
					}
					
				}
				
			}
			
		}
//		TODO: MIGRATE TO JPSwingParseArg =============END===============================================================
		
		Object[] sArgs = new Object[args.size()];
		System.arraycopy(args.toArray(), 0, sArgs, 0, args.size());
		
		Object varValue = ReflectionHelper.callConstructor(ReflectionHelper.getClassByFullName(varClass), sArgs);
		JPSwingVariable<Object> variable = new JPSwingVariable(varName, varValue);
		
		JPSwing.instance.getRuntime().addVariable(variable);
		
		baseConsole.println("Added variable " + variable.getName() + " of class " + variable.getValue().getClass().getName());
		
	}
	
}
