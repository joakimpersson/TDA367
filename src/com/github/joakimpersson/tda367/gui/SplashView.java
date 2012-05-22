package com.github.joakimpersson.tda367.gui;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.github.joakimpersson.tda367.gui.guiutils.GUIUtils;

public class SplashView implements IView {
	private ImageLoader imageLoader = null;
	private Animation textAnimation = null;

	/**
	 * Creates a new view representing the splash screen for game
	 * 
	 */
	public SplashView() {
		init();
	}

	/**
	 * Responsible for fetching images for the splash screen
	 */
	private void init() {
		imageLoader = ImageLoader.getInstance();
		Image imageOne = imageLoader.getImage("splash/text");
		Image imageTwo = imageLoader.getImage("splash/noText");
		Image[] textFrames = { imageOne, imageTwo };
		int duration = 600;
		textAnimation = new Animation(textFrames, duration);
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		GUIUtils.drawImage(0, 0, "splash/bg", g);
		textAnimation.draw(330, 580);
	}

	@Override
	public void enter() {
	}

}
