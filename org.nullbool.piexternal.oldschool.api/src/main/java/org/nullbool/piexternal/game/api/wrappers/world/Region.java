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

import org.nullbool.piexternal.game.api.accessors.world.IRegion;
import org.nullbool.piexternal.game.api.accessors.world.ITile;

/**
 * @author Bibl (don't ban me pls) <br>
 * @created 20 Apr 2015 at 21:34:22 <br>
 */
public class Region implements IRegion {

	private final IRegion _region;

	public Region(IRegion _region) {
		this._region = _region;
	}

	@Override
	public Tile[][][] getTiles() {
		ITile[][][] _tiles = _region.getTiles();
		Tile[][][] tiles = new Tile[3][104][104];
		for (int y = 0; y < 3; y++) {
			for (int z = 0; z < 104; z++) {
				for (int x = 0; x < 104; x++) {
					ITile _tile = _tiles[y][z][x];
					if (_tile != null)
						tiles[y][z][x] = new Tile(_tile);
				}
			}
		}
		return tiles;
	}
}
