package com.github.joakimpersson.tda367.gui;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.github.joakimpersson.tda367.gui.guiutils.GUIUtils;
import com.github.joakimpersson.tda367.model.IPyromaniacModel;
import com.github.joakimpersson.tda367.model.PyromaniacModel;
import com.github.joakimpersson.tda367.model.player.Player;
import com.github.joakimpersson.tda367.model.positions.FPosition;
import com.github.joakimpersson.tda367.model.positions.Position;
import com.github.joakimpersson.tda367.model.tiles.Tile;

/**
 * A view for the gameplayfield
 * 
 * @author joakimpersson
 * 
 */
public class GameFieldView implements IView {

	private IPyromaniacModel model = null;

	private static final int blockSide = 50;
	private int startY;
	private int startX;
	private List<Player> players = null;
	private int counter = 0;

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
		model = PyromaniacModel.getInstance();
	}

	@Override
	public void enter() {
		this.players = model.getPlayers();
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		drawMap(g);
		drawPlayers(g);
	}

	/**
	 * Draws the players current positions on the game map
	 * 
	 * @param g
	 *            The graphics context to render to
	 */
	private void drawPlayers(Graphics g) {

		for (Player player : players) {
			if (player.isAlive()) {
				FPosition pos = player.getGamePosition();
				drawImage(pos.getX() - 0.5F, pos.getY() - 0.6F,
						player.getImage(), g);
				if (player.isImmortal() && counter >= 10) {

					drawImage(pos.getX() - 0.5F, pos.getY() - 0.6F,
							"player/overlay/still-" + player.getDirection(), g);
					if (counter >= 20) {
						counter = 0;
					}
				}
				// TODO Adrian this will only work if we have a wall, else there
				// will be null pointer exception
				for (int i = -1; i <= 1; i++) {
					Position tilePos = new Position(player.getTilePosition()
							.getX() + i, player.getTilePosition().getY() + 1);
					Tile tile = model.getMap()[tilePos.getY()][tilePos.getX()];
					if (!tile.isWalkable()) {
						drawImage(tilePos.getX(), tilePos.getY(),
								tile.getTileType(), g);
					}
				}
				counter++;
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
	 * @param imageName
	 *            The path to the image as a string
	 * @param g
	 *            The graphics context to render to
	 */
	private void drawImage(float x, float y, String imageName, Graphics g) {
		// the players position is related to matrix so compensated is needed
		x *= blockSide;
		y *= blockSide;
		GUIUtils.drawImage(x + startX, y + startY, imageName, g);
	}

}
