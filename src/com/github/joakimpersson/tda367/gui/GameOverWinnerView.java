package com.github.joakimpersson.tda367.gui;

import java.util.List;

import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.github.joakimpersson.tda367.gui.guiutils.GUIUtils;
import com.github.joakimpersson.tda367.model.BombermanModel;
import com.github.joakimpersson.tda367.model.IBombermanModel;
import com.github.joakimpersson.tda367.model.constants.PointGiver;
import com.github.joakimpersson.tda367.model.highscore.Score;
import com.github.joakimpersson.tda367.model.player.PlayerPoints;

public class GameOverWinnerView implements IView {

	private final int X;
	private final int Y;

	private Font bigFont = null;

	private IBombermanModel model = null;
	private List<Score> playerScores = null;
	private Score winningScore = null;

	public GameOverWinnerView(int x, int y) {
		this.X = x;
		this.Y = y;
		init();
	}

	private void init() {
		model = BombermanModel.getInstance();
		try {
			bigFont = GUIUtils.getBigFont();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void enter() {
		playerScores = model.getGameOverSummary();
		winningScore = getWinningPlayer();
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

		y += yDelta;

		drawGameSummary(x, y, g);
	}

	private void drawContainerHeader(int x, int y, Graphics g) {
		g.setFont(bigFont);
		String str = "Winner:";
		g.drawString(str, x, y);
	}

	private void drawPlayerInfo(int x, int y, Graphics g) {
		g.setFont(bigFont);
		String playerSummaryStr = getPlayerSummaryStr();
		g.drawString(playerSummaryStr, x, y);
	}

	private String getPlayerSummaryStr() {
		PlayerPoints playerPoints = winningScore.getPlayerPoints();
		StringBuilder strBuilder = new StringBuilder();

		strBuilder.append("1. ");
		strBuilder.append(winningScore.getPlayerName());
		strBuilder.append(" ");
		strBuilder.append(playerPoints.getScore());
		strBuilder.append("p");

		return strBuilder.toString();
	}

	private void drawGameSummary(int posX, int posY, Graphics g) {
		PlayerPoints playerPoints = winningScore.getPlayerPoints();
		int x = posX;
		int y = posY;
		int xDelta = 400;
		int yDelta = 70;
		String str = "Players Killed: "
				+ playerPoints.getEarnedPointGiver(PointGiver.KillPlayer);
		g.drawString(str, x, y);
		y += yDelta;
		str = "Destroyed Boxs: "
				+ playerPoints.getEarnedPointGiver(PointGiver.Box);
		g.drawString(str, x, y);

		x += xDelta;
		y = posY;

		str = "Players Hit: "
				+ playerPoints.getEarnedPointGiver(PointGiver.PlayerHit);
		g.drawString(str, x, y);
		y += yDelta;
		str = "Destroyed Pillars: "
				+ playerPoints.getEarnedPointGiver(PointGiver.Pillar);
		g.drawString(str, x, y);
	}

	private Score getWinningPlayer() {
		// the score object with highest score should be the first one
		return playerScores.get(0);
	}
}
