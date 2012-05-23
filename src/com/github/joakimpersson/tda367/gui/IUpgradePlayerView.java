package com.github.joakimpersson.tda367.gui;

import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.github.joakimpersson.tda367.model.constants.Attribute;

/**
 * Interface for the UpgradePlayerView class
 * 
 * @author rekoil
 * 
 */
public interface IUpgradePlayerView {
	/**
	 * Render method for the upgrade player view, the first integer in each of
	 * the maps is representative of the player with this index
	 * 
	 * @param container
	 *            The container for the game
	 * @param g
	 *            The g context to draw with
	 * @param playerAttrIndex
	 *            The indexes of the players
	 * @param playerReadyness
	 *            The status of the different players
	 * @param playerCredits
	 *            The credits for the players
	 * @param upgradeMap
	 *            The current status for an attribute upgrade
	 */
	void render(GameContainer container, Graphics g,
			Map<Integer, Integer> playerAttrIndex,
			Map<Integer, Boolean> playerReadyness,
			Map<Integer, Integer> playerCredits,
			Map<Integer, Map<Attribute, Integer>> upgradeMap);
}
