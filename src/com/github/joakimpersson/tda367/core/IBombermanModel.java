package com.github.joakimpersson.tda367.core;

import java.util.List;

/**
 * An interface modeling the bomberman model that the main controller will use
 * to communicate with the rest of the model
 * 
 * @author joakimpersson & Viktor Anderling
 * 
 */
public interface IBombermanModel {

	/**
	 * Responisble for updating the model with player movements and actions
	 * 
	 * @param player
	 *            The player responsible for the action
	 * @param action
	 *            The action performed by the current player
	 */
	public void updateGame(Player player, PlayerAction action);

	/**
	 * After every match it is possible to upgrade a player's attribute
	 * 
	 * @param player
	 *            The player to recceive an attribute upgrade
	 * @param attr
	 *            The attribute to be upgraded or added
	 */
	public void upgradePlayer(Player player, Attribute attr);

	/**
	 * Returns a list of the current active players in the game
	 * @return A list of players
	 */
	public List<Player> getPlayers();

	public void startGame();

	public void endGame();

	public int getTileToughness(Position pos);

}
