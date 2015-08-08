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
import org.nullbool.piexternal.game.api.accessors.world.IWallObject;
import org.nullbool.piexternal.game.api.meta.RSTile;
import org.nullbool.piexternal.game.api.wrappers.definition.ObjectDefinition;

/**
 * @author Bibl (don't ban me pls) <br>
 * @created 20 Apr 2015 at 21:30:51 <br>
 */
public class WallObject implements IWallObject {

	private final IWallObject _obj;
	private ObjectDefinition objectDefinition;

	public WallObject(IWallObject _obj) {
		this._obj = _obj;
		this.objectDefinition = new ObjectDefinition(OldschoolClient.loadObjDefinition(getId()));
	}

	@Override
	public int getFlags() {
		return _obj.getFlags();
	}

	@Override
	public int getPlane() {
		return _obj.getPlane();
	}

	@Override
	public int getStrictX() {
		return _obj.getStrictX();
	}

	@Override
	public int getStrictY() {
		return _obj.getStrictY();
	}

	@Override
	public IRenderable getMarkedRenderable1() {
		return _obj.getMarkedRenderable1();
	}

	@Override
	public IRenderable getMarkedRenderable2() {
		return _obj.getMarkedRenderable2();
	}

	@Override
	public int getOrientation1() {
		return _obj.getOrientation1();
	}

	@Override
	public int getOrientation2() {
		return _obj.getOrientation2();
	}

	@Override
	public int getUid() {
		return _obj.getUid();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.world.IWallObject#setStrictX
	 * (int)
	 */
	@Override
	public void setStrictX(int var1) {
		_obj.setStrictX(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.world.IWallObject#setStrictY
	 * (int)
	 */
	@Override
	public void setStrictY(int var1) {
		_obj.setStrictY(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.world.IWallObject#setPlane
	 * (int)
	 */
	@Override
	public void setPlane(int var1) {
		_obj.setPlane(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.world.IWallObject#setUid(int)
	 */
	@Override
	public void setUid(int var1) {
		_obj.setUid(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.world.IWallObject#setFlags
	 * (int)
	 */
	@Override
	public void setFlags(int var1) {
		_obj.setFlags(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.world.IWallObject#setOrientation1
	 * (int)
	 */
	@Override
	public void setOrientation1(int var1) {
		_obj.setOrientation1(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.world.IWallObject#setOrientation2
	 * (int)
	 */
	@Override
	public void setOrientation2(int var1) {
		_obj.setOrientation2(var1);
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
		return getStrictX() + OldschoolClient.getBaseX();
	}

	public int getTileY() {
		return getStrictY() + OldschoolClient.getBaseY();
	}

	@Override
	public RSTile getPosition() {
		return new RSTile(getTileX(), getTileY(), getPlane());
	}
}
