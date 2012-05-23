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
import com.github.joakimpersson.tda367.gui.guiutils.ImageLoader;

/**
 * Sets up the players settings such as name and actionbutton
 * 
 * @author rekoil
 * @modified joakimpersson, adderollen
 */
public class SetupGameView implements INavigateableView {

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

	/**
	 * Create the SetupGameView in a given container
	 * 
	 * @param container
	 *            The container that the view will be created in
	 */
	public SetupGameView(GameContainer container) {

		init();
		field = new TextField(container, bigFont,
				container.getWidth() / 2 - 100, 240, 200, 30);
		field.setBorderColor(red);

	}

	/**
	 * Initiates the SetupGameView.
	 */
	private void init() {
		try {
			bigFont = GUIUtils.getBigFont();
			smlFont = GUIUtils.getSmlFont();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		imgs = ImageLoader.getInstance();
	}

	@Override
	public void enter() {
		stage = 0;
		names = new ArrayList<String>();
	}

	@Override
	public void render(GameContainer container, Graphics g, int selection)
			throws SlickException {
		GUIUtils.drawImage(0, 0, "bg", g);
		int posY = 200;
		g.setColor(white);
		g.setFont(smlFont);
		if (stage == 0) {
			drawCenteredString("Select number of players:", posY, g);

			posY += yDelta;
			int index = 1;
			while (index < 4) {
				index++;
				if (selection == index) {
					g.setColor(cyan);
				} else if (index > possiblePlayers) {
					g.setColor(gray);
				}
				drawCenteredString(index + " players", posY, g);
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

	/**
	 * Draw the controller info images
	 * 
	 * @param width
	 *            The width of the container
	 * @param g
	 *            The graphicals context to draw the images with
	 */
	private void drawControllerInfo(int width, Graphics g) {
		g.drawImage(imgs.getImage("info/keyboard"), width / 2 - 404, 300);
		g.drawImage(imgs.getImage("info/xbox"), width / 2 + 20, 300);
		g.drawImage(imgs.getImage("info/controls-legend"), 50, 180);
	}

	/**
	 * Draw the state where the players enter their action button.
	 * 
	 * @param playerName
	 *            The player that will be asked to enter action button.
	 * @param color
	 *            The players color
	 * @param y
	 *            The y-postion where to draw the text
	 * @param g
	 *            The graphicals context to draw with
	 */
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

	/**
	 * Draws a centered String.
	 * 
	 * @param string
	 *            The string that will be drawn
	 * @param posY
	 *            The y-position the string will be drawn at
	 * @param g
	 *            The graphical context to draw with
	 */
	private void drawCenteredString(String string, int posY, Graphics g) {
		g.drawString(string,
				GUIUtils.getStringCenterX(string, GUIUtils.getGameWidth(), g),
				posY);
	}

	/**
	 * Start a creation of a player.
	 * 
	 * @param players
	 *            The player that will be created, given by index
	 */
	public void startPlayerCreation(int players) {
		playersSelected = players;
		index = 1;
		stage++;
		resetField();
	}

	/**
	 * Sets the possible player amount.
	 * 
	 * @param numberOfPlayers
	 *            The possible player amount
	 */
	public void setPossiblePlayers(int numberOfPlayers) {
		this.possiblePlayers = numberOfPlayers;
	}

	/**
	 * Method called by the controller when it is time to assign controllers.
	 */
	public void assignControllers() {
		index = 1;
		stage++;
	}

	/**
	 * Method called by the controller when a player has been created, its
	 * purpose is to store the name for easy access by the controller when the
	 * controls get assigned.
	 */
	public void playerCreated() {
		names.add(field.getText());
		resetField();
		incIndex();
	}

	/**
	 * Resets the text field
	 */
	private void resetField() {
		field.setText("");
		field.setFocus(true);
	}

	/**
	 * Method called by the controller to get the name from the text field.
	 * 
	 * @return The players name
	 */
	public String getName() {
		return field.getText();
	}

	/**
	 * Method called by the controller to get the index of the player during
	 * player creation.
	 * 
	 * @return the player index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Increases the index after player creation
	 */
	public void incIndex() {
		index++;
	}

	/**
	 * Verifies if a name is valid by it's pixel length in the smlFont font
	 * 
	 * @return whether the name is too long or not
	 */
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
