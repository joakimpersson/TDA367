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
	private int startX, startY;
	private IBombermanModel model = null;
	private List<Player> players = null;
	private Font smlFont, bigFont;
	private ImageLoader imgs = null;
	private int count = 0;

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
		count++;
		if (count > 40)
			count = 0;
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
		boolean isWinner = (model.getLastPlayerAlive() == p);
		g.drawImage(imgs.getImage("round-info/overlay"), x, y);
		
		// draws scaled player image
		g.drawImage(imgs.getImage(p.getImage()).getScaledCopy(2), x, y + 7);
		
		// draw name
		g.setFont(bigFont);
		g.setColor(Color.white);
		g.drawString(p.getName(), x + 100, y + 10);
		
		// draw winner string
		g.setFont(smlFont);
		g.setColor(Color.darkGray);
		String winnerString = "LOSER";
		int yDiff = 54;
		int xDiff = 130;
		if (isWinner) {
			g.setFont(bigFont);
			g.setColor(Color.yellow);
			winnerString = "WINNER";
			yDiff = 48;
			xDiff = 105;
			if (count > 20)
				g.drawImage(imgs.getImage("info/winnerEffects1"), x + 94, y + 36);
			else 
				g.drawImage(imgs.getImage("info/winnerEffects2"), x + 94, y + 36);
			
		}
		g.drawString(winnerString, x + xDiff, y + yDiff);
	}
}
