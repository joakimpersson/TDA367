package com.github.joakimpersson.tda367.controller.input;

import org.newdawn.slick.Input;

import com.github.joakimpersson.tda367.model.constants.PlayerAction;
import com.github.joakimpersson.tda367.model.player.Player;

public class X360InputHandler implements InputHandler {
	private final int PROCEED_BUTTON = getProceed();
	private InputData proceedKey;

	private PlayerAction lastAction = null;
	private Player player;
	private int controllerId;
	
	public X360InputHandler(Player player, int controllerId) {
		this.player = player;
		this.controllerId = controllerId;
		this.proceedKey = new InputData(player, PlayerAction.Action);
	}

	public static int getProceed() {
		if (System.getProperty("os.name").toLowerCase().indexOf("mac") >= 0) {
			return 11;
		}
		return 1;
	}

	@Override
	public boolean hasKey(Input input) {
		if (input.isButtonPressed(PROCEED_BUTTON, controllerId)) {
			lastAction = PlayerAction.Action;
			return true;
		}

		if (input.isControllerUp(controllerId)) {
			lastAction = PlayerAction.MoveUp;
			return true;
		} else if (input.isControllerDown(controllerId)) {
			lastAction = PlayerAction.MoveDown;
			return true;
		} else if (input.isControllerLeft(controllerId)) {
			lastAction = PlayerAction.MoveLeft;
			return true;
		} else if (input.isControllerRight(controllerId)) {
			lastAction = PlayerAction.MoveRight;
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
		return getData(input).equals(proceedKey);
	}
}
