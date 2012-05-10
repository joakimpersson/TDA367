package com.github.joakimpersson.tda367.gui;

import java.util.Map;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.github.joakimpersson.tda367.model.constants.Attribute;
import com.github.joakimpersson.tda367.model.player.Player;

/**
 * 
 * @author joakimpersson
 * 
 */
public class UpgradePlayerPanelView implements IUpgradePlayerView {

	private Player player;
	private final int X;
	private final int Y;
	private int yDelta = -1;
	private Font smlFont = null;

	/**
	 * Creates a new view displaying a attributes that a player can purchase and
	 * also information about his current attributes
	 * 
	 * @param p
	 *            The player to show info about
	 * @param x
	 *            The starting coordinate in the x-axis
	 * @param y
	 *            The starting coordinate in the y-axis
	 */
	public UpgradePlayerPanelView(Player p, int x, int y) {
		this.player = p;
		this.X = x;
		this.Y = y;
		init();
	}

	/**
	 * Responsible for fetching instances ,info from the model and init fonts
	 * etc
	 */
	private void init() {
		try {
			smlFont = GUIParameters.INSTANCE.getSmlFont();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		yDelta = 50;
	}

	@Override
	public void render(GameContainer container, Graphics g,
			Map<Integer, Integer> playerAttrIndex) {
		g.setFont(smlFont);
		int posX = X;
		int posY = Y;

		g.setColor(Color.white);

		String str = formatHeaderString();
		g.drawString(str, posX, posY);

		posY += 25;

		g.drawString("name(lvl), cost", posX, posY);

		int index = 0;
		for (Attribute a : player.getPermanentAttributes()) {
			posY += yDelta;

			if (index == playerAttrIndex.get(player.getIndex())) {
				g.setColor(Color.cyan);
			}
			str = formatAttrString(a);
			g.drawString(str, posX, posY);
			// make sure that is is always white
			g.setColor(Color.white);
			index++;
		}
	}

	/**
	 * Formats a header string for a player containing its name and its credits
	 * to spend
	 * 
	 * @return A string containing the players name and credits
	 */
	private String formatHeaderString() {
		StringBuilder str = new StringBuilder();

		str.append(player.getName() + " (");
		str.append(player.getCredits());
		str.append("$)");

		return str.toString();
	}

	/**
	 * Formats a string containing info about the current attribute and its cost
	 * 
	 * @param a
	 *            The Attribute to draw info about
	 * @return A string containing the attributes name and cost
	 */
	private String formatAttrString(Attribute a) {
		StringBuilder str = new StringBuilder();

		str.append(a.name() + "(");
		str.append(player.getAttribute(a) + ") \t");
		str.append(a.getCost() + "$");
		return str.toString();
	}

}
