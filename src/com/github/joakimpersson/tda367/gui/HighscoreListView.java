package com.github.joakimpersson.tda367.gui;

import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.github.joakimpersson.tda367.gui.guiutils.GUIUtils;
import com.github.joakimpersson.tda367.model.BombermanModel;
import com.github.joakimpersson.tda367.model.IBombermanModel;
import com.github.joakimpersson.tda367.model.highscore.Score;

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
		for (Score s : highscore) {
			String str = formatScoreString(s, i + 1);

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
	 * @param s
	 *            The score object
	 * @param i
	 *            The score objects placement in the highscore list
	 * @return
	 */
	// TODO jocke perhaps write a toString method in the score object
	private String formatScoreString(Score s, int i) {
		StringBuffer str = new StringBuffer();
		str.append(i);
		str.append(". ");
		str.append(s.getPlayerName());
		str.append(" : ");
		str.append(s.getPlayerPoints().getScore());
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
