package com.n9mtq4.jinterpreter.reflection;

import com.n9mtq4.logwindow.events.AdditionEvent;
import com.n9mtq4.logwindow.listener.AdditionListener;
import com.n9mtq4.logwindow.utils.JarLoader;

import java.io.File;
import java.io.IOException;

/**
 * Created by will on 7/6/15 at 2:15 AM.
 */
public class ReflectionLoader implements AdditionListener {
	
	private static final String reflectionHelperLocation = "ReflectionHelper.jar";
	
	public void onAddition(AdditionEvent e) {
		
		try {
			JarLoader.addFile(new File(reflectionHelperLocation));
			e.getBaseConsole().print("Added ReflectionHelper to ClassPath\n> ");
		}catch (IOException e1) {
			e1.printStackTrace();
			e.getBaseConsole().printStackTrace(e1);
			e.getBaseConsole().println("Error in adding ReflectionHelper to ClassPath");
		}
		
//		This is a initializing listener, so disable it after its use
		e.getBaseConsole().disableListenerAttribute(this);
		
//		TODO: remove this listener or is just disabling it ok?
//		You can't just remove it, because for some reason it gets re-added over and over
/*		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				}catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				
				e.getBaseConsole().removeListener(ReflectionLoader.this);
				
			}
		}).start();*/
		
	}
	
}
