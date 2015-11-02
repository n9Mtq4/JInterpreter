package com.n9mtq4.jinterpreter.parser;

import com.n9mtq4.reflection.ReflectionHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by will on 7/26/15 at 11:15 PM.
 */
public class ClauseManager {
	
	protected ArrayList<Clause> clauses;
	
	public ClauseManager() {
		this.clauses = new ArrayList<Clause>();
	}
	
	public void registerClauses() {
		
		InputStream in = getClass().getResourceAsStream("/clauses.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		
		try {
			
//			proccess the list of clauses line by line
			String line;
			while ((line = reader.readLine()) != null) {
				
//				support for comments and blank lines!
				if (!line.startsWith("#") && !line.trim().equals("")) {
					
//					make a new instance of the clause
					Clause clause = ReflectionHelper.callConstructor(ReflectionHelper.getClassByFullName(line.trim()));
//					register it
					register(clause);
					
				}
				
			}
			
//			close
			in.close();
			reader.close();
			
//			oops something happened that is bad
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void register(Clause clause) {
		
		this.clauses.add(clause);
		
	}
	
}
