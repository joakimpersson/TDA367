package com.github.joakimpersson.tda367.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;


/**
 * 
 * @author joakimpersson
 *
 */
public abstract class Bomb extends OpaqueTile{

	private ActionListener actionListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}
	};
	
	private Player player;
	private Timer timer;
	
	public Bomb(Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * Replaces the getBombRange function and is now 
	 * only public
	 */
	public abstract void explode();
}
