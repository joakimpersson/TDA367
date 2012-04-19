package com.github.joakimpersson.tda367.controller.input;

import com.github.joakimpersson.tda367.model.constants.PlayerAction;
import com.github.joakimpersson.tda367.model.player.Player;

/**
 * A simple data representation for inputdata containing the the player and
 * selected action from the selected input
 * 
 * @author joakimpersson
 * 
 */
public class InputData {
	private Player player;
	private PlayerAction action;

	/**
	 * Creating a new inputdata object
	 * 
	 * @param player
	 *            The player that is going to perform an action
	 * @param action
	 *            The action the player will perform
	 */
	public InputData(Player player, PlayerAction action) {

		this.player = player;
		this.action = action;
	}

	/**
	 * Get the player that is performing an action
	 * 
	 * @return The player that will perform an action
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Get the action that is to be performed
	 * 
	 * @return The action the player will perform
	 */
	public PlayerAction getAction() {
		return action;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		System.out.println(action);
		str.append(player.getName());
		str.append(" ");
		str.append(action.name());

		return str.toString();
	}

}
