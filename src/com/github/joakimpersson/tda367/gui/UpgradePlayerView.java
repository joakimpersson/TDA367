package com.github.joakimpersson.tda367.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.github.joakimpersson.tda367.model.BombermanModel;
import com.github.joakimpersson.tda367.model.IBombermanModel;
import com.github.joakimpersson.tda367.model.player.Player;

/**
 * 
 * @author joakimpersson
 * 
 */
public class UpgradePlayerView implements IUpgradePlayerView {

	private IBombermanModel model = null;
	private List<Player> players = null;
	private List<UpgradePlayerPanelView> playerViews = null;
	private static final int POS_Y = 50;
	private static final int POS_X = 125;

	public UpgradePlayerView() {
		init();
	}

	private void init() {
		model = BombermanModel.getInstance();
		players = model.getPlayers();
		playerViews = new ArrayList<UpgradePlayerPanelView>();

		int xDelta = GUIParameters.INSTANCE.getGameWidth() / players.size();
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
			Map<Player, Integer> playerAttrIndex) {

		for (UpgradePlayerPanelView view : playerViews) {
			view.render(container, g, playerAttrIndex);
		}
	}

}
