package com.n9mtq4.jinterpreter.parser.clauses.primitive;

import com.n9mtq4.jinterpreter.parser.Clause;
import com.n9mtq4.jinterpreter.parser.ClauseParsingException;

/**
 * Created by will on 7/27/15 at 8:24 AM.
 */
public class PrimitiveInteger implements Clause<Integer> {
	
	@Override
	public boolean correctClause(String thisClause, String[] otherClauses) {
		
		try {
			int i = Integer.parseInt(thisClause);
			return true;
		}catch (NumberFormatException e) {
			return false;
		}
		
	}
	
	@Override
	public Integer evaluate(String thisClause, String[] otherClauses) throws ClauseParsingException {
		
		try {
			
			int i = Integer.parseInt(thisClause);
			return i;
			
		}catch (NumberFormatException e) {
			throw new ClauseParsingException("Error Parsing Primitive Integer. correctClause was true, but evaluating failed!");
		}
		
	}
	
}
