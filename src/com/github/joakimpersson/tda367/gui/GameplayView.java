package com.github.joakimpersson.tda367.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.github.joakimpersson.tda367.model.BombermanModel;
import com.github.joakimpersson.tda367.model.IBombermanModel;
import com.github.joakimpersson.tda367.model.tiles.Tile;
import com.github.joakimpersson.tda367.model.tiles.nonwalkable.Box;
import com.github.joakimpersson.tda367.model.tiles.nonwalkable.Pillar;
import com.github.joakimpersson.tda367.model.tiles.nonwalkable.Wall;
import com.github.joakimpersson.tda367.model.tiles.walkable.Floor;

public class GameplayView implements IView {

	// TODO jocke ask if this is okay!
	private IBombermanModel model = null;

	public GameplayView() {
		model = BombermanModel.getInstance();
		init();
	}

	private void init() {

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		drawMap(container, g);

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
							blockHeight, Color.red, g);
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
