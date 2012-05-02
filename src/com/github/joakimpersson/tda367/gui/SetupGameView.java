package com.github.joakimpersson.tda367.gui;

import static org.newdawn.slick.Color.cyan;
import static org.newdawn.slick.Color.gray;
import static org.newdawn.slick.Color.red;
import static org.newdawn.slick.Color.white;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.TextField;

public class SetupGameView {

	private int yDelta = 30;
	private int stage = 0;
	private int possiblePlayers, playersSelected;

	private Font bigFont, smlFont;
	private TextField field;
	private int index;
	private List<String> names = new ArrayList<String>();

	public SetupGameView(int possiblePlayers, GameContainer container) {
		try {
			bigFont = GUIParameters.INSTANCE.getBigFont();
			smlFont = GUIParameters.INSTANCE.getSmlFont();
		} catch (SlickException e) { }
		this.possiblePlayers = possiblePlayers;
		field = new TextField(container, bigFont, container.getWidth() / 2 - 180, 240, 200, 30);
		field.setBorderColor(red);
		init();
	}

	private void init() {
	}

	public void render(GameContainer container, Graphics g, int selection) throws SlickException {
		int posX = container.getWidth() / 2 - 210;
		int posY = 200;
		g.setColor(white);
		g.setFont(smlFont);
		if (stage == 0) {
			g.drawString("Select number of players:", posX, posY);

			posY += yDelta;
			int i = 1;
			while (i < 4) {
				i++;
				if (selection == i) {
					g.setColor(cyan);
				} else if (i > possiblePlayers) {
					g.setColor(gray);
				}
				g.drawString(i + " players", posX+80, posY);
				g.setColor(white);
				posY += yDelta;
			}
		} else if (stage == 1 && index <= playersSelected) {
			g.drawString("Player " + index + ", type your name and press enter", posX, posY);
			field.render(container, g);
		} else if (stage == 2 && index <= names.size()) {
			g.drawString(names.get(index-1) + " please press action", posX, posY);
			posY += yDelta;
			g.drawString("on your preferred controller!", posX, posY);
		}
	}

	public void startPlayerCreation(int players) {
		playersSelected = players;
		index = 1;
		stage++;
		resetField();
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

	public boolean nameFilledIn() {
		return !"".equals(field.getText());
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
}
