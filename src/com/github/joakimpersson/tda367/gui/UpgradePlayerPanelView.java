package com.github.joakimpersson.tda367.gui;

import java.util.Map;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.github.joakimpersson.tda367.model.constants.Attribute;
import com.github.joakimpersson.tda367.model.player.Player;

/**
 * 
 * @author joakimpersson
 *
 */
public class UpgradePlayerPanelView implements IUpgradePlayerView {
	//TODO jocke rethink design
	private Player player;
	private final int X;
	private final int Y;
	private int yDelta = -1;

	public UpgradePlayerPanelView(Player p, int x, int y) {
		this.player = p;
		this.X = x;
		this.Y = y;
		init();
	}

	private void init() {
		yDelta = 50;
	}

	@Override
	public void render(GameContainer container, Graphics g,
			Map<Player, Integer> playerAttrIndex) {

		int posX = X;
		int posY = Y;

		// TODO jocke yeah i know that it is a mess
		g.setColor(Color.white);

		String str = formatHeaderString();
		g.drawString(str, posX, posY);

		posY += 25;

		g.drawString("name(lvl), cost", posX, posY);

		int index = 0;
		for (Attribute a : player.getPermanentAttributes()) {
			posY += yDelta;

			if (index == playerAttrIndex.get(player)) {
				g.setColor(Color.cyan);
			}
			str = formatAttrString(a);
			g.drawString(str, posX, posY);
			// make sure that is is always white
			g.setColor(Color.white);
			index++;
		}
	}

	private String formatHeaderString() {
		StringBuilder str = new StringBuilder();

		str.append(player.getName() + " (");
		str.append(player.getCredits());
		str.append("$)");

		return str.toString();
	}

	private String formatAttrString(Attribute a) {
		StringBuilder str = new StringBuilder();

		str.append(a.name() + "(");
		str.append(player.getAttribute(a) + ") \t");
		str.append(a.getCost() + "$");
		return str.toString();
	}

}
