package com.n9mtq4.jpswing;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.jpswing.runtime.JPSwingRuntime;

/**
 * Created by will on 6/14/15 at 8:44 PM.
 */
public class JPSwing {
	
	public static final int PORT = 45454;
	
	public static JPSwing instance;
	
	public static void main(String[] args) {
		
		if (instance != null) return;
		instance = new JPSwing();
		
	}
	
	private BaseConsole server;
	private JPSwingRuntime runtime;
	
	public JPSwing() {
		
		this.runtime = new JPSwingRuntime();
		
	}
	
	public BaseConsole getServer() {
		return server;
	}
	
	public JPSwingRuntime getRuntime() {
		return runtime;
	}
	
}