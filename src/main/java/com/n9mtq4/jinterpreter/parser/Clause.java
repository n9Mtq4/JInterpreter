package com.n9mtq4.jinterpreter.parser;

/**
 * Created by will on 7/26/15 at 11:17 PM.
 */
public interface Clause<E> {
	
	boolean correctClause(String thisClause, String[] otherClauses);
	
	E evaluate(String thisClause, String[] otherClauses) throws ClauseParsingException;
	
}
