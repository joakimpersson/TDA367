package com.github.joakimpersson.tda367.gui;

import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.github.joakimpersson.tda367.gui.guiutils.GUIUtils;
import com.github.joakimpersson.tda367.model.PyromaniacModel;
import com.github.joakimpersson.tda367.model.IPyromaniacModel;
import com.github.joakimpersson.tda367.model.highscore.Score;

/**
 * A class representing a view that shows a bunch of highscores.
 * 
 * @author joakimpersson
 * @modified adderollen
 * 
 */
public class HighscoreListView {

	private final int X;
	private final int Y;
	private final static int WIDTH = 400;
	private IPyromaniacModel model = null;
	private List<Score> highscore = null;

	/**
	 * Creats a new view display a lit of all the score objects in the highscore
	 * list
	 * 
	 * @param x
	 *            The starting coordinate in the x-axis
	 * @param y
	 *            The starting coordinate in the y-axis
	 */
	public HighscoreListView(int x, int y) {
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
			drawPlayerInfo(x, y, g, currentIndex);
		} else {
			drawEmptyListString(x, y, g);
		}
	}

	/**
	 * Draws info about the player in the score object and also draws his score
	 * 
	 * @param x
	 *            The starting coordinate in the x-axis
	 * @param y
	 *            The starting coordinate in the y-axis *
	 * @param g
	 *            The games graphics object
	 * @param currentIndex
	 *            The index in the highscore list for the score object to be
	 *            shown as selected
	 */
	private void drawPlayerInfo(int x, int y, Graphics g, int currentIndex) {
		g.setColor(Color.white);
		int i = 0;
		for (Score score : highscore) {
			String str = formatScoreString(score, i + 1);

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

	/**
	 * Formats the score objects string containing the players name and total
	 * score in the game
	 * 
	 * @param score
	 *            The score object
	 * @param index
	 *            The score objects placement in the highscore list
	 * @return
	 */
	private String formatScoreString(Score score, int index) {
		StringBuffer str = new StringBuffer();
		str.append(index);
		str.append(". ");
		str.append(score.getPlayerName());
		str.append(" : ");
		str.append(score.getPlayerPoints().getScore());
		str.append("p");
		return str.toString();
	}

	/**
	 * Draws the string shown when the highscore list is empty
	 * 
	 * @param x
	 *            The starting coordinate in the x-axis
	 * @param y
	 *            The starting coordinate in the y-axis
	 * @param g
	 *            The games graphics object
	 */
	private void drawEmptyListString(int x, int y, Graphics g) {
		String str = "No Highscroes yet!";
		x += GUIUtils.getStringCenterX(str, WIDTH, g);
		g.drawString(str, x, y);
	}

}
