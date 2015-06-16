package com.n9mtq4.jpswing;

import com.n9mtq4.console.lib.gui.GuiSocket;

/**
 * Created by will on 6/14/15 at 8:48 PM.
 */
public class SocketHandler extends GuiSocket {
	
	public SocketHandler() {
		this.port = JPSwing.PORT;
	}
	
	@Override
	public void go() {
		System.out.println("Starting Server");
		super.go();
	}
	
}
