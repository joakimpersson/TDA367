package com.github.joakimpersson.tda367.model;

import java.util.List;

import com.github.joakimpersson.tda367.model.constants.Attribute;
import com.github.joakimpersson.tda367.model.constants.PlayerAction;
import com.github.joakimpersson.tda367.model.constants.ResetType;
import com.github.joakimpersson.tda367.model.player.Player;
import com.github.joakimpersson.tda367.model.tiles.Tile;

/**
 * An interface modeling the bomberman model that the main controller will use
 * to communicate with the rest of the model
 * 
 * @author joakimpersson
 * @modified Viktor Anderling
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
	 * 
	 * Upgrades a players attribute
	 * 
	 * @param player
	 *            The player to recceive an attribute upgrade
	 * @param attr
	 *            The attribute to be upgraded or added
	 */
	public void upgradePlayer(Player player, Attribute attr);

	/**
	 * Returns a list of the current active players in the game
	 * 
	 * @return A list of players
	 */
	public List<Player> getPlayers();

	/**
	 * Returns a copy of the matrix representing the game map
	 * 
	 * @return The game map matrix
	 */
	public Tile[][] getMap();

	/**
	 * Returns true or false depending if one of the players are dead or not
	 * 
	 * @return True if one player is dead false otherwise
	 */
	// TODO jocke only to test the game
	public boolean isPlayersAlive();

	/**
	 * Reset the models state with different kind of level to represent or three
	 * main game states
	 * 
	 * Game - Means that the game is over
	 * 
	 * Match - Enables players to buy upgrades
	 * 
	 * Round - Starts another round
	 * 
	 * @param type
	 *            What type of model reset
	 */
	public void reset(ResetType type);

	public void startGame();

	public void endGame();

}
