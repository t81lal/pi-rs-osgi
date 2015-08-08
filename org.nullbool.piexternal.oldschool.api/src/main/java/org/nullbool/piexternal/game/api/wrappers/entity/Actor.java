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

package org.nullbool.piexternal.game.api.wrappers.entity;

import java.awt.Point;

import org.nullbool.piexternal.game.api.Calculations;
import org.nullbool.piexternal.game.api.OldschoolClient;
import org.nullbool.piexternal.game.api.accessors.entity.IActor;
import org.nullbool.piexternal.game.api.meta.Interactable;
import org.nullbool.piexternal.game.api.meta.RSTile;

/**
 * @author Bibl (don't ban me pls) <br>
 * @created 19 Apr 2015 at 17:39:56 <br>
 */
public class Actor<T extends IActor> extends Renderable<T> implements IActor, Interactable {

	public Actor(T _actor) {
		super(_actor);
	}

	@Override
	public int getAnimationId() {
		return _node.getAnimationId();
	}

	@Override
	public int getHealth() {
		return _node.getHealth();
	}

	@Override
	public int[] getHitDamages() {
		return _node.getHitDamages();
	}

	@Override
	public int[] getHitTypes() {
		return _node.getHitTypes();
	}

	@Override
	public int getInteractingId() {
		return _node.getInteractingId();
	}

	@Override
	public int getLocalX() {
		return _node.getLocalX();
	}

	@Override
	public int getLocalY() {
		return _node.getLocalY();
	}

	@Override
	public int getMaxHealth() {
		return _node.getMaxHealth();
	}

	@Override
	public String getMessage() {
		return _node.getMessage();
	}

	
	public int getTileX() {
		return OldschoolClient.getBaseX() + (this.getLocalX() >> 7);
	}

	public int getTileY() {
		return OldschoolClient.getBaseY() + (this.getLocalY() >> 7);
	}

	public RSTile getTile() {
		return new RSTile(this.getTileX(), this.getTileY(), OldschoolClient.getPlane());
	}

	@Override
	public Point getScreenPoint() {
		return Calculations.getCenterPoint(this.getTile());
	}

	public boolean isOnScreen() {
		return Calculations.isOnScreen(getScreenPoint());
	}

	public int dist() {
		return (int) Calculations.distance(this);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.entity.IRenderable#setModelHeight(int)
	 */
	@Override
	public void setModelHeight(int var1) {
		_node.setModelHeight(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.entity.IActor#setLocalX(int)
	 */
	@Override
	public void setLocalX(int var1) {
		_node.setLocalX(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.entity.IActor#setLocalY(int)
	 */
	@Override
	public void setLocalY(int var1) {
		_node.setLocalY(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.entity.IActor#setAnimationId(int)
	 */
	@Override
	public void setAnimationId(int var1) {
		_node.setAnimationId(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.entity.IActor#setInteractingId(int)
	 */
	@Override
	public void setInteractingId(int var1) {
		_node.setInteractingId(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.entity.IActor#setHealth(int)
	 */
	@Override
	public void setHealth(int var1) {
		_node.setHealth(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.entity.IActor#setMaxHealth(int)
	 */
	@Override
	public void setMaxHealth(int var1) {
		_node.setMaxHealth(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.entity.IActor#setHitTypes(int[])
	 */
	@Override
	public void setHitTypes(int[] var1) {
		_node.setHitTypes(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.entity.IActor#setMessage(java.lang.String)
	 */
	@Override
	public void setMessage(String var1) {
		_node.setMessage(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.entity.IActor#setHitDamages(int[])
	 */
	@Override
	public void setHitDamages(int[] var1) {
		_node.setHitDamages(var1);
	}

	@Override
	public int[] getQueueX() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setQueueX(int[] var1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int[] getQueueY() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setQueueY(int[] var1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean[] getQueueRun() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setQueueRun(boolean[] var1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getQueueLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setQueueLength(int var1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int[] getHitCycle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setHitCycle(int[] var1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void queuePosition(int var1, int var2, boolean var3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move(int var1, boolean var2) {
		// TODO Auto-generated method stub
		
	}
}
