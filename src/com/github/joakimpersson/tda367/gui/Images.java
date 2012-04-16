package com.github.joakimpersson.tda367.gui;

import java.util.HashMap;

import javax.swing.ImageIcon;

public class Images {
	
	private static Images instance = null;
	
	private static HashMap<String, ImageIcon> imageMap = new HashMap();
	
	private Images() {
		initHashMap();
	}
	
	private void initHashMap() {
		imageMap.put("floor1", new ImageIcon("res/images/floor1.png"));
		imageMap.put("floor2", new ImageIcon("res/images/floor2.png"));
		imageMap.put("floor3", new ImageIcon("res/images/floor3.png"));
		imageMap.put("floor4", new ImageIcon("res/images/floor4.png"));
		imageMap.put("floor5", new ImageIcon("res/images/floor5.png"));
		imageMap.put("floor6", new ImageIcon("res/images/floor6.png"));
		imageMap.put("box1", new ImageIcon("res/images/box1.png"));
		imageMap.put("box2", new ImageIcon("res/images/box2.png"));
		imageMap.put("box3", new ImageIcon("res/images/box3.png"));
		imageMap.put("box4", new ImageIcon("res/images/box4.png"));
		imageMap.put("box5", new ImageIcon("res/images/box5.png"));
		imageMap.put("pillar", new ImageIcon("res/images/pillar.png"));
		imageMap.put("wall", new ImageIcon("res/images/wall.png"));
		imageMap.put("directedFire", new ImageIcon("res/images/directedFire.png"));
		imageMap.put("middleFire", new ImageIcon("res/images/middleFire.png"));
		imageMap.put("areaBomb", new ImageIcon("res/images/areaBomb.png"));
		imageMap.put("bomb", new ImageIcon("res/images/bomb.png"));
		imageMap.put("rangeUpItem", new ImageIcon("res/images/rangeUpItem.png"));
		imageMap.put("speedUpItem", new ImageIcon("res/images/speedUpItem.png"));
		imageMap.put("bombUpItem", new ImageIcon("res/images/bombUpItem.png"));
		
	}

	public static ImageIcon getImage(String image) {
		if (instance == null) {
			instance = new Images();
		}
		
		return imageMap.get(image);
	}

}
