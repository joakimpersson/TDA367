package com.github.joakimpersson.tda367.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.github.joakimpersson.tda367.gui.guiutils.GUIUtils;

/**
 * 
 * @author joakimpersson
 * 
 */
public class MainMenuView {

	private Font smlFont = null;
	private Font bigFont = null;
	private final int WIDTH;

	/**
	 * Creates a new view representing the main menu in the game
	 */
	public MainMenuView() {
		init();
		WIDTH = GUIUtils.getGameWidth();
	}

	/**
	 * Responsible for fetching instances ,info from the model and init fonts
	 * etc
	 */
	private void init() {
		try {
			smlFont = GUIUtils.getSmlFont();
			bigFont = GUIUtils.getBigFont();
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}

	public void render(GameContainer container, Graphics g, int selection)
			throws SlickException {
		int posX = 0;
		int posY = 140;
		drawTitle(posX, posY, g);
		posY += 80;
		drawMenu(posX, posY, selection, g);
	}

	private void drawTitle(int x, int y, Graphics g) {
		String title = "Bomberman";
		g.setFont(bigFont);
		g.setColor(Color.cyan);
		x += GUIUtils.getStrinCenterX(title, WIDTH, g);
		g.drawString(title, x, y);

	}

	private void drawMenu(int x, int y, int selection, Graphics g) {
		g.setColor(Color.white);
		g.setFont(smlFont);

		int i = 1;
		while (i < 4) {
			if (selection == i) {
				g.setColor(Color.cyan);
			} else {
				g.setColor(Color.gray);
			}
			
			if (i == 1) {
				drawMenuItem("Start Game", x, y, g);
				y += 40;
			} else if (i == 2) {
				drawMenuItem("Highscore View", x, y, g);
				y += 40;
			} else if (i == 3) {
				drawMenuItem("Exit Game", x, y, g);
				y += 40;
			}
			i++;
		}

	}

	private void drawMenuItem(String str, int x, int y, Graphics g) {
		int posX = x + GUIUtils.getStrinCenterX(str, WIDTH, g);
		g.drawString(str, posX, y);

	}
}