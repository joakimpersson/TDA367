package com.github.joakimpersson.tda367.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.github.joakimpersson.tda367.gui.guiutils.GUIUtils;
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
	private Font smlFont = null;
	private ImageLoader imgs;

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
		this.imgs = ImageLoader.getInstance();
		try {
			smlFont = GUIUtils.getSmlFont();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void enter() {
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		int xPos = startX;
		int yPos = startY;
		int x = 0;
		int y = 0;

		// draw black frame
		g.setColor(Color.black);
		g.drawRect(xPos, yPos, width, height);

		// draw image of player
		// drawImage(x+7, y+13, "player/"+player.getIndex()+"/still-east", g);
		drawImage(xPos + 7, yPos + 13, player.getImage(), g);

		// draw number of wins
		y = 15;
		for (int i = 0; i < player.getRoundsWon(); i++) {
			drawImage(xPos + 40, yPos + y, "info/chevron", g);
			y += 4;
		}

		// set up text
		g.setFont(smlFont);
		g.setColor(Color.white);

		// draw player name
		g.drawString(player.getName(), xPos + 60, yPos + 15);

		// draw pretty hearts
		if (player.isAlive()) {
			drawHearts(xPos + 59, yPos + 32, g);
		} else {
			drawImage(xPos + 59, yPos + 32, "info/skull", g);
		}

		// draw number of wins
		x = 59;
		for (int i = 0; i < player.getMatchesWon(); i++) {
			drawImage(xPos + x, yPos + 55, "info/star", g);
			x += 13;
		}

		int areaBombsAvailable = player.getAreaBombsAvailable();
		// draw score
		int score = player.getScore();
		int scoreLength = 11;
		if (areaBombsAvailable > 0) {
			scoreLength = 8;
		}
		String zeros = leadingZeroes(score, scoreLength);
		int scoreDisp = g.getFont().getWidth(zeros);
		g.setColor(Color.darkGray);
		g.drawString(zeros, xPos + 15, yPos + 70);
		g.setColor(Color.white);
		g.drawString("" + score, xPos + scoreDisp + 17, yPos + 70);

		// draw powerups
		x = 145;
		int textDisp = x + 40;
		g.setColor(Color.lightGray);

		// --speed--
		y = 0;
		int pSpeed = player.getAttribute(Attribute.Speed);
		drawImage(xPos + x, yPos + y, "info/speed", g);
		drawAttributeValue(pSpeed, xPos + textDisp, yPos + y + 10, g);

		// --range and power--
		y += 30;
		int pRange = player.getAttribute(Attribute.BombRange);
		int pPower = player.getAttribute(Attribute.BombPower);
		drawImage(xPos + x, yPos + y, "info/fire", g);
		drawAttributeValue(pRange, xPos + textDisp, yPos + y + 10, g);
		if (pPower > 1) {
			if (pPower < 3) {
				drawImage(xPos + x + 16, yPos + y, "info/power2", g);
			} else {
				drawImage(xPos + x + 16, yPos + y, "info/power3", g);
			}
		}

		// --bombs--
		y += 30;
		int pBombs = player.getAttribute(Attribute.BombStack);
		drawImage(xPos + x, yPos + y, "info/bomb", g);
		drawAttributeValue(pBombs, xPos + textDisp, yPos + y + 10, g);

		// --area bombs, if available--
		if (areaBombsAvailable > 0) {
			drawImage(xPos + x - 35, yPos + y, "info/bomb-area", g);
			g.setColor(Color.white);
			drawAttributeValue(areaBombsAvailable, xPos + x - 25, yPos
					+ y + 10, g);
		}
	}

	private String leadingZeroes(int score, int zeros) {
		String out = "";
		for (int i = 0; i < zeros - String.valueOf(score).length(); i++) {
			out = out + 0;
		}
		return out;
	}

	private void drawAttributeValue(int i, float x, float y, Graphics g) {
		if (i >= 10) {
			g.drawString("" + i, x - 5F, y);
		} else {
			g.drawString("" + i, x, y);
		}
	}

	private void drawHearts(float x, float y, Graphics g) {
		int xDelta;
		int health = player.getHealth();
		if (health < 4) {
			xDelta = 27;
		} else {
			xDelta = (int) Math.floor(63 / health);
		}
		for (int i = 0; i < health; i++) {
			drawImage(x, y, "info/heart", g);
			x += xDelta;
		}
	}

	private void drawImage(float x, float y, String s, Graphics g) {
		// the players position is related to matrix so compensated is needed
		Image i = imgs.getImage(s);
		g.drawImage(i, x, y);
	}
}