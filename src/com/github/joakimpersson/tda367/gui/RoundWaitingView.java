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
public class RoundWaitingView implements IView {
	private static final int WIDTH = 600;
	private static final int HEIGHT = 100;
	private Font bigFont = null;
	private int startX;
	private int startY;

	/**
	 * Creates a new view that shows info when the game is waiting for input
	 */
	public RoundWaitingView() {
		init();
	}

	/**
	 * Responsible for fetching instances ,info from the model and init fonts a
	 */
	private void init() {
		try {
			bigFont = GUIUtils.getBigFont();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void enter() {
		startX = GUIUtils.getGameWidth() / 2 - WIDTH / 2;
		startY = GUIUtils.getGameHeight() / 2 - HEIGHT / 2;
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		g.setColor(Color.white);
		g.setFont(bigFont);
		drawBackgroundContainer(g);
		drawText(g);
	}

	/**
	 * Draws the info text to the screen
	 * 
	 * @param g
	 *            The graphics context to render to
	 */
	private void drawText(Graphics g) {
		String str = "Press Proceed to start the game";
		int x = GUIUtils.getStrinCenterX(str, WIDTH, g) + startX;
		int y = GUIUtils.getStrinCenterY(str, HEIGHT, g) + startY;
		g.drawString(str, x, y);
	}

	/**
	 * Draws the background container to the game
	 * 
	 * @param g
	 *            The graphics context to render to
	 */
	private void drawBackgroundContainer(Graphics g) {
		g.setColor(Color.black);
		g.fillRoundRect(startX, startY, WIDTH, HEIGHT, 25);
		g.setColor(Color.white);
		g.drawRoundRect(startX, startY, WIDTH, HEIGHT, 25);
	}

}
