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
	// TODO add documentation
	
	public void updateGame(Player player, PlayerAction action);

	public void startGame();

	public void endGame();

	public void upgradePlayer(Player player, Attribute attr);

	public List<Player> getPlayers();
	
	public int getTileToughness(int x, int y);
	
}
