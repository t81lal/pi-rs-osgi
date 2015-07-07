package org.nullbool.piexternal.game.api;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

/**
 * @author Bibl (don't ban me pls)
 * @created 7 Jul 2015 15:29:41
 */
public class Utilities {
	public static int next(int maximum) {
		return (int) ((double) maximum * Math.random() + 1.0D);
	}

	public static void sleep(int min, int max) {
		sleep((long) next(min, max));
	}

	public static int next(int minimum, int maximum) {
		return (int) ((double) (maximum - minimum) * Math.random() + (double) minimum);
	}

	public static Image getImage(String path) {
		URL url = Utilities.class.getResource("/images/" + path);
		return (new ImageIcon(url)).getImage();
	}

	public static void sleep(long milis) {
		try {
			Thread.sleep(milis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}