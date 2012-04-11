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
		view.render(container, game, g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {

		Input input = container.getInput();

		// placing bombs
		if (input.isKeyPressed(Input.KEY_SPACE)) {
			model.updateGame(players.get(0), PlayerAction.PlaceBomb);
		}

		if (input.isKeyPressed(Input.KEY_2)) {
			model.updateGame(players.get(1), PlayerAction.PlaceBomb);
		}

		// moving player1
		if (input.isKeyPressed(Input.KEY_UP)) {
			model.updateGame(players.get(0), PlayerAction.MoveUp);
		}

		if (input.isKeyPressed(Input.KEY_DOWN)) {
			model.updateGame(players.get(0), PlayerAction.MoveDown);
		}

		if (input.isKeyPressed(Input.KEY_RIGHT)) {
			model.updateGame(players.get(0), PlayerAction.MoveRight);
		}

		if (input.isKeyPressed(Input.KEY_LEFT)) {
			model.updateGame(players.get(0), PlayerAction.MoveLeft);
		}

		if (input.isKeyPressed(Input.KEY_SPACE)) {
			model.updateGame(players.get(0), PlayerAction.PlaceBomb);
		}

		if (input.isKeyPressed(Input.KEY_2)) {
			model.updateGame(players.get(1), PlayerAction.PlaceBomb);
		}

		// moving player2
		if (input.isKeyPressed(Input.KEY_W)) {
			model.updateGame(players.get(1), PlayerAction.MoveUp);
		}

		if (input.isKeyPressed(Input.KEY_S)) {
			model.updateGame(players.get(1), PlayerAction.MoveDown);
		}

		if (input.isKeyPressed(Input.KEY_D)) {
			model.updateGame(players.get(1), PlayerAction.MoveRight);
		}

		if (input.isKeyPressed(Input.KEY_A)) {
			model.updateGame(players.get(1), PlayerAction.MoveLeft);
		}

		// TODO jocke only used during development
		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			container.exit();
		}

	}

	@Override
	public int getID() {
		return stateID;
	}

}
