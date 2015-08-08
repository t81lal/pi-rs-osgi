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

package org.nullbool.piexternal.game.api.wrappers.world;

import java.util.ArrayList;
import java.util.List;

import org.nullbool.piexternal.game.api.accessors.world.IGameObject;
import org.nullbool.piexternal.game.api.accessors.world.IGroundDecoration;
import org.nullbool.piexternal.game.api.accessors.world.IGroundObject;
import org.nullbool.piexternal.game.api.accessors.world.ITile;
import org.nullbool.piexternal.game.api.accessors.world.IWallDecoration;
import org.nullbool.piexternal.game.api.accessors.world.IWallObject;
import org.nullbool.piexternal.game.api.wrappers.collection.Node;

/**
 * @author Bibl (don't ban me pls) <br>
 * @created 20 Apr 2015 at 21:56:45 <br>
 */
public class Tile extends Node<ITile> implements ITile {

	public Tile(ITile _tile) {
		super(_tile);
	}

	@Override
	public GroundDecoration getGroundDecorations() {
		IGroundDecoration _dec = _node.getGroundDecorations();
		if (_dec == null)
			return null;
		return new GroundDecoration(_dec);
	}

	@Override
	public GroundObject getGroundObjects() {
		IGroundObject _obj = _node.getGroundObjects();
		if (_obj == null)
			return null;
		return new GroundObject(_obj);
	}

	@Override
	public GameObject[] getObjects() {
		IGameObject[] _objects = _node.getObjects();
		final int length = _objects.length;
		List<GameObject> objects = new ArrayList<GameObject>();
		for (int i = 0; i < length; i++) {
			IGameObject obj = _objects[i];
			if (obj != null)
				objects.add(new GameObject(obj));
		}
		return objects.toArray(new GameObject[objects.size()]);
	}

	@Override
	public WallDecoration getWallDecorations() {
		IWallDecoration _obj = _node.getWallDecorations();
		if (_obj == null)
			return null;
		return new WallDecoration(_obj);
	}

	@Override
	public WallObject getWallObjects() {
		IWallObject _obj = _node.getWallObjects();
		if (_obj == null)
			return null;
		return new WallObject(_obj);
	}
}
