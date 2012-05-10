package com.github.joakimpersson.tda367.controller.input;

import org.newdawn.slick.Input;

import com.github.joakimpersson.tda367.controller.input.osdependant.X360OsDep;
import com.github.joakimpersson.tda367.model.constants.PlayerAction;
import com.github.joakimpersson.tda367.model.player.Player;

public class X360InputHandler implements InputHandler {
	public final static int PROCEED_BUTTON = X360OsDep.getProceed();
	public final static int ACTION_BUTTON = X360OsDep.getAction();

	private PlayerAction lastAction = null;
	private Player player = null;
	private int controllerId;
	
	public X360InputHandler(Player player, int controllerId) {
		this.player = player;
		this.controllerId = controllerId;
	}
	
	public X360InputHandler(int controllerId) {
		this.controllerId = controllerId;
	}

	@Override
	public boolean hasKey(Input input) {
		if (input.isButtonPressed(ACTION_BUTTON, controllerId)) {
			lastAction = PlayerAction.ACTION;
			return true;
		}

		if (input.isControllerUp(controllerId)) {
			lastAction = PlayerAction.MOVE_NORTH;
			return true;
		} else if (input.isControllerDown(controllerId)) {
			lastAction = PlayerAction.MOVE_SOUTH;
			return true;
		} else if (input.isControllerLeft(controllerId)) {
			lastAction = PlayerAction.MOVE_WEST;
			return true;
		} else if (input.isControllerRight(controllerId)) {
			lastAction = PlayerAction.MOVE_EAST;
			return true;
		}
		return false;
	}

	@Override
	public InputData getData(Input input) {
		if (hasKey(input)) {
			PlayerAction action = lastAction;

			return new InputData(player, action);
		}
		return null;
	}

	@Override
	public Player getPlayer() {
		return player;
	}

	@Override
	public boolean pressedProceed(Input input) {
		return input.isButtonPressed(PROCEED_BUTTON, controllerId);
	}
}
