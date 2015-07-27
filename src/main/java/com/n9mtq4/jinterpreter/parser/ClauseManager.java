package com.n9mtq4.jinterpreter.parser;

import java.util.ArrayList;

/**
 * Created by will on 7/26/15 at 11:15 PM.
 */
public class ClauseManager {
	
	protected ArrayList<Clause> clauses;
	
	public ClauseManager() {
		this.clauses = new ArrayList<Clause>();
	}
	
	public void register(Clause clause) {
		
		this.clauses.add(clause);
		
	}
	
}
