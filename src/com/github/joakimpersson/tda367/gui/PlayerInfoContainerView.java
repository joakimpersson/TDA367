package com.github.joakimpersson.tda367.gui;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.github.joakimpersson.tda367.Main;
import com.github.joakimpersson.tda367.model.BombermanModel;
import com.github.joakimpersson.tda367.model.IBombermanModel;
import com.github.joakimpersson.tda367.model.player.Player;

public class PlayerInfoContainerView implements IView {
	private int startX;
	private int startY;
	private final int panelWidth = 200;
	private IBombermanModel model = null;
	private List<PlayerInfoView> playersInfo = null;

	public PlayerInfoContainerView(int startX, int startY) {
		this.startX = startX;
		this.startY = startY;
		init();
	}

	private void init() {
		model = BombermanModel.getInstance();
		playersInfo = new ArrayList<PlayerInfoView>();
		List<Player> players = model.getPlayers();
		int panelHeight = Main.gameHeight / model.getPlayers().size();
		int x = 0;
		int y = 0;
		for (Player p : players) {
			PlayerInfoView view = new PlayerInfoView(p, x, y, panelWidth,
					panelHeight);
			playersInfo.add(view);
			y += panelHeight;
		}

	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		g.setColor(Color.pink);
		g.fillRect(startX, startY, panelWidth, container.getHeight());
		for (PlayerInfoView view : playersInfo) {
			view.render(container, g);
		}
	}
}
