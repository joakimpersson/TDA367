package com.github.joakimpersson.tda367.controller.input;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Input;

/**
 * A class containing default keyboard controls
 * 
 * @author joakimpersson
 * 
 * @modified rekoil
 * 
 */
public class KeyboardControls {
	private static KeyboardControls instance = null;
	private List<int[]> kayArrayList = null;

	/**
	 * Get the KeyboardControls instace
	 * 
	 * @return The instance
	 */
	public static KeyboardControls getInstance() {
		if (instance == null) {
			instance = new KeyboardControls();
		}
		return instance;
	}

	/**
	 * Constructor for the KeyboardControls class
	 * 
	 */
	private KeyboardControls() {
		init();
	}

	/**
	 * 
	 * Init the class
	 * 
	 * @precondition keyCodes.length == 6
	 * @precondition keyCodes[0] is Move Up
	 * @precondition keyCodes[1] is Move Down
	 * @precondition keyCodes[2] is Move Left
	 * @precondition keyCodes[3] is Move Right
	 * @precondition keyCodes[4] is Primary Action
	 * @precondition keyCodes[5] is Secondary Action
	 * @postcondition The mapping will we correct
	 * @param keyCodes
	 *            An array of keyboardscodes
	 */
	private void init() {
		kayArrayList = new ArrayList<int[]>();

		int[][] keyCodes = {
				{ Input.KEY_UP, Input.KEY_DOWN, Input.KEY_LEFT,
						Input.KEY_RIGHT, Input.KEY_K, Input.KEY_L },
				{ Input.KEY_W, Input.KEY_S, Input.KEY_A, Input.KEY_D,
						Input.KEY_Q, Input.KEY_1 } };
		for (int i = 0; i < 2; i++) {
			kayArrayList.add(keyCodes[i]);
		}
	}

	public int[] getKeyArray(int index) {
		int i = index % kayArrayList.size();
		return kayArrayList.get(i);
	}

	/**
	 * Get the proceed key code for a certain index
	 * 
	 */
	public Integer getProceedButton() {
		return Input.KEY_ENTER;
	}

	/**
	 * Get the action key code for a certain index
	 * 
	 * @param index
	 *            The keyarray index
	 */
	public Integer getActionButton(int index) {
		return kayArrayList.get(index)[4];
	}
}
