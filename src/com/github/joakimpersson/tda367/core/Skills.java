package com.github.joakimpersson.tda367.core;


/**
 * 
 * @author Viktor
 *
 */
public class Skills {
	
	public enum Attributes {
		SPEED, BOMBRANGE, BOMBSTACK, HP
	}
	
	private int[] speed, bombRange, maxBombStack, maxHP;
	private int maxDuration;
	
	public Skills(int maxDuration) {
		this.maxDuration = maxDuration;
		speed  = new int[maxDuration];
		bombRange  = new int[maxDuration];
		maxBombStack  = new int[maxDuration];
		maxHP  = new int[maxDuration];
		
	}
	
	public void upgrade(int value, int duration, Attributes attribute) {
		if(duration > maxDuration) {
			throw new IndexOutOfBoundsException();
		}
		switch(attribute) {
		case SPEED:
			speed[duration] = speed[duration] + value;
			break;
			
		case BOMBSTACK:
			
			break;
			
		case BOMBRANGE:
			
			break;
			
		case HP:
			
			break;
		}
	}
	
	public int getMax(Attributes attribute) {
		return 0;
	}
	
	public void totalReset() {
		
	}
	
	public void reset(int duration) {
		
	}
	
	private void resetAttribute(Attributes attribute) {
		
	}
	
	public String toString() {
		return "";
	}
	
}
