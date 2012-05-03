package com.github.joakimpersson.tda367.gui;

import java.util.List;

import org.newdawn.slick.Color;
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
	private List<Score> highscore = null;

	public HighscoreListView(int x, int y) {
		this.X = x;
		this.Y = y;
		init();
	}

	private void init() {
		model = BombermanModel.getInstance();
	}

	public void enter() {
		if (model.getHighscoreList().size() > 0) {
			highscore = model.getHighscoreList();
		}
	}

	public void render(GameContainer container, Graphics g, int currentIndex)
			throws SlickException {
		int x = X;
		int y = Y;
		if (highscore != null) {
			drawPlayerInfo(x, y, g, currentIndex);
		} else {
			drawEmptyListString(x, y, g);
		}
	}

	private void drawPlayerInfo(int x, int y, Graphics g, int currentIndex) {
		g.setColor(Color.white);
		int i = 0;
		for (Score s : highscore) {
			String str = formatScoreString(s, i);

			if (i == currentIndex) {
				g.setColor(Color.cyan);
			}

			g.drawString(str, x, y);
			y += 25;
			i++;
			// Make sure that the color always is white
			g.setColor(Color.white);
		}
	}

	private String formatScoreString(Score s, int i) {
		StringBuffer str = new StringBuffer();
		str.append((i + 1));
		str.append(". ");
		str.append(s.getPlayerName());
		str.append(" : ");
		str.append(s.getPlayerPoints().getScore());
		str.append("p");
		return str.toString();
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
