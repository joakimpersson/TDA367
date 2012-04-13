package com.github.joakimpersson.tda367.gui;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.github.joakimpersson.tda367.model.BombermanModel;
import com.github.joakimpersson.tda367.model.IBombermanModel;
import com.github.joakimpersson.tda367.model.constants.Attribute;
import com.github.joakimpersson.tda367.model.player.Player;
import com.github.joakimpersson.tda367.model.player.PlayerAttributes;

/**
 * 
 * @author joakimpersson
 * 
 */
public class UpgradePlayerView implements IView {

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
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		int posY = 50;
		int posX = 125;
		int yDelta = 50;
		int playerXDelta = container.getWidth() / players.size();

		for (Player p : players) {
			drawPlayerStats(p, posX, posY, yDelta, g);
			posX += playerXDelta;
		}
	}

	private void drawPlayerStats(Player p, int posX, int posY, int yDelta,
			Graphics g) {
		PlayerAttributes attr = p.getAttr();

		g.drawString(p.getName(), posX, posY);

		posY += 25;

		g.drawString("name(lvl), cost", posX, posY);

		Set<Map.Entry<Attribute, Integer>> entries = attr.getMatchAttrs()
				.entrySet();
		Iterator<Map.Entry<Attribute, Integer>> iter = entries.iterator();
		while (iter.hasNext()) {
			posY += yDelta;
			Map.Entry<Attribute, Integer> entry = iter.next();
			Attribute a = entry.getKey();
			Integer value = entry.getValue();
			g.drawString(a.name() + "(" + value + ") \t" + a.getCost() + "p",
					posX, posY);

		}
	}

}
