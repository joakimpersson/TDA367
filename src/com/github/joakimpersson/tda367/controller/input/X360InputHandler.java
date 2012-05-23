package com.github.joakimpersson.tda367.controller.input;

import org.newdawn.slick.Input;

import com.github.joakimpersson.tda367.controller.input.osdependant.X360OsDep;
import com.github.joakimpersson.tda367.model.constants.PlayerAction;
import com.github.joakimpersson.tda367.model.player.Player;

/**
 * InputHandler for Xbox 360 controllers
 * 
 * @author rekoil
 * 
 */
public class X360InputHandler implements InputHandler {
	public final static int PROCEED_BUTTON = X360OsDep.getProceed();
	public final static int PRIMARY_ACTION = X360OsDep.getPrimaryAction();
	public final static int SECONDARY_ACTION = X360OsDep.getSecondaryAction();

	private PlayerAction currentAction = null;
	private Player player = null;
	private int controllerId;

	/**
	 * Constructor for the X360InputHandler class
	 * 
	 * @param player
	 *            The player the controller who owns this InputHandler
	 * @param controllerId
	 *            Which index Xbox 360 controller it pertains
	 */
	public X360InputHandler(Player player, int controllerId) {
		this.player = player;
		this.controllerId = controllerId;
	}

	/**
	 * Constructor for the X360InputHandler class
	 * 
	 * @param controllerId
	 *            Which index Xbox 360 controller it pertainsì
	 */
	public X360InputHandler(int controllerId) {
		this.controllerId = controllerId;
	}

	@Override
	public boolean hasKey(Input input) {
		if (input.isButtonPressed(PRIMARY_ACTION, controllerId)) {
			currentAction = PlayerAction.PRIMARY_ACTION;
			return true;
		}
		if (input.isButtonPressed(SECONDARY_ACTION, controllerId)) {
			currentAction = PlayerAction.SECONDARY_ACTION;
			return true;
		}

		if (input.isControllerUp(controllerId)) {
			currentAction = PlayerAction.MOVE_NORTH;
			if (input.isControllerLeft(controllerId))
				currentAction = PlayerAction.MOVE_NORTHWEST;
			else if (input.isControllerRight(controllerId))
				currentAction = PlayerAction.MOVE_NORTHEAST;
			return true;
		} else if (input.isControllerDown(controllerId)) {
			currentAction = PlayerAction.MOVE_SOUTH;
			if (input.isControllerLeft(controllerId))
				currentAction = PlayerAction.MOVE_SOUTHWEST;
			else if (input.isControllerRight(controllerId))
				currentAction = PlayerAction.MOVE_SOUTHEAST;
			return true;
		} else if (input.isControllerLeft(controllerId)) {
			currentAction = PlayerAction.MOVE_WEST;
			return true;
		} else if (input.isControllerRight(controllerId)) {
			currentAction = PlayerAction.MOVE_EAST;
			return true;
		}
		return false;
	}

	@Override
	public InputData getData(Input input) {
		if (hasKey(input)) {
			PlayerAction action = currentAction;

			return new InputData(player, action);
		}
		return new InputData(player, PlayerAction.DO_NOTHING);
	}

	@Override
	public Player getPlayer() {
		return player;
	}

	@Override
	public boolean pressedProceed(Input input) {
		return input.isButtonPressed(PROCEED_BUTTON, controllerId);
	}

	@Override
	public InputData getMenuInputData(Input input) {
		/**
		 * The +4 is because "isControlPressed" and "isButtonPressed" 
		 * have different indexes. (all platforms)
		 **/
		if (input.isControlPressed(PRIMARY_ACTION + 4, controllerId)) {
			return new InputData(player, PlayerAction.PRIMARY_ACTION);
		}
		String buttonPressed = X360OsDep.getDPadButtonPressed(input,
				controllerId);
		if (!buttonPressed.equals("none")) {
			if (buttonPressed.equals("up"))
				return new InputData(player, PlayerAction.MOVE_NORTH);
			else if (buttonPressed.equals("down"))
				return new InputData(player, PlayerAction.MOVE_SOUTH);
			else if (buttonPressed.equals("left"))
				return new InputData(player, PlayerAction.MOVE_WEST);
			else if (buttonPressed.equals("right"))
				return new InputData(player, PlayerAction.MOVE_EAST);
		}
		return new InputData(player, PlayerAction.DO_NOTHING);
	}
}
