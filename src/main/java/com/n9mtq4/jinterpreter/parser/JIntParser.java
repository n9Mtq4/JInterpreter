package com.n9mtq4.jinterpreter.parser;

/**
 * Created by will on 7/26/15 at 11:19 PM.
 */
public class JIntParser {
	
	private ClauseManager clauseManager;
	
	public ClauseManager getClauseManager() {
		return clauseManager;
	}
	
	public Object parseClause(String clause) {
		
		for (Clause c : getClauseManager().clauses) {
			
//			TODO: deprecate otherClauses?
			if (c.correctClause(clause, new String[]{})) {
				
//				TODO: deprecate otherClauses?
				return c.evaluate(clause, new String[]{});
				
			}
			
		}
		
//		TODO: throw an error instead!
		return null;
		
	}
	
}
