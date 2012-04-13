package com.github.joakimpersson.tda367.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import com.github.joakimpersson.tda367.model.BombermanModel;
import com.github.joakimpersson.tda367.model.IBombermanModel;
import com.github.joakimpersson.tda367.model.tiles.Tile;
import com.github.joakimpersson.tda367.model.tiles.bombs.Bomb;
import com.github.joakimpersson.tda367.model.tiles.nonwalkable.Box;
import com.github.joakimpersson.tda367.model.tiles.nonwalkable.Pillar;
import com.github.joakimpersson.tda367.model.tiles.nonwalkable.Wall;
import com.github.joakimpersson.tda367.model.tiles.walkable.Fire;
import com.github.joakimpersson.tda367.model.tiles.walkable.Floor;
import com.github.joakimpersson.tda367.model.tiles.walkable.PowerupItem;
import com.github.joakimpersson.tda367.model.utils.FPosition;

/**
 * 
 * @author joakimpersson
 * 
 */
public class GameplayView implements IView {

	// TODO jocke ask if this is okay!
	private IBombermanModel model = null;

	// private Map<Player,Image> players = null;

	public GameplayView() {
		model = BombermanModel.getInstance();
		init();
	}

	private void init() {
		// TODO jocke change to hashmap when have images
		// this.players = new HashMap<Player,Image>();
		// for (Player p : model.getPlayers()) {
		// Image img = null;
		// try {
		// // TODO adrian add image
		// img = new Image("");
		// } catch (SlickException e) {
		// e.printStackTrace();
		// }
		// this.players.put(p, img);
		// }
	}

	@Override
	public void render(GameContainer container,  Graphics g)
			throws SlickException {
		drawMap(container, g);
		drawPlayer(container, g);
	}

	private void drawPlayer(GameContainer container, Graphics g) {
		// TODO jocke remove dummy implementation
		Tile[][] map = model.getMap();
		int mapHeight = map.length;
		int mapWidth = map[0].length;

		int blockWidth = container.getWidth() / mapWidth;
		int blockHeight = container.getHeight() / mapHeight;

		Rectangle rect = new Rectangle(0, 0, 20, 20);
		FPosition p = model.getPlayers().get(0).getFPosition();
		rect.setCenterX(p.getX() * blockWidth);
		rect.setCenterY(p.getY() * blockHeight);
		g.setColor(Color.white);
		g.draw(rect);

	}

	private void drawMap(GameContainer container, Graphics g) {
		Tile[][] map = model.getMap();
		int mapHeight = map.length;
		int mapWidth = map[0].length;

		int blockWidth = container.getWidth() / mapWidth;
		int blockHeight = container.getHeight() / mapHeight;

		for (int i = 0; i < mapHeight; i++) {
			for (int j = 0; j < mapWidth; j++) {
				Tile tile = map[i][j];
				if (tile instanceof Wall) {
					drawRect(blockWidth * j, blockHeight * i, blockWidth,
							blockHeight, Color.pink, g);
				} else if (tile instanceof Floor) {
					drawRect(blockWidth * j, blockHeight * i, blockWidth,
							blockHeight, Color.cyan, g);
				} else if (tile instanceof Pillar) {
					drawRect(blockWidth * j, blockHeight * i, blockWidth,
							blockHeight, Color.orange, g);
				} else if (tile instanceof Box) {
					drawRect(blockWidth * j, blockHeight * i, blockWidth,
							blockHeight, Color.gray, g);
				} else if (tile instanceof Fire) {
					drawRect(blockWidth * j, blockHeight * i, blockWidth,
							blockHeight, Color.red, g);
				} else if (tile instanceof Bomb) {
					drawRect(blockWidth * j, blockHeight * i, blockWidth,
							blockHeight, Color.green, g);
				} else if (tile instanceof PowerupItem) {
					drawRect(blockWidth * j, blockHeight * i, blockWidth,
							blockHeight, Color.yellow, g);
				}
			}
		}

	}

	private void drawRect(final int x, final int y, final int width,
			final int height, final Color color, final Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
		g.setColor(Color.black);
		g.drawRect(x, y, width, height);
	}
}
