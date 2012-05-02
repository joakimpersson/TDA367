package com.github.joakimpersson.tda367.gui;

import java.util.List;

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
public class PlayerInfoView implements IView {

	private Player player;
	private int startX;
	private int startY;
	private int width;
	private int height;
	private int yDelta = -1;
	private Font smlFont = null;
	private static final int X = 40;

	public PlayerInfoView(Player player, int startX, int startY, int width,
			int height) {
		this.player = player;
		this.startX = startX;
		this.startY = startY;
		this.width = width;
		this.height = height;

		init();
	}

	private void init() {
		try {
			smlFont = GUIParameters.INSTANCE.getSmlFont();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		yDelta = height / 10;
	}

	@Override
	public void enter() {
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		int y = startY;

		g.setFont(smlFont);
		g.setColor(Color.black);
		g.drawRect(startX, y, width, height);

		y += yDelta;

		g.setColor(Color.white);
		g.drawString(player.getName(), X, y);

		int score = player.getScore();

		y += yDelta;

		g.drawString("Score: " + score + "p", X, y);

		y += yDelta;

		int hp = player.getHealth();

		g.drawString("Lives: " + hp, X, y);

		y += yDelta;

		// TODO jocke only during dev
		List<Attribute> playerAttrs = player.getPermanentAttributes();

		for (Attribute a : playerAttrs) {
			StringBuffer str = new StringBuffer();
			str.append(a.getName());
			str.append(": ");
			str.append(player.getAttribute(a));
			g.drawString(str.toString(), X, y);
			y += yDelta;
		}

		// String availableBombsStr = "Nbr Of Bombs: "
		// + player.getBombsAvailable();
		// g.drawString(availableBombsStr, X, y);

	}
}