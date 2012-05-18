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
	private ImageLoader imgs = null;

	/**
	 * Creates a new view representing the main menu in the game
	 */
	public MainMenuView() {
		init();
		WIDTH = GUIUtils.getGameWidth();
	}

	/**
	 * Responsible for fetching instances, info from the model and init fonts
	 * etc
	 */
	private void init() {
		try {
			smlFont = GUIUtils.getSmlFont();
			bigFont = GUIUtils.getBigFont();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		imgs  = ImageLoader.getInstance();
	}

	public void render(GameContainer container, Graphics g, int selection)
			throws SlickException {
		g.drawImage(imgs.getImage("bg"), 0, 0);
		int posY = 140;
		drawTitle(posY, g);
		posY += 80;
		drawMenu(posY, selection, g);
	}

	private void drawTitle(int y, Graphics g) {
		String title = "Pyromaniacs";
		g.setFont(bigFont);
		g.setColor(Color.cyan);
		g.drawString(title, GUIUtils.getStringCenterX(title, WIDTH, g), y);
	}

	private void drawMenu(int y, int selection, Graphics g) {
		g.setColor(Color.white);
		g.setFont(smlFont);

		for (int i = 0; i < 4; i++) {
			if (selection == i) {
				g.setColor(Color.cyan);
			} else {
				g.setColor(Color.gray);
			}
			
			if (i == 1) {
				drawMenuItem("Start Game", y, g);
				y += 40;
			} else if (i == 2) {
				drawMenuItem("Highscore View", y, g);
				y += 40;
			} else if (i == 3) {
				drawMenuItem("Exit Game", y, g);
				y += 40;
			}
		}
	}

	private void drawMenuItem(String str, int y, Graphics g) {
		g.drawString(str, GUIUtils.getStringCenterX(str, WIDTH, g), y);

	}
}