package com.n9mtq4.jpswing.listener;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;
import com.n9mtq4.console.lib.utils.ReflectionHelper;
import com.n9mtq4.jpswing.JPSwing;
import com.n9mtq4.jpswing.runtime.JPSwingParseArg;
import com.n9mtq4.jpswing.runtime.JPSwingVariable;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by will on 6/15/15 at 9:42 PM.
 */
public class MethodExecutor extends ConsoleListener {
	
	/**
	 * 0 = invoke
	 * 1 = variable name
	 * 2 = method name to run
	 * 3... = arguments
	 * */
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
		
		if (!consoleActionEvent.getCommand().getArg(0).equals("invoke")) return;
		if (consoleActionEvent.getCommand().getLength() < 3) return;
		
		String varName = consoleActionEvent.getCommand().getArg(1);
		JPSwingVariable var = JPSwing.instance.getRuntime().getVariableByName(varName);
		
		String methodName = consoleActionEvent.getCommand().getArg(2);
		
		Object[] args = JPSwingParseArg.parseArgs(3, consoleActionEvent.getCommand().getArgs(), consoleActionEvent.getCommand().getText());
		
		try {
			
			Method m = ReflectionHelper.getAllDeclaredMethod(methodName, ReflectionHelper.getClassParams(args), var.getValueType());
			Object result = ReflectionHelper.callObjectMethod(m, var.getValue(), args);
			
			baseConsole.println("invoked method " + var.getName() + "." + m.getName() + " with params " + Arrays.toString(m.getParameterTypes()));
			
			if (result != null) {
				baseConsole.pushObject(result, "result method " + var.getName() + methodName);
				
				JPSwingVariable<Object> resultVar = JPSwing.instance.getRuntime().getVariableByName("result"); //TODO: intellij warning
				if (resultVar != null) {
					resultVar.setValue(result);
				}else {
					resultVar = new JPSwingVariable<Object>("result", result);
					JPSwing.instance.getRuntime().addVariable(resultVar);
				}
			}
			
		}catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		
	}
	
}
