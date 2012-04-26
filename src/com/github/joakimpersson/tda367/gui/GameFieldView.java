package com.github.joakimpersson.tda367.gui;

import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

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

	public GameFieldView(int startX, int startY) {
		this.startX = startX;
		this.startY = startY;
		init();

	}

	private void init() {
		model = BombermanModel.getInstance();
		this.players = model.getPlayers();
		this.imgs = ImageLoader.getInstance();
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		drawMap(g);
		drawPlayer(g);
	}

	private void drawPlayer(Graphics g) {

		for (Player p : players) {
			if (p.isAlive()) {
//				g.setColor(Color.white);
//
//				if (p.isImmortal()) {
//					g.setColor(Color.gray);
//				}
//
//				// The x,y coordinates doesn't matter since we set the center
//				// coordinates after
//				Rectangle rect = new Rectangle(0, 0, blockSide, blockSide);
				FPosition pos = p.getGamePosition();
//				rect.setCenterX(pos.getX() * blockSide + startX);
//				rect.setCenterY(pos.getY() * blockSide + startY);
//
//				g.fill(rect);
//
//				// TODO remove, just for test, added to determine orientation of
//				// final picture
//				Circle circle = new Circle(0, 0, 2);
//				circle.setCenterX(pos.getX() * blockSide
//						+ (p.getDirection().getX() * 20) + startX);
//				circle.setCenterY(pos.getY() * blockSide
//						+ (p.getDirection().getY() * 20) + startY);
//				g.setColor(Color.black);
//				g.fill(circle);
				drawImage(pos.getX()-0.5F, pos.getY()-0.5F, p.getImageString(), g);
			}
		}

	}

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

	private void drawImage(float x, float y, String s, Graphics g) {
		// the players position is related to matrix so compensated is needed
		x *= blockSide;
		y *= blockSide;
		Image i = imgs.getImage(s);
		g.drawImage(i, x + startX, y + startY);
	}

}
