package com.n9mtq4.jpswing;

/**
 * Created by will on 6/15/15 at 8:55 PM.
 */
public class JPSwingVariable<E> {
	
	private String name;
	private E value;
	
	public JPSwingVariable(String name, E value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public E getValue() {
		return value;
	}
	
	public void setValue(E value) {
		this.value = value;
	}
	
}
