package com.github.joakimpersson.tda367.gui;

import java.util.List;

import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.github.joakimpersson.tda367.gui.guiutils.GUIUtils;
import com.github.joakimpersson.tda367.model.PyromaniacModel;
import com.github.joakimpersson.tda367.model.IBombermanModel;
import com.github.joakimpersson.tda367.model.highscore.Score;
import com.github.joakimpersson.tda367.model.player.PlayerPoints;

public class GameOverLosersView implements IView {

	private final int X;
	private final int Y;

	private Font bigFont = null;

	private IBombermanModel model = null;
	private List<Score> playerScores = null;

	public GameOverLosersView(int x, int y) {
		this.X = x;
		this.Y = y;
		init();
	}

	private void init() {
		model = PyromaniacModel.getInstance();
		try {
			bigFont = GUIUtils.getBigFont();
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void enter() {
		playerScores = model.getGameOverSummary();
		removeWinnerFromScoreList();
	}

	private void removeWinnerFromScoreList() {
		// the winner should be first in the list
		playerScores.remove(0);
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		int x = X;
		int y = Y;
		int yDelta = 50;

		drawContainerHeader(x, y, g);

		y += yDelta;

		drawPlayerInfo(x, y, g);
	}

	private void drawContainerHeader(int x, int y, Graphics g) {
		g.setFont(bigFont);
		String str = "Losers:";
		g.drawString(str, x, y);
	}

	private void drawPlayerInfo(int x, int y, Graphics g) {
		g.setFont(bigFont);
		int i = 2;
		for(Score score : playerScores) {
			String playerSummaryStr = getPlayerSummaryStr(score, i);
			g.drawString(playerSummaryStr, x, y);
		}
	}

	private String getPlayerSummaryStr(Score score, int i) {
		StringBuilder strBuilder = new StringBuilder();
		String playerName = score.getPlayerName();
		PlayerPoints playerPoints = score.getPlayerPoints();
		
		strBuilder.append(i);
		strBuilder.append(". ");
		strBuilder.append(playerName);
		strBuilder.append(" ");
		strBuilder.append(playerPoints.getScore());
		strBuilder.append("p");

		return strBuilder.toString();
	}

}
