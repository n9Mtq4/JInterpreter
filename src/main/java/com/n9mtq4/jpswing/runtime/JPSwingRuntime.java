package com.n9mtq4.jpswing.runtime;

import java.util.ArrayList;

/**
 * Created by will on 6/15/15 at 8:55 PM.
 */
public class JPSwingRuntime {
	
	private ArrayList<JPSwingVariable> variables;
	
	public JPSwingRuntime() {
		
		this.variables = new ArrayList<JPSwingVariable>();
		
	}
	
	public void addVariable(JPSwingVariable variable) {
		
		if (getVariableByName(variable.getName()) != null) throw new IllegalArgumentException("Variables can't have the same name");
		
		variables.add(variable);
		
	}
	
	public void removeVariable(JPSwingVariable variable) {
		variables.remove(variable);
	}
	
	public JPSwingVariable getVariableByName(String name) {
		
		for (JPSwingVariable variable : variables) {
			if (variable.getName().equals(name)) return variable;
		}
		
		return null;
		
	}
	
	public void updateResult(Object result) {
		
		@SuppressWarnings("unchecked")
		JPSwingVariable<Object> resultVar = getVariableByName("result"); //TODO: intellij warning
		if (resultVar != null) {
			resultVar.setValue(result);
		}else {
			resultVar = new JPSwingVariable<Object>("result", result);
			addVariable(resultVar);
		}
		
	}
	
	public ArrayList<JPSwingVariable> getVariables() {
		return variables;
	}
	
}
