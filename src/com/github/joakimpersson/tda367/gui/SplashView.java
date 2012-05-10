package com.github.joakimpersson.tda367.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class SplashView implements IView {
	private int count = 0;

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		g.drawImage(ImageLoader.getInstance().getImage("splash/bg"), 0, 0);
		if (count >= 30) {
			g.drawImage(ImageLoader.getInstance().getImage("splash/text"), 330, 580);
			if (count >= 60) {
				count = 0;
			}
		}
		count++;
	}
	
	@Override
	public void enter() {
	}

}
