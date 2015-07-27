package com.n9mtq4.jinterpreter.parser;

/**
 * Created by will on 7/26/15 at 11:19 PM.
 */
public class JIntParser {
	
	private ClauseManager clauseManager;
	
	public JIntParser() {
		
		this.clauseManager = new ClauseManager();
		clauseManager.registerClauses();
		
	}
	
	public Object parseClause(String clause) throws ClauseParsingException {
		
		ClauseParsingException e = null;
		for (Clause c : getClauseManager().clauses) {
			
//			TODO: deprecate otherClauses?
			if (c.correctClause(clause, new String[]{})) {
				
//				TODO: deprecate otherClauses?
				try {
					
					return c.evaluate(clause, new String[]{});
					
				}catch (ClauseParsingException e1) {
					e = e1;
				}
				
			}
			
		}
		
		if (e != null) throw e;
		throw new ClauseParsingException("A clause to parse the given input was not found!");
		
	}
	
	public ClauseManager getClauseManager() {
		return clauseManager;
	}
	
}
