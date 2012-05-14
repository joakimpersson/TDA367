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
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}

	public void render(GameContainer container, Graphics g, int selection)
			throws SlickException {
		String title = "Bomberman";
		int posY = 200;
		int posX = GUIUtils.getStrinCenterX(title, WIDTH, g);
		g.setFont(smlFont);
		g.setColor(Color.green);
		System.out.println(posX);
		g.drawString("Bomberman", posX, posY);
		posY += 40;
		int i = 1;
		while (i < 4) {
			if (selection == i) {
				g.setColor(Color.cyan);
			} else {
				g.setColor(Color.gray);
			}

			if (i == 1) {
				g.drawString("Start game", posX, posY);
				posY += 40;
			} else if (i == 2) {
				g.drawString("Highscore View", posX, posY);
				posY += 40;
			} else if (i == 3) {
				g.drawString("Exit Game - yeah right...", posX, posY);
			}
			i++;
		}
	}

}