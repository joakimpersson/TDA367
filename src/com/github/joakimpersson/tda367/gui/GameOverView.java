package com.github.joakimpersson.tda367.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.github.joakimpersson.tda367.gui.guiutils.GUIUtils;

/**
 * A class representing the GameOverView
 * 
 * @author joakimpersson
 * @modified adderollen
 * 
 */
public class GameOverView implements IView {

	private Font bigFont = null;
	private GameOverWinnerView gameOverWinnerView = null;
	private GameOverLosersView gameOverLosersView = null;

	public GameOverView() {
		gameOverWinnerView = new GameOverWinnerView(155, 100);
		gameOverLosersView = new GameOverLosersView(155, 350);
		init();
	}

	private void init() {
		try {
			bigFont = GUIUtils.getBigFont();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void enter() {
		gameOverWinnerView.enter();
		gameOverLosersView.enter();
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		drawHeader(g);
		gameOverWinnerView.render(container, g);
		gameOverLosersView.render(container, g);
	}

	private void drawHeader(Graphics g) {
		g.setColor(Color.white);
		g.setFont(bigFont);
		int panelWidth = GUIUtils.getGameWidth();
		int y = 25;
		String str = "Game Over";
		int strWidth = g.getFont().getWidth(str);
		int x = panelWidth / 2 - strWidth / 2;
		g.drawString(str, x, y);
	}

}
