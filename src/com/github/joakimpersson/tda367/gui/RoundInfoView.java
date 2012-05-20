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
	private ImageLoader imageLoader = null;
	private Player roundWinner = null;
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
		imageLoader = ImageLoader.getInstance();
	}

	@Override
	public void enter() {
		startX = 205 + 65;
		startY = 65;
		players = model.getPlayers();

		Image[] textAnimationImgs = {
				imageLoader.getImage("info/winnerEffects1"),
				imageLoader.getImage("info/winnerEffects2") };
		textAnimation = new Animation(textAnimationImgs, 300);
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		g.setFont(smlFont);
		g.drawImage(imageLoader.getImage("round-info/bg"), startX, startY);
		int x = startX + 20;
		int y = startY + 20;
		int deltaX = 285 + 10;
		int deltaY = 225 + 10;
		int index = 0;
		for (Player p : players) {
			if (players.size() < 3) {
				drawPlayerInfo(p, x, y + 92, g);
				x += deltaX;
			} else {
				if (index < 2) {
					drawPlayerInfo(p, x + (deltaX * index), y, g);
				} else {
					drawPlayerInfo(p, x + (deltaX * (index - 2)), y + deltaY, g);
				}
				index++;
			}
		}
	}

	/**
	 * Notify the view about who is the winning Player of the last played round
	 * 
	 * @param winningPlayer
	 *            The player who won the last round
	 */
	public void setWinningPlayer(Player winningPlayer) {

		if (winningPlayer != null
				&& (this.roundWinner == null || !(this.roundWinner
						.equals(winningPlayer)))) {
			this.roundWinner = winningPlayer;
			createPlayerAnimation();
		}
	}

	/**
	 * Create an simple animation based on two images
	 */
	private void createPlayerAnimation() {
		Image imgOne = imageLoader.getImage(
				"player/" + roundWinner.getIndex() + "/win1").getScaledCopy(2);
		Image imgTwo = imageLoader.getImage(
				"player/" + roundWinner.getIndex() + "/win2").getScaledCopy(2);
		Image[] winningPlayerAnimationImgs = { imgOne, imgTwo };
		winningPlayerAnimation = new Animation(winningPlayerAnimationImgs, 400);
	}

	/**
	 * 
	 * Draw a player onto the screen including his name, image and if he won the
	 * last round or not
	 * 
	 * @param player
	 *            The player to be drawn onto the screen
	 * @param x
	 *            The starting coordinate in the x-axis
	 * @param y
	 *            The starting coordinate in the y-axis
	 * 
	 * @param g
	 *            The games graphics object
	 */
	private void drawPlayerInfo(Player player, int x, int y, Graphics g) {
		boolean isWinner = (model.getLastRoundWinner() == player);
		Image img = imageLoader.getImage("round-info/overlay");
		g.drawImage(img, x, y);

		// draws scaled player image
		if (isWinner) {
			winningPlayerAnimation.draw(x, y + 7);
		} else {
			img = imageLoader.getImage(player.getImage()).getScaledCopy(2);
			g.drawImage(img, x, y + 7);
		}

		// draw name
		g.setFont(bigFont);
		g.setColor(Color.white);
		g.drawString(player.getName(), x + 100, y + 10);

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
