package com.github.joakimpersson.tda367.gui;

import java.util.List;
import java.util.Map;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.github.joakimpersson.tda367.model.BombermanModel;
import com.github.joakimpersson.tda367.model.IBombermanModel;
import com.github.joakimpersson.tda367.model.constants.Attribute;
import com.github.joakimpersson.tda367.model.player.Player;

/**
 * 
 * @author joakimpersson
 * 
 */
public class UpgradePlayerView implements IUpgradePlayerView {

	private IBombermanModel model = null;
	private List<Player> players = null;

	public UpgradePlayerView() {
		init();
	}

	private void init() {
		model = BombermanModel.getInstance();
		players = model.getPlayers();
	}

	@Override
	public void render(GameContainer container, Graphics g,
			Map<Player, Integer> playerAttrIndex) {
		int posY = 50;
		int posX = 125;
		int yDelta = 50;
		int playerXDelta = container.getWidth() / players.size();
		g.setColor(Color.white);
		for (Player p : players) {
			int attrIndex = playerAttrIndex.get(p);
			drawPlayerStats(p, attrIndex, posX, posY, yDelta, g);
			posX += playerXDelta;
		}
	}

	// TODO jocke yeah i know that it is a mess
	private void drawPlayerStats(Player p, int attrIndex, int posX, int posY,
			int yDelta, Graphics g) {

		g.drawString(p.getName() + " (" + p.getCredits() + "$)", posX, posY);

		posY += 25;

		g.drawString("name(lvl), cost", posX, posY);

		int index = 0;
		for (Attribute a : p.getPermantAttributes()) {
			posY += yDelta;

			if (index == attrIndex) {
				g.setColor(Color.cyan);
			}
			g.drawString(
					a.name() + "(" + p.getAttribute(a) + ") \t" + a.getCost()
							+ "$", posX, posY);
			// make sure that is is always white
			g.setColor(Color.white);
			index++;
		}
	}
}
