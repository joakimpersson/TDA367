package com.github.joakimpersson.tda367.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.github.joakimpersson.tda367.model.player.Player;

public class PlayerInfoView implements IView {

	private Player player;
	private int startX;
	private int startY;
	private int width;
	private int height;

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

	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		g.setColor(Color.black);
		g.drawRect(startX, startY, width, height);
		g.setColor(Color.white);
		g.drawString(player.getName(), startX + 20, startY + 20);
	}
}
