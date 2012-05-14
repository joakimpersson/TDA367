package com.github.joakimpersson.tda367.gui.guiutils;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * 
 * @author joakimpersson
 * 
 */
public class GUIUtils {
	private GUIUtils() {
	}

	/**
	 * Get the font used for "big" headers in the game
	 * 
	 * @return The font for headers
	 * @throws SlickException
	 */
	public static Font getBigFont() throws SlickException {
		return new AngelCodeFont("res/fonts/minecraft_big.fnt",
				"res/fonts/minecraft_big.tga");
	}

	/**
	 * Get the font used for text and smaller headers in the game
	 * 
	 * @return The font for samller fonts
	 * @throws SlickException
	 */
	public static Font getSmlFont() throws SlickException {
		return new AngelCodeFont("res/fonts/minecraft_sml.fnt",
				"res/fonts/minecraft_sml.tga");
	}

	/**
	 * Get the total width of the games screen
	 * 
	 * @return The games width
	 */
	public static int getGameWidth() {
		return 955;
	}

	/**
	 * Get the total height of the games screen
	 * 
	 * @return The games heights
	 */
	public static int getGameHeight() {
		return 650;
	}

	/**
	 * A util method for centering the texts midpoint in the panel/container
	 * 
	 * @param str
	 *            The text that needs to be centered
	 * @param width
	 *            The width of the panel/container
	 * @param g
	 *            The games graphics object
	 * @return The x coordinate for the string to be draws from
	 */
	public static int getStrinCenterX(String str, int width, Graphics g) {
		int strWidth = g.getFont().getWidth(str);
		return (width / 2) - (strWidth / 2);
	}

	/**
	 * A util method for centering the texts midpoint in the panel/container
	 * 
	 * @param str
	 *            The text that needs to be centered
	 * @param height
	 *            The height of the panel/container
	 * @param g
	 *            The graphics context to render to
	 * @return The y coordinate for the string to be draws from
	 */
	public static int getStrinCenterY(String str, int height, Graphics g) {
		int strHeight = g.getFont().getHeight(str);
		return height / 2 - strHeight / 2;
	}
}
