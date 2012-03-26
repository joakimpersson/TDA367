package com.github.joakimpersson.tda367.core;

import java.util.List;

public interface IBombermanModel {
	//TODO add documentation
	public void updateGame(Player player, PlayerAction action);
	public void startGame();
	public void endGame();
	public void upgradePlayer(Player player, Attribute attr);
	public List<Player>getPlayers();
}
