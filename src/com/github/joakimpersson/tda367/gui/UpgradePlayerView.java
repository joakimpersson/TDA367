package com.github.joakimpersson.tda367.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.github.joakimpersson.tda367.gui.guiutils.GUIUtils;
import com.github.joakimpersson.tda367.model.PyromaniacModel;
import com.github.joakimpersson.tda367.model.IPyromaniacModel;
import com.github.joakimpersson.tda367.model.constants.Attribute;
import com.github.joakimpersson.tda367.model.player.Player;

/**
 * 
 * @author joakimpersson
 * 
 */
public class UpgradePlayerView implements IUpgradePlayerView {

	private IPyromaniacModel model = null;
	private List<Player> players = null;
	private List<UpgradePlayerPanelView> playerViews = null;
	private static final int POS_Y = 50;
	private static final int POS_X = 50;

	/**
	 * Creats a new view that holds the info about the upgradeplayerview
	 * subpanels
	 */
	public UpgradePlayerView() {
		init();
	}

	/**
	 * Responsible for fetching instances ,info from the model and init fonts
	 * etc
	 */
	private void init() {
		model = PyromaniacModel.getInstance();
	}

	public void enter() {
		players = model.getPlayers();
		playerViews = new ArrayList<UpgradePlayerPanelView>();
		int xDelta = GUIUtils.getGameWidth() / players.size();
		int x = POS_X;

		for (Player p : players) {
			UpgradePlayerPanelView view = new UpgradePlayerPanelView(p, x,
					POS_Y);

			playerViews.add(view);
			x += xDelta;
		}

	}

	@Override
	public void render(GameContainer container, Graphics g,
			Map<Integer, Integer> playerAttrIndex,
			Map<Integer, Boolean> playerReadyness,
			Map<Integer, Integer> playerCredits,
			Map<Integer, Map<Attribute, Integer>> upgradeMap) {
		try {
			g.setFont(GUIUtils.getBigFont());
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		g.setColor(Color.yellow);
		String str = "Use left and right to purchase upgrades.";
		g.drawString(str, GUIUtils.getStringCenterX(str, GUIUtils.getGameWidth(), g), GUIUtils.getGameHeight()-100);
		str = "Press action when ready!";
		g.drawString(str, GUIUtils.getStringCenterX(str, GUIUtils.getGameWidth(), g), GUIUtils.getGameHeight()-65);
		
		for (UpgradePlayerPanelView view : playerViews) {
			view.render(container, g, playerAttrIndex, playerReadyness, playerCredits, upgradeMap);
		}
	}

}
