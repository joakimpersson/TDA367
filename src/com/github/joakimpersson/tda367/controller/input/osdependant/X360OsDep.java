package com.github.joakimpersson.tda367.controller.input.osdependant;

import org.newdawn.slick.Input;

/**
 * Keymapping for Xbox360 controller
 * 
 * @author adderollen
 * 
 */
public class X360OsDep {
	private final static String platform = System.getProperty("os.name");
	private final static String MAC = "Mac OS X";
	private final static String WINDOWS = "Windows";

	/**
	 * Get the proceed keycode.
	 * 
	 * @return The proceed keycode
	 */
	public static int getProceed() {
		// should be mapped to START
		if (platform.equals(MAC)) {
			return 4;
		} else if (platform.startsWith(WINDOWS)) {
			return 7;
		}
		return 0;
	}

	/**
	 * Get the primary action keycode
	 * 
	 * @return The primary action keycode
	 */
	public static int getPrimaryAction() {
		// should be mapped to A
		if (platform.equals(MAC)) {
			return 11;
		} else if (platform.startsWith(WINDOWS)) {
			return 0;
		}
		return 0;
	}

	/**
	 * Get the secondary action keycode
	 * 
	 * @return The secondary action keycode
	 */
	public static int getSecondaryAction() {
		// should be mapped to X
		if (platform.equals(MAC)) {
			return 13;
		} else if (platform.startsWith(WINDOWS)) {
			return 2;
		}
		return 0;
	}

	/**
	 * Get the direction of any input keycode
	 * 
	 * @param input
	 *            The input that will be checked
	 * @param controllerId
	 *            The controller identity
	 * @return A String containing the direction the given input means
	 */
	public static String getDPadButtonPressed(Input input, int controllerId) {
		if (platform.equals(MAC)) {
			if (input.isControlPressed(4, controllerId)) {
				return "up";
			} else if (input.isControlPressed(5, controllerId)) {
				return "down";
			} else if (input.isControlPressed(6, controllerId)) {
				return "left";
			} else if (input.isControlPressed(7, controllerId)) {
				return "right";
			}
		} else if (platform.startsWith(WINDOWS)) {
			if (input.isControlPressed(2, controllerId)) {
				return "up";
			} else if (input.isControlPressed(3, controllerId)) {
				return "down";
			} else if (input.isControlPressed(0, controllerId)) {
				return "left";
			} else if (input.isControlPressed(1, controllerId)) {
				return "right";
			}
		}
		return "none";
	}

}
