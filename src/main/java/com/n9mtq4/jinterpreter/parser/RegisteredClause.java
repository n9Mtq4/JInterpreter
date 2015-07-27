package com.n9mtq4.jinterpreter.parser;

import com.n9mtq4.jinterpreter.JInterpreter;

/**
 * Created by will on 7/26/15 at 11:12 PM.
 */
public abstract class RegisteredClause<E> implements Clause<E> {
	
	public RegisteredClause() {
		registerThis();
	}
	
	protected void registerThis() {
//		get ClauseHandler and register this clause
		JInterpreter.instance.getRuntime().getParser().getClauseManager().register(this);
	}
	
}
