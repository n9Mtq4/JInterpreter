package com.n9mtq4.jinterpreter.parser.clauses.primitive;

import com.n9mtq4.jinterpreter.parser.Clause;
import com.n9mtq4.jinterpreter.parser.ClauseParsingException;

/**
 * Created by will on 7/27/15 at 8:37 AM.
 */
public class PrimitiveBoolean implements Clause<Boolean> {
	
	@Override
	public boolean correctClause(String thisClause, String[] otherClauses) {
		
		return (thisClause.trim().equals("true") || thisClause.trim().equals("false"));
		
	}
	
	@Override
	public Boolean evaluate(String thisClause, String[] otherClauses) throws ClauseParsingException {
		
		if (thisClause.trim().equals("true")) return true;
		if (thisClause.trim().equals("false")) return false;
		
		throw new ClauseParsingException("Error Parsing Primitive Boolean. correctClause was true, but evaluating failed!");
		
	}
	
}
