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

import java.awt.Point;
import java.awt.Rectangle;
import java.util.stream.IntStream;

/**
 * @author Bibl (don't ban me pls)
 * @created 6 Jul 2015 17:56:27
 */
public class Menu {

	public static int getX() {
		return OldschoolClient.client().getMenuX();
	}

	public static int getY() {
		return OldschoolClient.client().getMenuY();
	}

	public static int getWidth() {
		return OldschoolClient.client().getMenuWidth();
	}

	public static int getHeight() {
		return OldschoolClient.client().getMenuHeight();
	}

	public static int getMenuSize() {
		return OldschoolClient.client().getMenuSize();
	}

	public static boolean isVisible() {
		return OldschoolClient.client().isMenuOpen();
	}

	public static String[] getActions() {
		return OldschoolClient.client().getMenuActions();
	}

	public static String[] getOptions() {
		return OldschoolClient.client().getMenuOptions();
	}

	public static Rectangle getActionArea(String action, boolean cond) {
		boolean invalid = !isVisible() || getIndex(action, cond) < 0;
		return invalid ? null : calculate(getIndex(action, cond));
	}

	public static Rectangle getActionArea(String action, String option) {
		boolean invalid = !isVisible() || getIndex(action, option) < 0;
		return invalid ? null : calculate(getIndex(action, option));
	}

	private static Rectangle calculate(int i) {
		return new Rectangle(getX(), getY() + 18 + i * 15 + 1, getWidth(), 15);
	}

	public static Point getInteractingPoint(String action, boolean cond) {
		return calculatePoint(getActionArea(action, cond));
	}

	public static Point getInteractingPoint(String action, String option) {
		return calculatePoint(getActionArea(action, option));
	}

	private static Point calculatePoint(Rectangle r) {
		Point point = new Point(-1, -1);
		point.x = r != null ? (int) ((double) r.x + (double) r.width * Math.random()) : -1;
		point.y = r != null ? (int) ((double) r.y + (double) r.height * Math.random()) : -1;
		return point;
	}

	public static int getIndex(String action, boolean cond) {
		String[] actions = getCleanActions(cond);
		IntStream s = IntStream.range(0, actions.length);
		s = s.filter((x) -> {
			return actions[x].equalsIgnoreCase(action);
		});
		return s.findFirst().orElse(-1);
	}

	public static int getIndex(String act, String opt) {
		String[] a = getCleanActions(true);
		String[] o = getCleanActions(false);
		IntStream s = IntStream.range(0, a.length);
		s = s.filter((x) -> {
			return a[x].equals(act) && o[x].equals(opt);
		});
		return s.findFirst().orElse(-1);
	}

	public static String[] getCleanActions(boolean cond) {
		String[] cleanArray = new String[getMenuSize()];
		String[] bruteArray = cond ? getActions() : getOptions();
		int y = 0;

		for (int x = cleanArray.length - 1; x >= 0; ++y) {
			cleanArray[y] = bruteArray[x].replaceAll("\\<.+?\\>", "").trim();
			--x;
		}

		return cleanArray;
	}

}
