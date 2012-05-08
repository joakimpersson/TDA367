package com.github.joakimpersson.tda367.model.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 
 * @author joakimpersson
 * 
 */
public class FileScanner {

	private FileScanner() {
	}

	public static void writeOjbect(String path, Object object) {
		File file = new File(path);
		try {
			FileOutputStream outFile = new FileOutputStream(file);
			ObjectOutputStream dest = new ObjectOutputStream(outFile);

			try {
				dest.writeObject(object);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					dest.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Object readObject(String path) {
		Object object = null;
		File file = new File(path);
		if (file.exists()) {
			try {
				FileInputStream inFile = new FileInputStream(file);
				ObjectInputStream source = new ObjectInputStream(inFile);

				try {
					object = source.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						source.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return object;
	}
}
