package com.github.joakimpersson.tda367.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class HighscoreView implements IView {

	private HighscoreListView highscoreListView = null;
	private HighscoreInfoView highscoreInfoView = null;

	public HighscoreView() {
		init();
	}

	private void init() {
		highscoreInfoView = new HighscoreInfoView(50, 450);
		highscoreListView = new HighscoreListView(500, 900);
	}

	@Override
	public void enter() {
		highscoreInfoView.enter();
		highscoreListView.enter();
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {

		highscoreInfoView.render(container, g);
		highscoreListView.render(container, g);

	}

}
