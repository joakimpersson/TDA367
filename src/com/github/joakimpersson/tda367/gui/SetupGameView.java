package com.github.joakimpersson.tda367.gui;

import static org.newdawn.slick.Color.*;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import org.newdawn.slick.gui.TextField;

public class SetupGameView {

	private int yDelta = 30;
	private int progress = 0;
	private int possiblePlayers, playersSelected;

	private Font font;
	private TextField field;
	private int index;

	public SetupGameView(int possiblePlayers, GameContainer container) {
		try {
			font = new AngelCodeFont("res/font.fnt", "res/font.tga");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		this.possiblePlayers = possiblePlayers;
		field = new TextField(container, font, container.getWidth() / 2 - 180, 270, 200, 35);
		init();
	}

	private void init() {
	}

	public void render(GameContainer container, Graphics g, int selection) throws SlickException {
		int posX = container.getWidth() / 2 - 210;
		int posY = 200;
		if (progress == 0) {
			g.setColor(white);
			g.drawString("Select number of players:", posX, posY);

			posY += 25;
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
		} else if (progress == 1) {
			g.setColor(white);
			g.drawString("Player " + index + ", type your name and press", posX, posY);
			posY += 25;
			g.drawString("action on your preferred controller", posX, posY);
			field.render(container, g);
		}
	}

	public void startPlayerCreation(int players) {
		playersSelected = players;
		index = 1;
		resetField();
		progress++;
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

	public void playerCreated() {
		resetField();
		index++;
	}
}
