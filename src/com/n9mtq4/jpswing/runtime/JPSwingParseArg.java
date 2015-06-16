package com.n9mtq4.jpswing.runtime;

import com.n9mtq4.jpswing.JPSwing;

/**
 * Created by will on 6/15/15 at 9:51 PM.
 */
public class JPSwingParseArg {
	
	public static Object[] parseArgs(int startIndex, String[] allArg, String allArgs) {
		return new Object[] {true};
	}
	
	public static ParseArgReturn parseArg(String arg, int index, String[] allArg, String allArgs) throws Exception {
		
//		TODO: migrate from VariableCreator
		
		
		if (arg.contains("\"")) {
//			String
			
//			if the string is one word
			if (arg.endsWith("\"")) {
				String eString = arg.substring(1, arg.length() - 1); //crop off " and "
				return new ParseArgReturn<>(index, eString);
			}
			
//			if it is more than one word, we have to search through allArgs.
//			TODO: loop
			
		}else if (arg.startsWith("{") && arg.startsWith("}")) {
//			JPSwingVariable
			
			String eVarName = arg.substring(1, arg.length() - 1); //crop off { and }
			JPSwingVariable eVar = JPSwing.instance.getRuntime().getVariableByName(eVarName);
			return new ParseArgReturn<>(index, eVar);
			
		}else {
//			number or boolean
			
//			try a double
			if (arg.contains(".")) {
//				is a double and not an int
				try {
					double earg = Double.parseDouble(arg);
					return new ParseArgReturn<>(index, earg);
				}catch (NumberFormatException e) {
//					this is NOT expected
					e.printStackTrace();
					throw e; //bubble for possibly more handling
				}
			}
			
//			try an int
			try {
				int earg = Integer.parseInt(arg);
				return new ParseArgReturn<>(index, earg);
			}catch (NumberFormatException e) {
//				this is expected
			}
			
//			try a boolean
			if (arg.equalsIgnoreCase("true")) {
				return new ParseArgReturn<>(index, true);
			}else if (arg.equalsIgnoreCase("false")) {
				return new ParseArgReturn<>(index, false);
			}
			
//			nothing else to try, must be an error
//			the code will continue and pop out of the if statement
//			and go on to throw an Exception
			
		}
		
		throw new Exception("Error parsing argument: " + arg);
		
	}
	
	public static class ParseArgReturn<E> {
		
		private int index;
		private E eval;
		
		public ParseArgReturn(int index, E eval) {
			this.index = index;
			this.eval = eval;
		}
		
		public int getIndex() {
			return index;
		}
		
		public E getEval() {
			return eval;
		}
		
	}
	
}
