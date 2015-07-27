package com.n9mtq4.jinterpreter.parser.clauses.primitive;

import com.n9mtq4.jinterpreter.parser.Clause;
import com.n9mtq4.jinterpreter.parser.ClauseParsingException;

/**
 * Created by will on 7/27/15 at 8:40 AM.
 */
public class PrimitiveDouble implements Clause<Double> {
	
//	TODO: support for 1.5D rather than just 1.5 - This will also allow for difference of float and double.
	
	@Override
	public boolean correctClause(String thisClause, String[] otherClauses) {
		
		try {
			
			double d = Double.parseDouble(thisClause.trim());
			return true;
			
		}catch (NumberFormatException e) {
			return false;
		}
		
	}
	
	@Override
	public Double evaluate(String thisClause, String[] otherClauses) throws ClauseParsingException {
		
		try {
			
			double d = Double.parseDouble(thisClause.trim());
			return d;
			
		}catch (NumberFormatException e) {
			throw new ClauseParsingException("Error Parsing Primitive Double. correctClause was true, but evaluating failed!");
		}
		
	}
	
}
