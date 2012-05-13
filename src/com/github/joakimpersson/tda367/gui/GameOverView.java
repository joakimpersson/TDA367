package com.github.joakimpersson.tda367.gui;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.github.joakimpersson.tda367.model.BombermanModel;
import com.github.joakimpersson.tda367.model.IBombermanModel;
import com.github.joakimpersson.tda367.model.player.Player;

/**
 * 
 * @author joakimpersson
 * 
 */
public class GameOverView implements IView {

	private List<Player> players = null;
	private IBombermanModel model = null;

	public GameOverView() {
		init();
	}

	private void init() {
		model = BombermanModel.getInstance();
	}

	@Override
	public void enter() {
		players = model.getPlayers();
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		

	}

}
