package com.github.joakimpersson.tda367.gui;

import java.util.List;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
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

	private int startX;
	private int startY;
	private IBombermanModel model = null;
	private List<Player> players = null;
	private Font smlFont = null;
	private Font bigFont = null;
	private ImageLoader imgs = null;
	private Animation textAnimation = null;
	private Animation winningPlayerAnimation = null;

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

		Image[] textAnimationImgs = { imgs.getImage("info/winnerEffects1"),
				imgs.getImage("info/winnerEffects2") };
		textAnimation = new Animation(textAnimationImgs, 300);
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
				drawPlayerStats(p, x, y + 92, g);
				x += deltaX;
			} else {
				if (index < 2) {
					drawPlayerStats(p, x + (deltaX * index), y, g);
				} else {
					drawPlayerStats(p, x + (deltaX * (index - 2)), y + deltaY,
							g);
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
		boolean isWinner = (model.getLastRoundWinner() == p);
		g.drawImage(imgs.getImage("round-info/overlay"), x, y);

		// draws scaled player image
		// TODO jocke refactor this not good!
		if (isWinner) {
			if (winningPlayerAnimation == null) {
				Image[] winningPlayerAnimationImgs = {
						imgs.getImage("player/" + p.getIndex() + "/win1")
								.getScaledCopy(2),
						imgs.getImage("player/" + p.getIndex() + "/win2")
								.getScaledCopy(2) };
				winningPlayerAnimation = new Animation(
						winningPlayerAnimationImgs, 400);
			}
			winningPlayerAnimation.draw(x, y + 7);
		} else {
			g.drawImage(imgs.getImage(p.getImage()).getScaledCopy(2), x, y + 7);
		}

		// draw name
		g.setFont(bigFont);
		g.setColor(Color.white);
		g.drawString(p.getName(), x + 100, y + 10);

		// draw winner string
		g.setFont(smlFont);
		g.setColor(Color.darkGray);
		String gameStatusString = "LOSER";
		int yDiff = 54;
		int xDiff = 130;
		if (isWinner) {
			g.setFont(bigFont);
			g.setColor(Color.yellow);
			gameStatusString = "WINNER";
			yDiff = 48;
			xDiff = 105;

			textAnimation.draw(x + 94, y + 36);

		}
		g.drawString(gameStatusString, x + xDiff, y + yDiff);
	}
}
