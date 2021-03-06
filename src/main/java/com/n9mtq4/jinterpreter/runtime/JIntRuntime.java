package com.n9mtq4.jinterpreter.runtime;

import com.n9mtq4.jinterpreter.parser.JIntParser;
import com.n9mtq4.logwindow.BaseConsole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by will on 6/15/15 at 8:55 PM.
 */
public class JIntRuntime {
	
	private ArrayList<JIntVariable> variables;
	private JIntParser parser;
	
	public static String getString(Object obj) {
		
		String value;
		if (obj instanceof Object[]) {
			value = Arrays.toString((Object[]) obj);
		}else if (obj instanceof Collection) {
			value = Arrays.toString(((Collection) obj).toArray());
		}else {
			value = obj.toString();
		}
		
		return value;
		
	}
	
	public JIntRuntime() {
		
		this.variables = new ArrayList<JIntVariable>();
		this.parser = new JIntParser();
		
	}
	
	public void output(Object o, BaseConsole console) {
		console.push(o, "output");
	}
	
	public void addVariable(JIntVariable variable) {
		
		if (getVariableByName(variable.getName()) != null) throw new IllegalArgumentException("Variables can't have the same name");
		
		variables.add(variable);
		
	}
	
	public void removeVariable(JIntVariable variable) {
		variables.remove(variable);
	}
	
	public JIntVariable getVariableByName(String name) {
		
		for (JIntVariable variable : variables) {
			if (variable.getName().equals(name)) return variable;
		}
		
		return null;
		
	}
	
	public void updateResult(Object result) {
		
		@SuppressWarnings("unchecked")
		JIntVariable<Object> resultVar = getVariableByName("result"); //TODO: intellij warning
		if (resultVar != null) {
			resultVar.setValue(result);
		}else {
			resultVar = new JIntVariable<Object>("result", result);
			addVariable(resultVar);
		}
		
	}
	
	public ArrayList<JIntVariable> getVariables() {
		return variables;
	}
	
	public JIntParser getParser() {
		return parser;
	}
	
}
