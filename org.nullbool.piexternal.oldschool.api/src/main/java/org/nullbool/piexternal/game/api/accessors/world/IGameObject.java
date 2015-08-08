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

public interface IGameObject extends ISceneComponent {
	int getHash();

	void setHash(int var1);

	int getPlane();

	void setPlane(int var1);

	int getStrictX();

	void setStrictX(int var1);

	int getStrictY();

	void setStrictY(int var1);

	int getLocalX();

	void setLocalX(int var1);

	int getLocalY();

	void setLocalY(int var1);

	int getWidth();

	void setWidth(int var1);

	int getHeight();

	void setHeight(int var1);

	int getOrientation();

	void setOrientation(int var1);

	int getFlags();

	void setFlags(int var1);

	IRenderable getMarkedRenderable();
}
