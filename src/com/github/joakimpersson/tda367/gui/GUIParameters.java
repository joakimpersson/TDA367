package com.github.joakimpersson.tda367.gui;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Font;
import org.newdawn.slick.SlickException;

/**
 * 
 * @author joakimpersson
 * 
 */
public enum GUIParameters {
	INSTANCE;

	/**
	 * Get the font used for "big" headers in the game
	 * 
	 * @return The font for headers
	 * @throws SlickException
	 */
	public Font getBigFont() throws SlickException {
		return new AngelCodeFont("res/fonts/minecraft_big.fnt",
				"res/fonts/minecraft_big.tga");
	}

	/**
	 * Get the font used for text and smaller headers in the game
	 * 
	 * @return The font for samller fonts
	 * @throws SlickException
	 */
	public Font getSmlFont() throws SlickException {
		return new AngelCodeFont("res/fonts/minecraft_sml.fnt",
				"res/fonts/minecraft_sml.tga");
	}

	/**
	 * Get the total width of the games screen
	 * 
	 * @return The games width
	 */
	public int getGameWidth() {
		return 955;
	}

	/**
	 * Get the total height of the games screen
	 * @return The games heights
	 */
	public int getGameHeight() {
		return 650;
	}
}
