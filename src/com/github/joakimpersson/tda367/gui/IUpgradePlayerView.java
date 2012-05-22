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
	 * Render method for the upgrade player view
	 */
	void render(GameContainer container, Graphics g,
			Map<Integer, Integer> playerAttrIndex,
			Map<Integer, Boolean> playerReadyness,
			Map<Integer, Integer> playerCredits,
			Map<Integer, Map<Attribute, Integer>> upgradeMap);
}
