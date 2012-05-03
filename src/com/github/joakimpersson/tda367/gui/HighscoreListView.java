package com.github.joakimpersson.tda367.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.github.joakimpersson.tda367.model.BombermanModel;
import com.github.joakimpersson.tda367.model.IBombermanModel;
import com.github.joakimpersson.tda367.model.Score;

/**
 * 
 * @author joakimpersson
 * 
 */
public class HighscoreListView {

	private final int X;
	private final int Y;
	private final static int WIDTH = 400;
	private IBombermanModel model = null;
	private Score[] highscore = null;

	public HighscoreListView(int x, int y) {
		this.X = x;
		this.Y = y;
		init();
	}

	private void init() {
		model = BombermanModel.getInstance();
	}

	public void enter() {
		if (model.getHighscoreList().length > 0) {
			highscore = model.getHighscoreList();
		}
	}

	public void render(GameContainer container, Graphics g, int currentIndex)
			throws SlickException {
		int x = X;
		int y = Y;
		if (highscore != null) {
			for (Score s : highscore) {
				g.drawString(s.getPlayerName(), x, y);
				y += 50;
				System.out.println(s.getPlayerName());
			}
		} else {
			drawEmptyListString(x, y, g);
		}
	}

	private void drawEmptyListString(int x, int y, Graphics g) {
		String str = "No Highscroes yet!";
		x += getStrinCenterX(str, WIDTH, g);
		g.drawString(str, x, y);

	}

	private int getStrinCenterX(String str, int width, Graphics g) {
		int strWidth = g.getFont().getWidth(str);
		return width / 2 - strWidth / 2;
	}

}
