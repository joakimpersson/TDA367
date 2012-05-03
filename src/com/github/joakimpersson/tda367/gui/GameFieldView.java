package com.github.joakimpersson.tda367.gui;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.github.joakimpersson.tda367.model.BombermanModel;
import com.github.joakimpersson.tda367.model.IBombermanModel;
import com.github.joakimpersson.tda367.model.player.Player;
import com.github.joakimpersson.tda367.model.tiles.Tile;
import com.github.joakimpersson.tda367.model.utils.FPosition;

/**
 * 
 * @author joakimpersson
 * 
 */
public class GameFieldView implements IView {

	private IBombermanModel model = null;

	private static final int blockSide = 50;
	private int startY;
	private int startX;
	private List<Player> players = null;

	private ImageLoader imgs;

	/**
	 * Creats a new view displaying the game map and the players
	 * 
	 * @param startX
	 *            The starting coordinate in the x-axis
	 * @param startY
	 *            The starting coordinate in the y-axis
	 */
	public GameFieldView(int startX, int startY) {
		this.startX = startX;
		this.startY = startY;
		init();

	}

	/**
	 * Responsible for fetching instances ,info from the model and init fonts
	 * etc
	 */
	private void init() {
		model = BombermanModel.getInstance();
	}

	@Override
	public void enter() {
		this.players = model.getPlayers();
		this.imgs = ImageLoader.getInstance();
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		drawMap(g);
		drawPlayer(g);
	}

	/**
	 * Draws the players current positions on the game map
	 * 
	 * @param g
	 *            The graphics context to render to
	 */
	private void drawPlayer(Graphics g) {

		for (Player p : players) {
			if (p.isAlive()) {
				FPosition pos = p.getGamePosition();
				drawImage(pos.getX() - 0.5F, pos.getY() - 0.5F,
						p.getImageString(), g);
			}
		}

	}

	/**
	 * Draws the current verision of the game map onto the screen
	 * 
	 * @param g
	 *            The graphics context to render to
	 */
	private void drawMap(Graphics g) {
		Tile[][] map = model.getMap();
		int mapHeight = map.length;
		int mapWidth = map[0].length;

		for (int i = 0; i < mapHeight; i++) {
			for (int j = 0; j < mapWidth; j++) {
				Tile tile = map[i][j];
				drawImage(j, i, tile.getTileType(), g);
			}
		}
	}

	/**
	 * 
	 * Draws an image to the screen
	 * 
	 * @param x
	 *            The starting coordinate in the x-axis
	 * @param y
	 *            The starting coordinate in the y-axis
	 * @param s
	 *            The path to the image as a string
	 * @param g
	 *            The graphics context to render to
	 */
	private void drawImage(float x, float y, String s, Graphics g) {
		// the players position is related to matrix so compensated is needed
		x *= blockSide;
		y *= blockSide;
		Image i = imgs.getImage(s);
		g.drawImage(i, x + startX, y + startY);
	}

}
