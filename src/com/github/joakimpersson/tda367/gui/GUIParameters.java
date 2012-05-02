package com.github.joakimpersson.tda367.gui;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Font;
import org.newdawn.slick.SlickException;

public enum GUIParameters {
	INSTANCE;
	
	public Font getBigFont() throws SlickException {
		return new AngelCodeFont("res/fonts/minecraft_big.fnt", "res/fonts/minecraft_big.tga");
	}

	public Font getSmlFont() throws SlickException {
		return new AngelCodeFont("res/fonts/minecraft_sml.fnt", "res/fonts/minecraft_sml.tga");
	}

	public int getGameWidth() {
		return 950;
	}

	public int getGameHeight() {
		return 650;
	}
}
