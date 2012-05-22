package com.github.joakimpersson.tda367.gui;

import static org.newdawn.slick.Color.cyan;
import static org.newdawn.slick.Color.gray;
import static org.newdawn.slick.Color.red;
import static org.newdawn.slick.Color.white;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.TextField;

import com.github.joakimpersson.tda367.gui.guiutils.GUIUtils;

/**
 * Sets up the players
 * 
 * @author rekoil
 * @modified joakimpersson
 */
public class SetupGameView {

	private int yDelta = 30;
	private int stage = -1;
	private int possiblePlayers = -1;
	private int playersSelected = -1;
	private int index;
	private int nameLength = 0;

	private Font bigFont = null;
	private Font smlFont = null;
	private TextField field;
	private List<String> names = null;
	private ImageLoader imgs = null;
	private String errorString = "";

	public SetupGameView(GameContainer container) {

		init();
		field = new TextField(container, bigFont,
				container.getWidth() / 2 - 100, 240, 200, 30);
		field.setBorderColor(red);

	}

	private void init() {
		try {
			bigFont = GUIUtils.getBigFont();
			smlFont = GUIUtils.getSmlFont();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		imgs = ImageLoader.getInstance();
	}

	public void enter() {
		stage = 0;
		names = new ArrayList<String>();
	}

	public void render(GameContainer container, Graphics g, int selection)
			throws SlickException {
		g.drawImage(imgs.getImage("bg"), 0, 0);
		int posY = 200;
		g.setColor(white);
		g.setFont(smlFont);
		if (stage == 0) {
			drawCenteredString("Select number of players:", posY, g);

			posY += yDelta;
			int i = 1;
			while (i < 4) {
				i++;
				if (selection == i) {
					g.setColor(cyan);
				} else if (i > possiblePlayers) {
					g.setColor(gray);
				}
				drawCenteredString(i + " players", posY, g);
				g.setColor(white);
				posY += yDelta;
			}
		} else if (stage == 1 && index <= playersSelected) {
			drawCenteredString("Player " + index
					+ ", type your name and press enter", posY, g);
			field.render(container, g);
			nameLength = g.getFont().getWidth(field.getText());
			if (!errorString.isEmpty()) {
				g.setColor(Color.red);
				drawCenteredString(errorString, 280, g);
			}
		} else if (stage == 2 && index <= names.size()) {
			drawCenteredAssignPlayerString(names.get(index - 1), Color.green,
					posY, g);
			drawControllerInfo(container.getWidth(), g);
		}
	}

	private void drawControllerInfo(int width, Graphics g) {
		g.drawImage(imgs.getImage("info/keyboard"), width / 2 - 404, 300);
		g.drawImage(imgs.getImage("info/xbox"), width / 2 + 20, 300);
		g.drawImage(imgs.getImage("info/controls-legend"), 50, 180);
	}

	private void drawCenteredAssignPlayerString(String playerName, Color color,
			int y, Graphics g) {
		int startX = GUIUtils.getStringCenterX(playerName
				+ " please press action", GUIUtils.getGameWidth(), g);
		int nameLength = g.getFont().getWidth(playerName);
		g.setColor(color);
		g.drawString(playerName, startX, y);
		g.setColor(white);
		g.drawString(" please press action", startX + nameLength + 2, y);
		y += yDelta;
		drawCenteredString("on your preferred controller!", y, g);
	}

	private void drawCenteredString(String string, int posY, Graphics g) {
		g.drawString(string,
				GUIUtils.getStringCenterX(string, GUIUtils.getGameWidth(), g),
				posY);
	}

	public void startPlayerCreation(int players) {
		playersSelected = players;
		index = 1;
		stage++;
		resetField();
	}

	public void setPossiblePlayers(int numberOfPlayers) {
		this.possiblePlayers = numberOfPlayers;
	}

	public void assignControllers() {
		index = 1;
		stage++;
	}

	public void playerCreated() {
		names.add(field.getText());
		resetField();
		incIndex();
	}

	private void resetField() {
		field.setText("");
		field.setFocus(true);
	}

	public String getName() {
		return field.getText();
	}

	public int getIndex() {
		return index;
	}

	public void incIndex() {
		index++;
	}

	public boolean verifyNameValidity() {
		String name = getName();
		if ("".equals(name)) {
			return false;
		}
		if (nameLength > 82) {
			errorString = name + " is too long, please use a shorter name!";
			field.setText("");
			return false;
		} else {
			errorString = "";
			return true;
		}
	}
}
