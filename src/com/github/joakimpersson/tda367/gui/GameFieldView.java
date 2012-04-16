package com.github.joakimpersson.tda367.gui;

import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

import com.github.joakimpersson.tda367.model.BombermanModel;
import com.github.joakimpersson.tda367.model.IBombermanModel;
import com.github.joakimpersson.tda367.model.player.Player;
import com.github.joakimpersson.tda367.model.tiles.Tile;
import com.github.joakimpersson.tda367.model.tiles.bombs.Bomb;
import com.github.joakimpersson.tda367.model.tiles.nonwalkable.Box;
import com.github.joakimpersson.tda367.model.tiles.nonwalkable.Pillar;
import com.github.joakimpersson.tda367.model.tiles.nonwalkable.Wall;
import com.github.joakimpersson.tda367.model.tiles.walkable.Fire;
import com.github.joakimpersson.tda367.model.tiles.walkable.Floor;
import com.github.joakimpersson.tda367.model.tiles.walkable.PowerupItem;
import com.github.joakimpersson.tda367.model.utils.FPosition;

public class GameFieldView implements IView {

	// TODO jocke ask if this is okay!
	private IBombermanModel model = null;

	private final int blockSide = 50;
	private int startY;
	private int startX;
	private List<Player> players = null;

	public GameFieldView(int startX, int startY) {
		this.startX = startX;
		this.startY = startY;
		init();
	}

	private void init() {
		model = BombermanModel.getInstance();
		this.players = model.getPlayers();
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		drawMap(g);
		drawPlayer(g);
	}

	private void drawPlayer(Graphics g) {

		for (Player p : players) {
			// The x,y coordinates doesn't matter since we set the center
			// coordinates after
			Rectangle rect = new Rectangle(0, 0, blockSide, blockSide);
			FPosition pos = p.getGamePosition();
			rect.setCenterX(pos.getX() * blockSide + startX);
			rect.setCenterY(pos.getY() * blockSide + startY);
			g.setColor(Color.white);
			g.fill(rect);

			// TODO remove, just for test, added to determine orientation of
			// final picture
			Circle circle = new Circle(0, 0, 2);
			circle.setCenterX(pos.getX() * blockSide
					+ (p.getDirection().getX() * 20) + startX);
			circle.setCenterY(pos.getY() * blockSide
					+ (p.getDirection().getY() * 20) + startY);
			g.setColor(Color.black);
			g.fill(circle);
		}

	}

	private void drawMap(Graphics g) {
		Tile[][] map = model.getMap();
		int mapHeight = map.length;
		int mapWidth = map[0].length;
		Color color = null;
		for (int i = 0; i < mapHeight; i++) {
			for (int j = 0; j < mapWidth; j++) {
				Tile tile = map[i][j];
				if (tile instanceof Wall) {
					color = new Color(162, 152, 105);
					drawRect(j, i, color, g);
				} else if (tile instanceof Floor) {
					color = new Color(33, 29, 25);
					drawRect(j, i, color, g);
				} else if (tile instanceof Pillar) {
					color = new Color(162, 152, 105);
					drawRect(j, i, color, g);
				} else if (tile instanceof Box) {
					color = new Color(180, 117, 119);
					drawRect(j, i, color, g);
				} else if (tile instanceof Fire) {
					drawRect(j, i, Color.red, g);
				} else if (tile instanceof Bomb) {
					drawRect(j, i, Color.cyan, g);
				} else if (tile instanceof PowerupItem) {
					color = new Color(22, 100, 151);
					drawRect(j, i, color, g);
				}
			}
		}

	}

	private void drawRect(int x, int y, Color color, Graphics g) {
		// the players position is related to matrix so compensated is needed
		x *= blockSide;
		y *= blockSide;
		g.setColor(color);
		g.fillRect(x + startX, y + startY, blockSide, blockSide);
	}

	// private void drawMap(GameContainer container, Graphics g) {
	// Tile[][] map = model.getMap();
	// int mapHeight = map.length;
	// int mapWidth = map[0].length;
	//
	// for (int i = 0; i < mapHeight; i++) {
	// for (int j = 0; j < mapWidth; j++) {
	// Tile tile = map[i][j];
	// drawRect(j, i, tile.getImage(), g);
	// }
	// }
	//
	// }

	// private void drawRect(int x, int y, String s, Graphics g) {
	// // the players position is related to matrix so compensated is needed
	// x *= blockSide;
	// y *= blockSide;
	// // g.setColor(color);
	// g.fillRect(x + startX, y + startY, blockSide, blockSide);
	// }

}
