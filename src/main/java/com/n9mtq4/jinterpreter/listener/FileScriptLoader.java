package com.n9mtq4.jinterpreter.listener;

import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.events.ObjectEvent;
import com.n9mtq4.logwindow.listener.ObjectListener;
import com.n9mtq4.logwindow.utils.StringParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by will on 7/1/15 at 2:14 PM.
 */
public class FileScriptLoader implements ObjectListener {
	
	private static final String commandName = "runfile";
	
	/**
	 * 0 = runfile
	 * 1 = file path (file path CAN have spaces)
	 * */
	@Override
	public void objectReceived(ObjectEvent objectEvent, BaseConsole baseConsole) {
		
		if (!objectEvent.isUserInputString()) return;
		StringParser stringParser = new StringParser(objectEvent);
		
		if (!stringParser.getArg(0).equalsIgnoreCase(commandName)) return;
		if (stringParser.getLength() < 2) return;
		
//		define the file
		String filePath = stringParser.getAfterPattern(commandName + " ").trim();
		File file = new File(filePath);
		if (!file.exists()) {
			baseConsole.println(filePath + " Does not exist!");
			return;
		}
		
//		read the file
		try {
			
			FileInputStream fis = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			
			String line;
			while ((line = br.readLine()) != null) {
				
//				check for comments
				if (!line.startsWith("#")) {
//					send it to one BaseConsole only
					objectEvent.getInitiatingBaseConsole().pushString(line);
				}
				
			}
			
			fis.close();
			
		}catch (FileNotFoundException e) {
			baseConsole.println("There was an error reading the file!");
			e.printStackTrace();
			baseConsole.printStackTrace(e);
		}catch (IOException e) {
			baseConsole.println("There was an error reading the file!");
			e.printStackTrace();
			baseConsole.printStackTrace(e);
		}
		
		
	}
	
}
