package com.github.joakimpersson.tda367.gui;

import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.github.joakimpersson.tda367.gui.guiutils.GUIUtils;
import com.github.joakimpersson.tda367.model.PyromaniacModel;
import com.github.joakimpersson.tda367.model.IPyromaniacModel;
import com.github.joakimpersson.tda367.model.constants.PointGiver;
import com.github.joakimpersson.tda367.model.highscore.Score;
import com.github.joakimpersson.tda367.model.player.PlayerPoints;

/**
 * A class representing a view that shows info about a specified highscore.
 * 
 * @author joakimpersson
 * @modified adderollen
 * 
 */
public class HighscoreInfoView {

	private final int X;
	private final int Y;
	private final static int WIDTH = 400;
	private IPyromaniacModel model = null;
	private List<Score> highscore = null;

	/**
	 * Create a new HighscoreInfoView that is responsible for displaying info
	 * about a single score object
	 * 
	 * @param x
	 *            The starting coordinate in the x-axis
	 * @param y
	 *            The starting coordinate in the y-axis
	 */
	public HighscoreInfoView(int x, int y) {
		this.X = x;
		this.Y = y;
		init();
	}

	/**
	 * Responsible for fetching instances ,info from the model and init fonts
	 * etc
	 */
	private void init() {
		model = PyromaniacModel.getInstance();
	}

	/**
	 * Notification that we've entered this game state
	 */
	public void enter() {
		if (model.getHighscoreList().size() > 0) {
			highscore = model.getHighscoreList();
		}
	}

	/**
	 * Render this view to the game's graphics context
	 * 
	 * @param container
	 *            The container holding the game
	 * @param g
	 *            The graphics context to render to
	 * @throws SlickException
	 *             Indicates a failure to render an gui object
	 */
	public void render(GameContainer container, Graphics g, int currentIndex)
			throws SlickException {
		int x = X;
		int y = Y;
		if (highscore != null) {
			g.setColor(Color.cyan);
			drawSelectedPlayerName(x, y, currentIndex, g);
			y += 60;
			g.setColor(Color.white);
			drawPlayerScore(x, y, currentIndex, g);
			y += 5;
			drawPlayerStats(x, y, currentIndex, g);
		} else {
			drawEmptyListString(x, y, g);
		}

	}

	/**
	 * Draws the name of the player onto the screen,
	 * 
	 * @param x
	 *            The starting coordinate in the x-axis
	 * @param y
	 *            The starting coordinate in the y-axis
	 * @param currentIndex
	 *            The index in the highscore list for the score object to be
	 *            shown
	 * @param g
	 *            The graphics context to render to
	 */
	private void drawSelectedPlayerName(int x, int y, int currentIndex,
			Graphics g) {

		Score score = highscore.get(currentIndex);
		String playerName = score.getPlayerName();
		int midX = x + GUIUtils.getStringCenterX(playerName, WIDTH, g);
		g.drawString(playerName, midX, y);

	}

	/**
	 * Draws the score/player objects score onto the screen
	 * 
	 * @param x
	 *            The starting coordinate in the x-axis
	 * @param y
	 *            The starting coordinate in the y-axis
	 * @param currentIndex
	 *            The index in the highscore list for the score object to be
	 *            shown
	 * @param g
	 *            The graphics context to render to
	 */
	private void drawPlayerScore(int x, int y, int currentIndex, Graphics g) {
		Score score = highscore.get(currentIndex);
		PlayerPoints playerPoints = score.getPlayerPoints();
		String str = "Score: " + playerPoints.getScore() + "p";
		g.drawString(str, x, y);
	}

	/**
	 * Draws all the info about the score objects playerpoint object onto the
	 * screen
	 * 
	 * @param x
	 *            The starting coordinate in the x-axis
	 * @param y
	 *            The starting coordinate in the y-axis
	 * @param currentIndex
	 *            The index in the highscore list for the score object to be
	 *            shown
	 * @param g
	 *            The graphics context to render to
	 */
	private void drawPlayerStats(int x, int y, int currentIndex, Graphics g) {
		PointGiver[] pointGivers = PointGiver.values();

		int yDelta = 40;

		y += yDelta;

		g.drawString("Destroyed/Killed", x, y);
		Score score = highscore.get(currentIndex);
		PlayerPoints playerPoints = score.getPlayerPoints();
		for (PointGiver pointGiver : pointGivers) {
			y += yDelta;

			String str = getPointGiverString(pointGiver, playerPoints);

			g.drawString(str, x, y);
		}

	}

	/**
	 * Formats a string including the pointgivers name and its corresponging
	 * value in the PlayerPoint object
	 * 
	 * @param pointGiver
	 *            The current PointGiver
	 * @param playerPoints
	 *            The PointGiveres value in the PlayerPoints object
	 * @return A string containing the name of the PointGiver and its value
	 */
	private String getPointGiverString(PointGiver pointGiver,
			PlayerPoints playerPoints) {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(pointGiver.name());
		strBuilder.append(": ");
		strBuilder.append(playerPoints.getEarnedPointGiver(pointGiver));
		strBuilder.append(" st");
		return strBuilder.toString();
	}

	/**
	 * Draws the string shown when the highscore list is empty
	 * 
	 * @param x
	 *            The starting coordinate in the x-axis
	 * @param y
	 *            The starting coordinate in the y-axis
	 * @param g
	 *            The graphics context to render to
	 */
	private void drawEmptyListString(int x, int y, Graphics g) {
		g.setColor(Color.white);
		String str = "Du vågar aldrig...";
		x += GUIUtils.getStringCenterX(str, WIDTH, g);
		g.drawString(str, x, y);

	}
}
