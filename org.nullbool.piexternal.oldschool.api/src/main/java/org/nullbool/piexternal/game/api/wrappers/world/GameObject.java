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

import java.awt.Point;
import java.awt.geom.Rectangle2D;

import org.nullbool.piexternal.game.api.Calculations;
import org.nullbool.piexternal.game.api.OldschoolClient;
import org.nullbool.piexternal.game.api.accessors.entity.IRenderable;
import org.nullbool.piexternal.game.api.accessors.world.IGameObject;
import org.nullbool.piexternal.game.api.meta.Interactable;
import org.nullbool.piexternal.game.api.meta.RSTile;
import org.nullbool.piexternal.game.api.wrappers.definition.ObjectDefinition;

/**
 * @author Bibl (don't ban me pls) <br>
 * @created 20 Apr 2015 at 21:24:49 <br>
 */
public class GameObject implements IGameObject, Interactable, Comparable<GameObject> {

	private final IGameObject _obj;
	private final ObjectDefinition definition;
	private final double initDistance;

	public GameObject(IGameObject _obj) {
		this._obj = _obj;
		initDistance = Calculations.distance(getPosition());
		definition = new ObjectDefinition(OldschoolClient.loadObjDefinition(getId()));
	}

	@Override
	public int getId() {
		return getHash() >> 14 & 32767;
	}

	@Override
	public int getFlags() {
		return _obj.getFlags();
	}

	@Override
	public int getHash() {
		return _obj.getHash();
	}

	@Override
	public int getHeight() {
		return _obj.getHeight();
	}

	@Override
	public int getLocalX() {
		return _obj.getLocalX();
	}

	@Override
	public int getLocalY() {
		return _obj.getLocalY();
	}

	@Override
	public IRenderable getMarkedRenderable() {
		return _obj.getMarkedRenderable();
	}

	@Override
	public int getOrientation() {
		return _obj.getOrientation();
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
	public int getWidth() {
		return _obj.getWidth();
	}

	public boolean isWalkable() {
		return definition != null && definition.isWalkable();
	}

	public int getAnimation() {
		return definition != null ? definition.getObjMapScene() : 0;
	}

	public int getMapScene() {
		return definition != null ? definition.getObjMapScene() : 0;
	}

	@Override
	public String getName() {
		return definition != null ? definition.getName() : "";
	}

	@Override
	public String[] getActions() {
		return definition != null ? definition.getActions() : new String[0];
	}

	public int getX() {
		return getLocalX();
	}

	public int getY() {
		return getLocalY();
	}

	public int getTileX() {
		return getX() + OldschoolClient.getBaseX();
	}

	public int getTileY() {
		return getY() + OldschoolClient.getBaseY();
	}

	public int getWidth1() {
		return definition != null ? definition.getWidth() - 1 : 1;
	}

	public int getHeight1() {
		return definition != null ? definition.getHeight() - 1 : 1;
	}

	public Rectangle2D getRectangle() {
		return Calculations.getRectangle2d(getPosition(), getWidth1(), getHeight1());
	}

	@Override
	public Point getScreenPoint() {
		return Calculations.getCenterPoint(getPosition(), 1.0D);
	}

	@Override
	public int compareTo(GameObject obj) {
		return Double.compare(initDistance, initDistance);
	}

	@Override
	public Point getActionPoint(int x, int y) {
		// Model m = getModel();
		// Point point = m != null ? m.getPoint() : getScreenPoint();
		Point point = getScreenPoint();
		point.x += x;
		point.y += y;
		return point;
	}

	@Override
	public void setHash(int var1) {
		_obj.setHash(var1);
	}

	@Override
	public void setPlane(int var1) {
		_obj.setPlane(var1);
	}

	@Override
	public void setStrictX(int var1) {
		_obj.setStrictX(var1);
	}

	@Override
	public void setStrictY(int var1) {
		_obj.setStrictY(var1);
	}

	@Override
	public void setLocalX(int var1) {
		_obj.setLocalX(var1);
	}

	@Override
	public void setLocalY(int var1) {
		_obj.setLocalY(var1);
	}

	@Override
	public void setWidth(int var1) {
		_obj.setWidth(var1);
	}

	@Override
	public void setHeight(int var1) {
		_obj.setHeight(var1);
	}

	@Override
	public void setOrientation(int var1) {
		_obj.setOrientation(var1);
	}

	@Override
	public void setFlags(int var1) {
		_obj.setFlags(var1);
	}

	@Override
	public RSTile getPosition() {
		return new RSTile(getTileX(), getTileY(), getPlane());
	}
}
