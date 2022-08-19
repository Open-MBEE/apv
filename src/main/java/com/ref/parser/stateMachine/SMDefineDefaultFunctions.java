package com.ref.parser.stateMachine;

public class SMDefineDefaultFunctions {
	
	public SMDefineDefaultFunctions() {
		
	}
	
	public String getDefaultFunctions() {
		StringBuilder stringFunctions = new StringBuilder();
		
		stringFunctions.append("\nLOOP = loop -> LOOP\n");
		stringFunctions.append("\nmax (a, b) = if a < b then b else a\n"
				+ "min (a, b) = if a < b then a else b\n");
		
		return stringFunctions.toString();
	}
}
