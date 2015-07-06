package com.n9mtq4.jpswing.runtime;

import java.util.ArrayList;

/**
 * Created by will on 6/15/15 at 8:55 PM.
 */
public class JIntRuntime {
	
	private ArrayList<JIntVariable> variables;
	
	public JIntRuntime() {
		
		this.variables = new ArrayList<JIntVariable>();
		
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
	
}
