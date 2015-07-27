package com.n9mtq4.jinterpreter;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.jinterpreter.runtime.JIntRuntime;

/**
 * Created by will on 6/14/15 at 8:44 PM.
 */
public class JInterpreter {
	
	public static final boolean RUN_SERVER = false;
	public static final int PORT = 45454;
	
	public static JInterpreter instance;
	
	public static void main(String[] args) {
		
		if (instance != null) return;
		instance = new JInterpreter();
		
	}
	
	@Deprecated
	private BaseConsole server;
	private JIntRuntime runtime;
	
	public JInterpreter() {
		
		this.runtime = new JIntRuntime();
		
	}
	
	@Deprecated
	public BaseConsole getServer() {
		return server;
	}
	
	@Deprecated
	public void setServer(BaseConsole server) {
		this.server = server;
	}
	
	public JIntRuntime getRuntime() {
		return runtime;
	}
	
}
