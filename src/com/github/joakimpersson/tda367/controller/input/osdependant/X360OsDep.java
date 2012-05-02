package com.github.joakimpersson.tda367.controller.input.osdependant;

public class X360OsDep {
	private final static String platform = System.getProperty("os.name");

	// should be mapped to START
	public static int getProceed() {
		if (platform.equals("Mac OS X")) {
			return 4;
		}
		return 1; // this is wrong at the moment
	}
	
	// should be mapped to A
	public static int getAction() {
		if (platform.equals("Mac OS X")) {
			return 11;
		}
		return 1; // this is wrong at the moment
	}

}
