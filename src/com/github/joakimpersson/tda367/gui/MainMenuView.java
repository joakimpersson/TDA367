package com.github.joakimpersson.tda367.gui;

import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * 
 * @author joakimpersson
 * 
 */
public class MainMenuView implements IView {

	private Font smlFont = null;

	/**
	 * Creates a new view representing the main menu in the game
	 */
	public MainMenuView() {
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

	}

	@Override
	public void enter() {

	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		// TODO only used during developing
		g.setFont(smlFont);
		int posY = 200;
		int posX = container.getWidth() / 2 - 130;
		g.drawString("Bomberman", posX, posY);
		posY += 40;
		g.drawString("Start game (G)", posX, posY);
		posY += 40;
		g.drawString("Highscore View (H)", posX, posY);
		posY += 40;
		g.drawString("Exit Game - yeah right... (Q)", posX, posY);
	}

}