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
		int x = startX;
		int y = startY;
		int xDisp = 0;
		int yDisp = 0;
		
		// draw black frame
		g.setColor(Color.black);
		g.drawRect(x, y, width, height);

		// draw image of player
//		drawImage(x+7, y+13, "player/"+player.getIndex()+"/still-east", g);
		drawImage(x+7, y+13, player.getImage(), g);
		
		// draw number of wins
		yDisp = 15;
		for (int i=0; i<player.getRoundsWon()+2; i++) {
			drawImage(x+40, y+yDisp, "info/chevron", g);
			yDisp += 4;
		}
		
		// set up text
		g.setFont(smlFont);
		g.setColor(Color.white);
		
		// draw player name
		g.drawString(player.getName(), x+60, y+15);
		
		// draw pretty hearts
		if (player.isAlive()) {
			drawHearts(x+59, y+32, g);
		} else {
			drawImage(x+59, y+32, "info/skull", g);
		}
		
		// draw number of wins
		xDisp = 59;
		for (int i=0; i<player.getRoundsWon()+2; i++) {
			drawImage(x+xDisp, y+55, "info/star", g);
			xDisp += 13;
		}

		// draw score
		int score = player.getScore();
		g.drawString("Score   " + score + "p", x+15, y+70);
		
		// draw powerups
		xDisp = 145;
		int textDisp = xDisp+40;
		g.setColor(Color.lightGray);
		
		// --speed--
		yDisp = 0;
		int pSpeed = player.getAttribute(Attribute.Speed);
		drawImage(x+xDisp, y+yDisp, "info/speed", g);
		drawAttributeValue(pSpeed, x+textDisp, y+yDisp+10, g);
		
		// --range and power--
		yDisp += 30;
		int pRange = player.getAttribute(Attribute.BombRange);
		int pPower = player.getAttribute(Attribute.BombPower);
		drawImage(x+xDisp, y+yDisp, "info/fire", g);
		drawAttributeValue(pRange, x+textDisp, y+yDisp+10, g);
		if (pPower > 1) {
			if (pPower < 3) {
				drawImage(x+xDisp+16, y+yDisp, "info/power2", g);
			} else {
				drawImage(x+xDisp+16, y+yDisp, "info/power3", g);
			}
		}
		
		// --bombs--
		yDisp += 30;
		int pBombs = player.getAttribute(Attribute.BombStack);
		drawImage(x+xDisp, y+yDisp, "info/bomb", g);
		drawAttributeValue(pBombs, x+textDisp, y+yDisp+10, g);
	}

	private void drawAttributeValue(int i, float x, float y,
			Graphics g) {
//		System.out.println(Integer.decode(string));
		if (i >= 10) {
			g.drawString(""+i, x-5F, y);
		} else {
			g.drawString(""+i, x, y);
		}
	}

	private void drawHearts(float x, float y, Graphics g) {
		int xDelta;
		int health = player.getHealth();
		if (health < 4) {
			xDelta = 27;
		} else {
			xDelta = (int)Math.floor(63/health);
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