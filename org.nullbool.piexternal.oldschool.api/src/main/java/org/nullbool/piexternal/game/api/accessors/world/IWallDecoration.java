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

package org.nullbool.piexternal.game.api.accessors.world;

import org.nullbool.piexternal.game.api.accessors.entity.IRenderable;

public interface IWallDecoration extends ISceneComponent {
	IRenderable getMarkerRenderable1();

	IRenderable getMarkerRenderable2();

	int getStrictX();

	void setStrictX(int var1);

	int getStrictY();

	void setStrictY(int var1);

	int getPlane();

	void setPlane(int var1);

	int getHash();

	void setHash(int var1);

	int getUid();

	void setUid(int var1);

	int getOrientation1();

	void setOrientation1(int var1);

	int getOrientation2();

	void setOrientation2(int var1);
}
