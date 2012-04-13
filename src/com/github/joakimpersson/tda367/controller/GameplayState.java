package com.github.joakimpersson.tda367.controller;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.github.joakimpersson.tda367.gui.GameplayView;
import com.github.joakimpersson.tda367.gui.IView;
import com.github.joakimpersson.tda367.model.BombermanModel;
import com.github.joakimpersson.tda367.model.IBombermanModel;
import com.github.joakimpersson.tda367.model.constants.PlayerAction;
import com.github.joakimpersson.tda367.model.player.Player;

/**
 * 
 * @author joakimpersson
 * 
 */
public class GameplayState extends BasicGameState {

	private int stateID = -1;
	private IBombermanModel model = null;
	private IView view = null;

	private List<Player> players = null;

	public GameplayState(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		model = BombermanModel.getInstance();
		view = new GameplayView();
		players = model.getPlayers();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		view.render(container, g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {

		Input input = container.getInput();

		// TODO jocke only used during development
		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			container.exit();
		}

		// player 1 movement/bomb
		
		Player p1 = players.get(0);
		
		if(input.isKeyDown(Input.KEY_SPACE)) {
			model.updateGame(p1, PlayerAction.PlaceBomb);
		}
		
		if (input.isKeyDown(Input.KEY_UP)) {
			model.updateGame(p1, PlayerAction.MoveUp);
		} else if (input.isKeyDown(Input.KEY_DOWN)) {
			model.updateGame(p1, PlayerAction.MoveDown);
		} else if (input.isKeyDown(Input.KEY_LEFT)) {
			model.updateGame(p1, PlayerAction.MoveLeft);
		} else if (input.isKeyDown(Input.KEY_RIGHT)) {
			model.updateGame(p1, PlayerAction.MoveRight);
		} 

	}

	@Override
	public int getID() {
		return stateID;
	}

}
