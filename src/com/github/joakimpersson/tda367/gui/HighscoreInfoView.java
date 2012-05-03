package com.github.joakimpersson.tda367.gui;

import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.github.joakimpersson.tda367.model.BombermanModel;
import com.github.joakimpersson.tda367.model.IBombermanModel;
import com.github.joakimpersson.tda367.model.Score;
import com.github.joakimpersson.tda367.model.constants.PointGiver;
import com.github.joakimpersson.tda367.model.player.PlayerPoints;

/**
 * 
 * @author joakimpersson
 * 
 */
public class HighscoreInfoView {

	private final int X;
	private final int Y;
	private final static int WIDTH = 400;
	private IBombermanModel model = null;
	private List<Score> highscore = null;

	public HighscoreInfoView(int x, int y) {
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
			g.setColor(Color.cyan);
			drawSelectedPlayer(x, y, currentIndex, g);
			y += 60;
			g.setColor(Color.white);
			drawPlayerScore(x, y, currentIndex, g);
			y += 5;
			drawPlayerStats(x, y, currentIndex, g);
		} else {
			drawEmptyListString(x, y, g);
		}

	}

	private void drawSelectedPlayer(int x, int y, int currentIndex, Graphics g) {

		Score score = highscore.get(currentIndex);
		String playerName = score.getPlayerName();
		int midX = x + getStrinCenterX(playerName, WIDTH, g);
		g.drawString(playerName, midX, y);

	}

	private void drawPlayerScore(int x, int y, int currentIndex, Graphics g) {
		Score s = highscore.get(currentIndex);
		PlayerPoints pp = s.getPlayerPoints();
		String str = "Score: " + pp.getScore() + "p";
		g.drawString(str, x, y);
	}

	private void drawPlayerStats(int x, int y, int currentIndex, Graphics g) {
		PointGiver[] pointGivers = PointGiver.values();

		int yDelta = 40;

		y += yDelta;

		g.drawString("Destroyed/Killed", x, y);
		Score s = highscore.get(currentIndex);
		PlayerPoints pp = s.getPlayerPoints();
		for (PointGiver pg : pointGivers) {
			y += yDelta;

			String str = getPointGiverString(pg, pp);

			g.drawString(str, x, y);
		}

	}

	private String getPointGiverString(PointGiver pg, PlayerPoints pp) {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(pg.name());
		strBuilder.append(": ");
		strBuilder.append(pp.getDestroyedPointGiver(pg));
		strBuilder.append(" st");
		return strBuilder.toString();
	}

	private void drawEmptyListString(int x, int y, Graphics g) {
		String str = "Du vågar aldrig...";
		x += getStrinCenterX(str, WIDTH, g);
		g.drawString(str, x, y);

	}

	private int getStrinCenterX(String str, int width, Graphics g) {
		int strWidth = g.getFont().getWidth(str);
		return width / 2 - strWidth / 2;
	}
}
