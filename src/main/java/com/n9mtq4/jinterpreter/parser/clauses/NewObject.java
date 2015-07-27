package com.n9mtq4.jinterpreter.parser.clauses;

import com.n9mtq4.console.lib.command.ConsoleCommand;
import com.n9mtq4.jinterpreter.JInterpreter;
import com.n9mtq4.jinterpreter.parser.Clause;
import com.n9mtq4.jinterpreter.parser.ClauseParsingException;
import com.n9mtq4.reflection.ReflectionHelper;

/**
 * Created by will on 7/26/15 at 11:13 PM.
 */
public class NewObject implements Clause<Object> {
	
	@Override
	public boolean correctClause(String thisClause, String[] otherClauses) {
		return thisClause.startsWith("new ") && thisClause.split(" ").length > 1;
	}
	
	/**
	 * new ClassName(clause, clause, clause...);
	 * */
	@Override
	public Object evaluate(String thisClause, String[] otherClauses) throws ClauseParsingException {
		
//		get the class name after "new " but before the "(" ex "new String(\"Hello World\")"
		String className = new ConsoleCommand(thisClause).getBetween("new ", "(").trim();
		
//		now the args for the class are going to be separated by "," inside the ()'s.
//		TODO: Doesn't support something like "new SomeObject(\"Hi\", new SomeObject("Hi", \"I will NOT work!\"))"
//		TODO: implement a recursive loop for parentheses parsing
		String[] sArgs = new ConsoleCommand(thisClause).getBetween(className + "(", ");").split(",");
		Object[] args = new Object[sArgs.length];
		
		for (int i = 0; i < sArgs.length; i++) {
			args[i] = JInterpreter.instance.getRuntime().getParser().parseClause(sArgs[i].trim());
		}
		
		return ReflectionHelper.callConstructor(ReflectionHelper.getClass(className), args);
		
	}
	
}
