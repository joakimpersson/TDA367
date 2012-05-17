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
import com.github.joakimpersson.tda367.model.player.Player;

/**
 * 
 * @author joakimpersson
 * 
 */
public class RoundInfoView implements IView {

//	private static final int width = 550;
//	private static final int height = 440;
	private int startX;
	private int startY;
	private IBombermanModel model = null;
	private List<Player> players = null;
	private Font smlFont, bigFont;
	private ImageLoader imgs = null;

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
			bigFont = GUIUtils.getBigFont();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		imgs = ImageLoader.getInstance();
	}

	@Override
	public void enter() {
		startX = 205 + 65;
		startY = 65;
		players = model.getPlayers();
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		g.setFont(smlFont);
		g.drawImage(imgs.getImage("round-info/bg"), startX, startY);
		int x = startX + 20;
		int y = startY + 20;
		int deltaX = 285 + 10;
		int deltaY = 225 + 10;
		int index = 0;
		for (Player p : players) {
			if (players.size() < 3) {
				drawPlayerStats(p, x, y+92, g);
				x += deltaX;
			} else {
				if (index < 2) {
					drawPlayerStats(p, x+(deltaX*index), y, g);
				} else {
					drawPlayerStats(p, x+(deltaX*(index-2)), y+deltaY, g);
				}
				index++;
			}
		}
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
		g.drawImage(imgs.getImage("round-info/overlay"), x, y);
		g.drawImage(imgs.getImage(p.getImage()), x + 5F, y + 5F);
		
		g.setFont(bigFont);
		g.setColor(Color.white);
		g.drawString(p.getName(), x + 55F, y + 7F);
	}
}
