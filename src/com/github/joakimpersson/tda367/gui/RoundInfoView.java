package com.github.joakimpersson.tda367.gui;

import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.github.joakimpersson.tda367.gui.guiutils.GUIUtils;
import com.github.joakimpersson.tda367.model.BombermanModel;
import com.github.joakimpersson.tda367.model.IBombermanModel;
import com.github.joakimpersson.tda367.model.constants.PointGiver;
import com.github.joakimpersson.tda367.model.player.Player;

/**
 * 
 * @author joakimpersson
 * 
 */
public class RoundInfoView implements IView {

	private static final int width = 850;
	private static final int height = 550;
	private int startX;
	private int startY;
	private IBombermanModel model = null;
	private List<Player> players = null;
	private Font smlFont;

	/**
	 * Creates a new view containing info about the players stats from the
	 * PlayerPoints object
	 */
	public RoundInfoView() {
		init();
	}

	/**
	 * Responsible for fetching instances ,info from the model and init fonts
	 * etc
	 */
	private void init() {
		model = BombermanModel.getInstance();
		try {
			smlFont = GUIUtils.getSmlFont();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void enter() {
		startX = GUIUtils.getGameWidth() / 2 - width / 2;
		startY = GUIUtils.getGameHeight() / 2 - height / 2;
		players = model.getPlayers();
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		g.setFont(smlFont);
		int x = startX + 50;
		int deltaX = width / players.size();
		int midX = startX + width / 2;

		drawBackgroundContainer(g);

		int y = startY + 40;
		drawTitle(g, midX, y);

		y += 60;

		for (Player p : players) {
			drawPlayerStats(p, x, y, g);
			x += deltaX;
		}
	}

	/**
	 * Draws the title of the view
	 * 
	 * @param g
	 *            The games graphics object
	 * @param x
	 *            The starting coordinate in the x-axis
	 * @param y
	 *            The starting coordinate in the y-axis
	 */
	private void drawTitle(Graphics g, int x, int y) {
		// should be template and say round over, matchover etc
		String str = "Skumme dummy()";
		int strWidth = g.getFont().getWidth(str);
		g.drawString(str, x - strWidth / 2, y);

	}

	/**
	 * Draws the background of the container
	 * 
	 * @param g
	 *            The games graphics object
	 */
	private void drawBackgroundContainer(Graphics g) {
		g.setColor(Color.black);

		g.fillRoundRect(startX, startY, width, height, 25);
		g.setColor(Color.white);
		g.drawRoundRect(startX, startY, width, height, 25);

	}

	/**
	 * Draws all the info about the Player and stats from his PlayerPoint object
	 * onto the screen
	 * 
	 * 
	 * @param p
	 *            The player holding the PlayerPoints object
	 * @param x
	 *            The starting coordinate in the x-axis
	 * @param y
	 *            The starting coordinate in the y-axis
	 * 
	 * @param g
	 *            The games graphics object
	 */
	private void drawPlayerStats(Player p, int x, int y, Graphics g) {
		PointGiver[] pointGivers = PointGiver.values();

		int yDelta = 40;

		g.drawString(p.getName(), x, y);

		y += yDelta;

		g.drawString("Destroyed/Killed", x, y);

		for (PointGiver pg : pointGivers) {
			y += yDelta;

			String str = getPlayerString(p, pg);

			g.drawString(str, x, y);

		}

	}

	/**
	 * Formats a string including the pointgivers name and its corresponging
	 * value in the PlayerPoint object
	 * 
	 * @param p
	 *            The player holding the PlayerPoints object
	 * @param pp
	 *            The PointGiveres value in the PlayerPoints object
	 * @return A string containing the name of the PointGiver and its value
	 */
	private String getPlayerString(Player p, PointGiver pg) {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(pg.name());
		strBuilder.append(": ");
		strBuilder.append(p.getDestroyedPointGiver(pg));
		strBuilder.append(" st");
		return strBuilder.toString();
	}
}
