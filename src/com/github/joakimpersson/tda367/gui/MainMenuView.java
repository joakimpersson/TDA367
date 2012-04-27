package com.github.joakimpersson.tda367.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * 
 * @author joakimpersson
 * 
 */
public class MainMenuView implements IView {

	public MainMenuView() {
		init();
	}

	private void init() {

	}

	@Override
	public void enter() {
		
	}
	
	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		// TODO only used during developing
		int posY = 200;
		int posX = container.getWidth() / 2 - 130;
		g.drawString("Bomberman", posX, posY);
		posY += 40;
		g.drawString("Gameplay View (G)", posX, posY);
		posY += 40;
		g.drawString("Highscore View (H)", posX, posY);
		posY += 40;
		g.drawString("Exit Game - yeah right... (Q)", posX, posY);
	}

}