package com.github.joakimpersson.tda367.gui;

import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class HighscoreView {

	private HighscoreListView highscoreListView = null;
	private HighscoreInfoView highscoreInfoView = null;
	private Font bigFont = null;

	public HighscoreView() {
		init();
	}

	private void init() {
		highscoreListView = new HighscoreListView(50, 100);
		highscoreInfoView = new HighscoreInfoView(500, 100);
		try {
			bigFont = GUIParameters.INSTANCE.getBigFont();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public void enter() {
		highscoreInfoView.enter();
		highscoreListView.enter();
	}

	public void render(GameContainer container, Graphics g, int currentIndex)
			throws SlickException {

		drawHeader(g);
		highscoreInfoView.render(container, g, currentIndex);
		highscoreListView.render(container, g, currentIndex);

	}

	private void drawHeader(Graphics g) {
		g.setFont(bigFont);
		int panelWidth = GUIParameters.INSTANCE.getGameWidth();
		int y = 25;
		String str = "Highscores";
		int strWidth = g.getFont().getWidth(str);
		int x = panelWidth / 2 - strWidth / 2;
		g.drawString(str, x, y);
	}

}
