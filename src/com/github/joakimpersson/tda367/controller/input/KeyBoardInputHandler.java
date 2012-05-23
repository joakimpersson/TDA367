package com.github.joakimpersson.tda367.controller.input;

import java.util.Arrays;

import org.newdawn.slick.Input;

import com.github.joakimpersson.tda367.model.constants.PlayerAction;
import com.github.joakimpersson.tda367.model.player.Player;

/**
 * 
 * @author joakimpersson
 * 
 */
public class KeyBoardInputHandler implements InputHandler {

	private PlayerAction currentAction;
	private Player player = null;
	private int[] keyArray;

	/**
	 * Creates a new keyboard inputhandler using one of the default keymappings
	 * 
	 * @param player
	 *            The player responsible for the actions
	 * @param id
	 *            The id of the player that is used for mapping it to a default
	 *            keyboard
	 */
	public KeyBoardInputHandler(Player player, int id) {
		this.player = player;
		this.keyArray = KeyboardControls.getInstance().getKeyArray(id);
	}

	public KeyBoardInputHandler(int id) {
		this.keyArray = KeyboardControls.getInstance().getKeyArray(id);
	}

	/**
	 * 
	 * Creates a new keyboard inputhandler with an custom keyboard mapping
	 * 
	 * @param player
	 *            The player responsible for the actions
	 * @param keyActionMap
	 *            The players custom made keymapping
	 */
	public KeyBoardInputHandler(Player player, int[] keyMap) {
		this.player = player;
		this.keyArray = keyMap;
	}

	@Override
	public boolean hasKey(Input input) {
		if (input.isKeyDown(keyArray[4])) {
			currentAction = PlayerAction.PRIMARY_ACTION;
			return true;
		}
		if (input.isKeyDown(keyArray[5])) {
			currentAction = PlayerAction.SECONDARY_ACTION;
			return true;
		}

		if (input.isKeyDown(keyArray[0])) {
			currentAction = PlayerAction.MOVE_NORTH;
			if (input.isKeyDown(keyArray[2])) {
				currentAction = PlayerAction.MOVE_NORTHWEST;
			} else if (input.isKeyDown(keyArray[3])) {
				currentAction = PlayerAction.MOVE_NORTHEAST;
			}
			return true;
		} else if (input.isKeyDown(keyArray[1])) {
			currentAction = PlayerAction.MOVE_SOUTH;
			if (input.isKeyDown(keyArray[2])) {
				currentAction = PlayerAction.MOVE_SOUTHWEST;
			} else if (input.isKeyDown(keyArray[3])) {
				currentAction = PlayerAction.MOVE_SOUTHEAST;
			}
			return true;
		} else if (input.isKeyDown(keyArray[2])) {
			currentAction = PlayerAction.MOVE_WEST;
			return true;
		} else if (input.isKeyDown(keyArray[3])) {
			currentAction = PlayerAction.MOVE_EAST;
			return true;
		}
		return false;
	}

	@Override
	public InputData getData(Input input) {
		if (hasKey(input)) {
			return new InputData(player, currentAction);
		}
		return new InputData(player, PlayerAction.DO_NOTHING);
	}

	@Override
	public InputData getMenuInputData(Input input) {
		PlayerAction menuAction = PlayerAction.DO_NOTHING;
		if (input.isKeyPressed(keyArray[0])) {
			menuAction = PlayerAction.MOVE_NORTH;
		} else if (input.isKeyPressed(keyArray[1])) {
			menuAction = PlayerAction.MOVE_SOUTH;
		} else if (input.isKeyPressed(keyArray[2])) {
			menuAction = PlayerAction.MOVE_WEST;
		} else if (input.isKeyPressed(keyArray[3])) {
			menuAction = PlayerAction.MOVE_EAST;
		} else if (input.isKeyPressed(keyArray[4])) {
			menuAction = PlayerAction.PRIMARY_ACTION;
		}
		if (!menuAction.equals(PlayerAction.DO_NOTHING)) {
			return new InputData(player, menuAction);
		}
		return new InputData(player, PlayerAction.DO_NOTHING);
	}

	@Override
	public Player getPlayer() {
		return player;
	}

	@Override
	public boolean pressedProceed(Input input) {
		int proceedButton = KeyboardControls.getInstance().getProceedButton();

		return input.isKeyPressed(proceedButton);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		KeyBoardInputHandler other = (KeyBoardInputHandler) obj;
		return this.player.equals(other.player)
				&& Arrays.equals(this.keyArray, other.keyArray);
	}

	@Override
	public int hashCode() {
		int sum = 0;

		for (Integer i : keyArray) {
			sum += i * 13;
		}

		sum += currentAction.hashCode() * 7;

		sum += player.hashCode() * 5;

		return sum;
	}
}
