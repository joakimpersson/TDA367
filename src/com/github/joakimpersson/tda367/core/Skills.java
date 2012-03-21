package com.github.joakimpersson.tda367.core;

import java.util.ArrayList;
import java.util.*;

/**
 * 
 * @author Viktor
 *
 */
public class Skills {
	
	public enum Attributes {
		SPEED, BOMBRANGE, BOMBSTACK, HP
	}
	
	private List<Integer> speed, bombRange, maxBombStack, maxHP;
	
	public Skills() {
		speed  = new ArrayList<Integer>();
		bombRange  = new ArrayList<Integer>();
		maxBombStack  = new ArrayList<Integer>();
		maxHP  = new ArrayList<Integer>();
		
	}
	
	public void upgrade(int value, Attributes attribute) {
		
	}
	
	public int getMax(Attributes attribute) {
		return 0;
	}
	
	
}
