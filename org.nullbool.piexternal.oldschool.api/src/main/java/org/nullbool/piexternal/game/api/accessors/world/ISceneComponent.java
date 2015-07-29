package org.nullbool.piexternal.game.api.accessors.world;

import java.awt.Point;

import org.nullbool.piexternal.game.api.Calculations;
import org.nullbool.piexternal.game.api.meta.RSTile;

public interface ISceneComponent {

	int getId();

	String getName();

	String[] getActions();

	RSTile getPosition();

	default int dist() {
		return (int) Calculations.distance(getPosition());
	}

	default Point getScreenPoint() {
		return Calculations.getCenterPoint(getPosition());
	}

	default boolean isOnScreen() {
		return Calculations.isOnScreen(getScreenPoint());
	}
}
