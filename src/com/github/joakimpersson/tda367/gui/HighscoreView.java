package com.github.joakimpersson.tda367.gui;

import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.github.joakimpersson.tda367.gui.guiutils.GUIUtils;

/**
 * A view for displaying the highscores in the game
 * 
 * @author joakimpersson
 * 
 */
public class HighscoreView implements INavigateableView {

	private HighscoreListView highscoreListView = null;
	private HighscoreInfoView highscoreInfoView = null;
	private Font bigFont = null;

	/**
	 * Create a new instance of the Highscore View
	 */
	public HighscoreView() {
		init();
	}

	/**
	 * Initialize the view
	 */
	private void init() {
		highscoreListView = new HighscoreListView(50, 100);
		highscoreInfoView = new HighscoreInfoView(475, 100);
		try {
			bigFont = GUIUtils.getBigFont();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void enter() {
		highscoreInfoView.enter();
		highscoreListView.enter();
	}

	@Override
	public void render(GameContainer container, Graphics g, int currentIndex)
			throws SlickException {

		drawHeader(g);
		highscoreInfoView.render(container, g, currentIndex);
		highscoreListView.render(container, g, currentIndex);

	}

	/**
	 * Draw the views the header
	 * 
	 * @param g
	 *            The graphics context to render to
	 */
	private void drawHeader(Graphics g) {
		g.setFont(bigFont);
		int panelWidth = GUIUtils.getGameWidth();
		int y = 25;
		String str = "Highscores";
		int strWidth = g.getFont().getWidth(str);
		int x = panelWidth / 2 - strWidth / 2;
		g.drawString(str, x, y);
	}

}
