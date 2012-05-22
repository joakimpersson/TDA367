package com.github.joakimpersson.tda367.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * An interface for views that are supposed to support navigation
 * 
 * @author joakimpersson
 * 
 */
public interface INavigateableView {

	/**
	 * Render this view to the game's graphics context
	 * 
	 * @param container
	 *            The container holding the game
	 * @param g
	 *            The graphics context to render to
	 * @param selection
	 *            The index that is selected
	 * @throws SlickException
	 *             Indicates a failure to render an gui object
	 */
	public void render(GameContainer container, Graphics g, int selection)
			throws SlickException;

	/**
	 * Notification that we've entered this game state
	 */
	public void enter();
}
