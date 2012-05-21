package com.github.joakimpersson.tda367.controller.input.osdependant;

import org.newdawn.slick.Input;

public class X360OsDep {
	private final static String platform = System.getProperty("os.name");
	private final static String MAC = "Mac OS X";

	// should be mapped to START
	public static int getProceed() {
		if (platform.equals(MAC)) {
			return 4;
		} else return 7;
	}
	
	// should be mapped to A
	public static int getPrimaryAction() {
		if (platform.equals(MAC)) {
			return 11;
		} else return 0;
	}
	
	// should be mapped to X
	public static int getSecondaryAction() {
		if (platform.equals(MAC)) {
			return 13;
		} else return 2;
	}
	
	public static String getDPadButtonPressed(Input input, int controllerId) {
		if (platform.equals(MAC)) {
			if (input.isControlPressed(4, controllerId))
				return "up";
			else if (input.isControlPressed(5, controllerId))
				return "down";
			else if (input.isControlPressed(6, controllerId))
				return "left";
			else if (input.isControlPressed(7, controllerId))
				return "right";
		} else {
			if (input.isControlPressed(2, controllerId))
				return "up";
			else if (input.isControlPressed(3, controllerId))
				return "down";
			else if (input.isControlPressed(0, controllerId))
				return "left";
			else if (input.isControlPressed(1, controllerId))
				return "right";
		}
		return "none";
	}

}
