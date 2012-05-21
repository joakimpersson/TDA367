package com.github.joakimpersson.tda367.controller.input;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Input;

/**
 * 
 * @author joakimpersson
 * 
 */
public class DefaultKeyMappings {
	private static DefaultKeyMappings instance = null;
	private List<int[]> mappings = null;

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
	 * Some stupid documentation
	 * 
	 * @precondition keyCodes.length == 6
	 * @precondition keyCodes[0] is Move Up
	 * @precondition keyCodes[1] is Move Down
	 * @precondition keyCodes[2] is Move Left
	 * @precondition keyCodes[3] is Move Right
	 * @precondition keyCodes[4] is Primary Action
	 * @precondition keyCodes[5] is Secondary Action
	 * 
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

	public int[] getKeyMap(int index) {
		int i = index % mappings.size();
		return mappings.get(i);
	}

	public Integer getProceedButton() {
		return Input.KEY_ENTER;
	}
	
	public Integer getActionButton(int index) {
		return mappings.get(index)[4];
	}
}
