package com.github.joakimpersson.tda367.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
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
			smlFont = GUIParameters.INSTANCE.getSmlFont();
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
		
		// draw black frame
		g.setColor(Color.black);
		g.drawRect(x, y, width, height);

		// draw image of player
//		drawImage(x+7F, y+13F, "player/"+player.getIndex()+"/still-east", g);
		drawImage(x+7F, y+13F, player.getImageString(), g);
		
		// set up text
		g.setFont(smlFont);
		g.setColor(Color.white);
		
		// draw player name
		g.drawString(player.getName(), x+60F, y+15F);
		
		// draw pretty hearts
		if (player.isAlive()) {
			drawHearts(x+59F, y+35F, g);
		} else {
			drawImage(x+59F, y+35F, "info/skull", g);
		}

		// draw score
		int score = player.getScore();
		g.drawString("Score   " + score + "p", x+15F, y+70F);
		
		// draw powerups
		float xDisp = 145F;
		float textDisp = xDisp+40F;
		g.setColor(Color.lightGray);
		
		// --speed--
		float yDisp = 0F;
		int pSpeed = player.getAttribute(Attribute.Speed);
		drawImage(x+xDisp, y+yDisp, "info/speed", g);
		g.drawString(""+pSpeed, x+textDisp, y+yDisp+10F);
		
		// --range and power--
		yDisp += 30F;
		int pRange = player.getAttribute(Attribute.BombRange);
		int pPower = player.getAttribute(Attribute.BombPower);
		drawImage(x+xDisp, y+yDisp, "info/fire", g);
		g.drawString(""+pRange, x+textDisp, y+yDisp+10F);
		if (pPower > 1) {
			if (pPower < 3) {
				drawImage(x+xDisp+16F, y+yDisp, "info/power2", g);
			} else {
				drawImage(x+xDisp+16F, y+yDisp, "info/power3", g);
			}
		}
		
		// --bombs--
		yDisp += 30F;
		int pBombs = player.getAttribute(Attribute.BombStack);
		drawImage(x+xDisp, y+yDisp, "info/bomb", g);
		g.drawString(""+pBombs, x+textDisp, y+yDisp+10F);
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