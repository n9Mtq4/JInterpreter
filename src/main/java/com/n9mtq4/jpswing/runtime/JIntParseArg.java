package com.n9mtq4.jpswing.runtime;

import com.n9mtq4.jpswing.JInterpreter;

import java.util.ArrayList;

/**
 * Created by will on 6/15/15 at 9:51 PM.
 */
public class JIntParseArg {
	
	public static Object[] parseArgs(int startIndex, String[] allArg, String allArgs) {
		ArrayList<Object> objects = new ArrayList<Object>();
		for (int i = startIndex; i < allArg.length; i++) {
			try {
				ParseArgReturn result = parseArg(allArg[i], i, allArg, allArgs);
				i = result.getIndex();
				objects.add(result.getEval());
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		Object[] objects1 = new Object[objects.size()];
		System.arraycopy(objects.toArray(), 0, objects1, 0, objects.size());
		return objects1;
	}
	
	public static ParseArgReturn parseArg(String arg, int index, String[] allArg, String allArgs) throws Exception {
		
		if (arg.startsWith("\"")) {
//			String
			
//			if the string is one word
			if (arg.endsWith("\"")) {
				String eString = arg.substring(1, arg.length() - 1); //crop off " and "
				return new ParseArgReturn<String>(index, eString);
			}
			
//			if it is more than one word, we have to search through allArgs.
//			TODO: support for double spaces
			String str = "";
			str += allArg[index].substring(1) + " ";
			int i = index + 1;
			while (!allArg[i].endsWith("\"")) {
				str += allArg[i] + " ";
				i++;
			}
			
			str += allArg[i].substring(0, allArg[i].length() - 1);
			return new ParseArgReturn<String>(i, str);
			
		}else if (arg.startsWith("{") && arg.endsWith("}")) {
//			JPSwingVariable
			
			String eVarName = arg.substring(1, arg.length() - 1); //crop off { and }
			JIntVariable eVar = JInterpreter.instance.getRuntime().getVariableByName(eVarName);
			return new ParseArgReturn<Object>(index, eVar.getValue());
			
		}else {
//			number or boolean
			
//			try a double
			if (arg.contains(".")) {
//				is a double and not an int
				try {
					double earg = Double.parseDouble(arg);
					return new ParseArgReturn<Double>(index, earg);
				}catch (NumberFormatException e) {
//					this is NOT expected
					e.printStackTrace();
					throw e; //bubble for possibly more handling
				}
			}
			
//			try an int
			try {
				int earg = Integer.parseInt(arg);
				return new ParseArgReturn<Integer>(index, earg);
			}catch (NumberFormatException e) {
//				this is expected
			}
			
//			try a boolean
			if (arg.equalsIgnoreCase("true")) {
				return new ParseArgReturn<Boolean>(index, true);
			}else if (arg.equalsIgnoreCase("false")) {
				return new ParseArgReturn<Boolean>(index, false);
			}
			
//			try null
			if (arg.equalsIgnoreCase("null")) {
				return new ParseArgReturn<Object>(index, null);
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
