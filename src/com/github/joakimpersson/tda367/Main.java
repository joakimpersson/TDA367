package com.github.joakimpersson.tda367;

import java.io.File;

import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import com.github.joakimpersson.tda367.controller.BombermanGame;

/**
 * 
 * @author joakimpersson
 *
 */
public class Main {
	public static void main(String[] args) {
		/*
		 * Dynamically uses the correct native files for lwjgl
		 */
		System.setProperty("org.lwjgl.librarypath",
				new File(new File(new File(System.getProperty("user.dir"),
						"lib"), "native"), LWJGLUtil.getPlatformName())
						.getAbsolutePath());
		System.setProperty("net.java.games.input.librarypath",
				System.getProperty("org.lwjgl.librarypath"));
		try {
			AppGameContainer app = new AppGameContainer(new BombermanGame(
					"Joakim e kung"));

			app.setDisplayMode(750, 650, false);
			// make sure that we are using the players screen
			app.setVSync(true);

			// launch the game
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
