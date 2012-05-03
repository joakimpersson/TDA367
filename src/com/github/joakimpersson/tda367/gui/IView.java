package com.github.joakimpersson.tda367.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * 
 * @author joakimpersson
 * 
 */
public interface IView {
	/**
	 * Render this view to the game's graphics context
	 * 
	 * @param container
	 *            The container holding the game
	 * @param g
	 *            The graphics context to render to
	 * @throws SlickException
	 *             Indicates a failure to render an gui object
	 */
	public void render(GameContainer container, Graphics g)
			throws SlickException;

	/**
	 * Notification that we've entered this game state
	 */
	public void enter();
}
