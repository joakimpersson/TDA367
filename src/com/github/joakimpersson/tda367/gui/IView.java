package com.github.joakimpersson.tda367.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
/**
 * 
 * @author joakimpersson
 *
 */
public interface IView {
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException;
}
