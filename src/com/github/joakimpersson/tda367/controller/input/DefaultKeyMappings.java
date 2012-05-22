package com.github.joakimpersson.tda367.controller.input;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Input;

/**
 * A class containing Default key mappings
 * 
 * @author joakimpersson
 * 
 */
public class DefaultKeyMappings {
	private static DefaultKeyMappings instance = null;
	private List<int[]> mappings = null;

	/**
	 * Get the DefaultKeyMappings instace
	 * 
	 * @return The instance
	 */
	public static DefaultKeyMappings getInstance() {
		if (instance == null) {
			instance = new DefaultKeyMappings();
		}
		return instance;
	}

	private DefaultKeyMappings() {
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
		mappings = new ArrayList<int[]>();

		int[][] keyCodes = {
				{ Input.KEY_UP, Input.KEY_DOWN, Input.KEY_LEFT,
						Input.KEY_RIGHT, Input.KEY_K, Input.KEY_L },
				{ Input.KEY_W, Input.KEY_S, Input.KEY_A, Input.KEY_D,
						Input.KEY_Q, Input.KEY_1 } };
		for (int i = 0; i < 2; i++) {
			mappings.add(keyCodes[i]);
		}
	}

	// TODO adrian update method name and javadoc
	public int[] getKeyMap(int index) {
		int i = index % mappings.size();
		return mappings.get(i);
	}

	/**
	 * Get the proceed key code for a certain index
	 * 
	 * @param The
	 *            keymappings index
	 */
	public Integer getProceedButton() {
		return Input.KEY_ENTER;
	}

	/**
	 * Get the action key code for a certain index
	 * 
	 * @param The
	 *            keymappings index
	 */
	public Integer getActionButton(int index) {
		return mappings.get(index)[4];
	}
}
