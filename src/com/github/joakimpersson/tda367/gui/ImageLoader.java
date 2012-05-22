package com.github.joakimpersson.tda367.gui;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * A class handling all the imageloading in to the game.
 * 
 * @author adderollen
 * 
 */
public class ImageLoader {

	private static final String PATH = "res/images/";
	private static ImageLoader instance = null;

	private Map<String, Image> imageMap;

	/**
	 * Creating an ImageLoader to load all the images in to the game.
	 */
	private ImageLoader() {
		imageMap = new HashMap<String, Image>();
		loadDirectory(PATH);
	}

	/**
	 * Get the instance of ImageLoader
	 * 
	 * @return The ImageLoader instance.
	 */
	public static ImageLoader getInstance() {
		if (instance == null) {
			instance = new ImageLoader();
		}

		return instance;
	}

	/**
	 * Load a give directory in to the game.
	 * 
	 * @param path
	 *            The directory that will be loaded.
	 */
	private void loadDirectory(String path) {
		String directoryString = path.substring(PATH.length(), path.length());
		File directory = new File(path);
		for (File child : directory.listFiles()) {
			if (".".equals(child.getName()) || "..".equals(child.getName())) {
				continue;
			} else if (child.isDirectory()) {
				loadDirectory(path + child.getName() + "/");
				continue;
			}
			loadImage(directoryString + child.getName());
		}
	}

	/**
	 * Load an image with a given name.
	 * 
	 * @param imageName
	 *            The name of the image that will be load.
	 */
	private void loadImage(String imageName) {
		try {
			Image image = new Image(PATH + imageName);
			image.setFilter(Image.FILTER_NEAREST);
			imageMap.put(imageName.substring(0, imageName.length() - 4), image);
		} catch (SlickException e) {
			System.out.println("File not found: " + imageName);
		}
	}

	/**
	 * Get an image with a given name.
	 * 
	 * @param imageName
	 *            The name of the image.
	 * @return The image with the given name.
	 */
	public Image getImage(String imageName) {
		return imageMap.get(imageName);
	}

}
