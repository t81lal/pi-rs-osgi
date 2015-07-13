package org.nullbool.piexternal.game.api.meta;

import java.awt.Point;

import org.nullbool.piexternal.game.api.Menu;
import org.nullbool.piexternal.game.api.Mouse;
import org.nullbool.piexternal.game.api.Utilities;

/**
 * @author Bibl (don't ban me pls)
 * @created 7 Jul 2015 16:01:57
 */
public abstract interface Interactable {

	Point getScreenPoint();

	public default void interact() {
		interact(0, 0);
	}

	public default boolean interact(String action) {
		return interact(action, 0, 0);
	}

	public default boolean interact(String action, String option) {
		return interact(action, option, 0, 0);
	}

	public default Point getActionPoint(int x, int y) {
		Point point = this.getScreenPoint();
		point.x += x;
		point.y += y;
		return point;
	}

	public default void interact(int x, int y) {
		Point point = getActionPoint(x, y);
		Mouse mouse = Mouse.get();
		mouse.move(point);
		Utilities.sleep((long) Utilities.next(600, 1200));
		mouse.click(point, false);
	}

	public default boolean interact(String action, int x, int y) {
		Point point = null;
		boolean success = false;
		Mouse mouse = Mouse.get();

		for (int count = 0; count < 50 && !success; ++count) {
			mouse.move(point = getActionPoint(x, y));
			Utilities.sleep((long) Utilities.next(30, 60));
			if (Menu.getIndex(action, true) != -1 && !Menu.isVisible()) {
				mouse.click(point, true);
				Utilities.sleep((long) Utilities.next(300, 500));
				point = Menu.getInteractingPoint(action, true);
				mouse.move(point);
				Utilities.sleep(700L);
				mouse.click(point, false);
				success = point.x != -1 && point.equals(mouse.getPoint());
			}
		}

		return success;
	}

	public default boolean interact(String action, String opt, int x, int y) {
		Point point = null;
		boolean success = false;
		Mouse mouse = Mouse.get();

		for (int count = 0; count < 50 && !success; ++count) {
			mouse.move(point = getActionPoint(x, y));
			Utilities.sleep((long) Utilities.next(30, 60));
			if (Menu.getIndex(action, opt) != -1 && !Menu.isVisible()) {
				mouse.click(point, true);
				Utilities.sleep((long) Utilities.next(300, 500));
				point = Menu.getInteractingPoint(action, opt);
				mouse.move(point);
				Utilities.sleep(700L);
				mouse.click(point, false);
				success = point.x != -1 && point.equals(mouse.getPoint());
			}
		}

		return success;
	}
}