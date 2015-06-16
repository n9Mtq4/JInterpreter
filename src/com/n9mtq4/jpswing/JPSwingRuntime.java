package com.n9mtq4.jpswing;

import java.util.ArrayList;

/**
 * Created by will on 6/15/15 at 8:55 PM.
 */
public class JPSwingRuntime {
	
	private ArrayList<JPSwingVariable> variables;
	
	public JPSwingRuntime() {
		
		this.variables = new ArrayList<>();
		
	}
	
	public void addVariable(JPSwingVariable variable) {
		
		if (getVariableByName(variable.getName()) != null) throw new IllegalArgumentException("Variables can't have the same name");
		
		variables.add(variable);
		
	}
	
	private JPSwingVariable getVariableByName(String name) {
		
		for (JPSwingVariable variable : variables) {
			if (variable.getName().equals(name)) return variable;
		}
		
		return null;
		
	}
	
	public ArrayList<JPSwingVariable> getVariables() {
		return variables;
	}
	
}
