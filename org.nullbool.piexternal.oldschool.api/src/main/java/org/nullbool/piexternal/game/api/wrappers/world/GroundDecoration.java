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

import org.nullbool.piexternal.game.api.OldschoolClient;
import org.nullbool.piexternal.game.api.accessors.entity.IRenderable;
import org.nullbool.piexternal.game.api.accessors.world.IGroundDecoration;
import org.nullbool.piexternal.game.api.meta.RSTile;
import org.nullbool.piexternal.game.api.wrappers.definition.ObjectDefinition;

/**
 * @author Bibl (don't ban me pls) <br>
 * @created 20 Apr 2015 at 21:29:13 <br>
 */
public class GroundDecoration implements IGroundDecoration {

	private final IGroundDecoration _deco;
	private final ObjectDefinition objectDefinition;

	public GroundDecoration(IGroundDecoration _deco) {
		this._deco = _deco;
		this.objectDefinition = new ObjectDefinition(OldschoolClient.loadObjDefinition(getId()));
	}

	@Override
	public IRenderable getTopRenderable() {
		return _deco.getTopRenderable();
	}

	@Override
	public IRenderable getMiddleRenderable() {
		return _deco.getMiddleRenderable();
	}

	@Override
	public IRenderable getBottomRenderable() {
		return _deco.getBottomRenderable();
	}

	@Override
	public int getPlane() {
		return _deco.getPlane();
	}

	@Override
	public int getRegionX() {
		return _deco.getRegionX();
	}

	@Override
	public int getRegionY() {
		return _deco.getRegionY();
	}

	@Override
	public int getUid() {
		return _deco.getUid();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.world.IGroundDecoration#setRegionX
	 * (int)
	 */
	@Override
	public void setRegionX(int var1) {
		_deco.setRegionX(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.world.IGroundDecoration#setRegionY
	 * (int)
	 */
	@Override
	public void setRegionY(int var1) {
		_deco.setRegionY(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.world.IGroundDecoration#setPlane
	 * (int)
	 */
	@Override
	public void setPlane(int var1) {
		_deco.setPlane(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.world.IGroundDecoration#setUid
	 * (int)
	 */
	@Override
	public void setUid(int var1) {
		_deco.setUid(var1);
	}

	@Override
	public int getId() {
		return this.getUid() >> 14 & 32767;
	}

	@Override
	public String getName() {
		return objectDefinition.instance() != null ? objectDefinition.getName() : "";
	}

	@Override
	public String[] getActions() {
		return objectDefinition.instance() != null ? objectDefinition.getActions() : new String[0];
	}

	public int getTileX() {
		return getRegionX() + OldschoolClient.getBaseX();
	}

	public int getTileY() {
		return getRegionY() + OldschoolClient.getBaseY();
	}

	@Override
	public RSTile getPosition() {
		return new RSTile(getTileX(), getTileY(), getPlane());
	}
}
