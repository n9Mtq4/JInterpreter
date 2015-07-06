package com.n9mtq4.jpswing.utils;

import com.n9mtq4.console.lib.gui.GuiSocket;
import com.n9mtq4.jpswing.JInterpreter;

/**
 * Created by will on 6/14/15 at 8:48 PM.
 */
public class SocketHandler extends GuiSocket {
	
	public SocketHandler() {
		this.port = JInterpreter.PORT;
	}
	
	@Override
	public void go() {
		System.out.println("Starting Server");
		super.go();
	}
	
}
