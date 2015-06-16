package com.n9mtq4.jpswing;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.gui.GuiScanner;

/**
 * Created by will on 6/14/15 at 8:44 PM.
 */
public class JPSwing {
	
	public static final int PORT = 4545;
	
	public static JPSwing instance;
	
	public static void main(String[] args) {
		
		if (instance != null) return;
		instance = new JPSwing();
		
	}
	
	private BaseConsole server;
	private JPSwingRuntime runtime;
	
	public JPSwing() {
		
		this.runtime = new JPSwingRuntime();
		
		this.server = new BaseConsole();
//		server.addGui(new SocketHandler());
		server.addGui(new GuiScanner());
		
		server.addListener(new JPSwingMainListener());
		
	}
	
	public BaseConsole getServer() {
		return server;
	}
	
	public JPSwingRuntime getRuntime() {
		return runtime;
	}
	
}
