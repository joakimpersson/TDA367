package com.github.joakimpersson.tda367.gui;

import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.github.joakimpersson.tda367.model.BombermanModel;
import com.github.joakimpersson.tda367.model.IBombermanModel;
import com.github.joakimpersson.tda367.model.constants.PointGiver;
import com.github.joakimpersson.tda367.model.player.Player;

public class RoundInfoView implements IView {

	private static final int width = 850;
	private static final int height = 550;
	private int startX;
	private int startY;
	private IBombermanModel model = null;
	private List<Player> players = null;
	private Font smlFont;

	public RoundInfoView() {
		init();
	}

	private void init() {
		model = BombermanModel.getInstance();
		try {
			smlFont = GUIParameters.INSTANCE.getSmlFont();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void enter() {
		startX = GUIParameters.INSTANCE.getGameWidth() / 2 - width / 2;
		startY = GUIParameters.INSTANCE.getGameHeight() / 2 - height / 2;
		players = model.getPlayers();
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		g.setFont(smlFont);
		int x = startX + 50;
		int deltaX = width / players.size();
		int midX = startX + width / 2;

		drawBackgroundContainer(g);

		int y = startY + 40;
		drawTitle(g, midX, y);

		y += 60;

		for (Player p : players) {
			drawPlayerStats(p, x, y, g);
			x += deltaX;
		}
	}

	private void drawTitle(Graphics g, int midX, int y) {
		// should be template and say round over, matchover etc
		String str = "Skumme dummy()";
		int strWidth = g.getFont().getWidth(str);
		g.drawString(str, midX - strWidth / 2, y);

	}

	private void drawBackgroundContainer(Graphics g) {
		g.setColor(Color.black);

		g.fillRoundRect(startX, startY, width, height, 25);
		g.setColor(Color.white);
		g.drawRoundRect(startX, startY, width, height, 25);

	}

	private String getPlayerString(Player p, PointGiver pg) {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(pg.name());
		strBuilder.append(": ");
		strBuilder.append(p.getDestroyedPointGiver(pg));
		strBuilder.append(" st");
		return strBuilder.toString();
	}

	private void drawPlayerStats(Player p, int x, int y, Graphics g) {
		PointGiver[] pointGivers = PointGiver.values();

		int yDelta = 40;

		g.drawString(p.getName(), x, y);

		y += yDelta;

		g.drawString("Destroyed/Killed", x, y);

		for (PointGiver pg : pointGivers) {
			y += yDelta;

			String str = getPlayerString(p, pg);

			g.drawString(str, x, y);

		}

	}
}
