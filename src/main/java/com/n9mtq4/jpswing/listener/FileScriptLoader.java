package com.n9mtq4.jpswing.listener;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;

import java.io.*;

/**
 * Created by will on 7/1/15 at 2:14 PM.
 */
public class FileScriptLoader extends ConsoleListener {
	
	private static final String commandName = "runfile";
	
	/**
	 * 0 = runfile
	 * 1 = file path (file path CAN have spaces)
	 * */
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
		
		if (!consoleActionEvent.getCommand().getArg(0).equalsIgnoreCase(commandName)) return;
		if (consoleActionEvent.getCommand().getLength() < 2) return;
		
//		define the file
		String filePath = consoleActionEvent.getCommand().getAfterPattern(commandName + " ").trim();
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
					consoleActionEvent.getInitiatingBaseConsole().push(line);
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
