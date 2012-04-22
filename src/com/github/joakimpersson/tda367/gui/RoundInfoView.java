package com.github.joakimpersson.tda367.gui;

import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.github.joakimpersson.tda367.model.BombermanModel;
import com.github.joakimpersson.tda367.model.IBombermanModel;
import com.github.joakimpersson.tda367.model.player.Player;

public class RoundInfoView implements IView {

	private final int width = 650;
	private final int height = 450;
	private int startX;
	private int startY;
	private IBombermanModel model = null;
	private List<Player> players = null;

	public RoundInfoView() {
		init();
	}

	private void init() {
		startX = GUIParameters.INSTANCE.getGameWidth() / 2 - width / 2;
		startY = GUIParameters.INSTANCE.getGameHeight() / 2 - height / 2;
		model = BombermanModel.getInstance();
		players = model.getPlayers();
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		g.setColor(Color.black);

		g.fillRoundRect(startX, startY, width, height, 25);
		g.setColor(Color.white);
		g.drawRoundRect(startX, startY, width, height, 25);

		int x = startX + width / 4;
		int midX = startX + width / 2 - 60;
		
		int y = startY + 40;

		String str = "Skumme dummy()";
		g.drawString(str, midX, y);

		y += 60;
		for (Player p : players) {
			g.drawString(p.getName(), x, y);
			x += 200;
		}
	}
}
