/************************************************************************************
 * pi rs - a generic framework for loading Java Applets in a contained environment. *
 * Copyright (C) 2015  NullBool                                                     *
 *                                                                                  *
 * This program is free software: you can redistribute it and/or modify             *
 * it under the terms of the GNU General Public License as published by             *
 * the Free Software Foundation, either version 3 of the License, or                *
 * (at your option) any later version.                                              *
 *                                                                                  *
 * This program is distributed in the hope that it will be useful,                  *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of                   *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                    *
 * GNU General Public License for more details.                                     *
 *                                                                                  *
 * You should have received a copy of the GNU General Public License                *
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.            *
 ************************************************************************************/

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
