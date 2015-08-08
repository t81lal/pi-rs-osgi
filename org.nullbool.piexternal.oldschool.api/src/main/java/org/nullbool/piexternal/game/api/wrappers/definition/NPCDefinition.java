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

package org.nullbool.piexternal.game.api.wrappers.definition;

import org.nullbool.piexternal.game.api.accessors.definitions.INPCDefinition;
import org.nullbool.piexternal.game.api.wrappers.collection.DualNode;

/**
 * @author Bibl (don't ban me pls) <br>
 * @created 19 Apr 2015 at 17:47:54 <br>
 */
public class NPCDefinition extends DualNode<INPCDefinition> implements INPCDefinition {

	public NPCDefinition(INPCDefinition _npcdef) {
		super(_npcdef);
	}

	@Override
	public String[] getActions() {
		return _node.getActions();
	}

	@Override
	public int getBrightness() {
		return _node.getBrightness();
	}

	@Override
	public int getCombatLevel() {
		return _node.getCombatLevel();
	}

	@Override
	public int getContrast() {
		return _node.getContrast();
	}

	@Override
	public int getHeadIcon() {
		return _node.getHeadIcon();
	}

	@Override
	public int getHeight() {
		return _node.getHeight();
	}

	@Override
	public int getIdleAnimationId() {
		return _node.getIdleAnimationId();
	}

	@Override
	public String getName() {
		return _node.getName();
	}

	@Override
	public int getNpcBoundDim() {
		return _node.getNpcBoundDim();
	}

	@Override
	public int getNpcDegToTurn() {
		return _node.getNpcDegToTurn();
	}

	@Override
	public int getNpcTurnAround() {
		return _node.getNpcTurnAround();
	}

	@Override
	public int getNpcTurnLeft() {
		return _node.getNpcTurnLeft();
	}

	@Override
	public int getNpcTurnRight() {
		return _node.getNpcTurnRight();
	}

	@Override
	public int getSettingId() {
		return _node.getSettingId();
	}

	@Override
	public int getVarpId() {
		return _node.getVarpId();
	}

	@Override
	public int getWalkAnimationId() {
		return _node.getWalkAnimationId();
	}

	@Override
	public int getWidth() {
		return _node.getWidth();
	}

	@Override
	public boolean isClickable() {
		return _node.isClickable();
	}

	@Override
	public boolean isOnMap() {
		return _node.isOnMap();
	}

	@Override
	public boolean isVisible() {
		return _node.isVisible();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.definitions.INPCDefinition#setActions(java.lang.String[])
	 */
	@Override
	public void setActions(String[] var1) {
		_node.setActions(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.definitions.INPCDefinition#setOnMap(boolean)
	 */
	@Override
	public void setOnMap(boolean var1) {
		_node.setOnMap(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.definitions.INPCDefinition#setVisible(boolean)
	 */
	@Override
	public void setVisible(boolean var1) {
		_node.setVisible(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.definitions.INPCDefinition#setClickable(boolean)
	 */
	@Override
	public void setClickable(boolean var1) {
		_node.setClickable(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.definitions.INPCDefinition#setName(java.lang.String)
	 */
	@Override
	public void setName(String var1) {
		_node.setName(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.definitions.INPCDefinition#setCombatLevel(int)
	 */
	@Override
	public void setCombatLevel(int var1) {
		_node.setCombatLevel(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.definitions.INPCDefinition#setWidth(int)
	 */
	@Override
	public void setWidth(int var1) {
		_node.setWidth(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.definitions.INPCDefinition#setHeight(int)
	 */
	@Override
	public void setHeight(int var1) {
		_node.setHeight(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.definitions.INPCDefinition#setBrightness(int)
	 */
	@Override
	public void setBrightness(int var1) {
		_node.setBrightness(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.definitions.INPCDefinition#setContrast(int)
	 */
	@Override
	public void setContrast(int var1) {
		_node.setContrast(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.definitions.INPCDefinition#setHeadIcon(int)
	 */
	@Override
	public void setHeadIcon(int var1) {
		_node.setHeadIcon(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.definitions.INPCDefinition#setNpcDegToTurn(int)
	 */
	@Override
	public void setNpcDegToTurn(int var1) {
		_node.setNpcDegToTurn(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.definitions.INPCDefinition#setVarpId(int)
	 */
	@Override
	public void setVarpId(int var1) {
		_node.setVarpId(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.definitions.INPCDefinition#settingId(int)
	 */
	@Override
	public void setSettingId(int var1) {
		_node.setSettingId(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.definitions.INPCDefinition#setNpcBoundDim(int)
	 */
	@Override
	public void setNpcBoundDim(int var1) {
		_node.setNpcBoundDim(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.definitions.INPCDefinition#setIdleAnimationId(int)
	 */
	@Override
	public void setIdleAnimationId(int var1) {
		_node.setIdleAnimationId(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.definitions.INPCDefinition#setWalkAnimationId(int)
	 */
	@Override
	public void setWalkAnimationId(int var1) {
		_node.setWalkAnimationId(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.definitions.INPCDefinition#setNpcTurnAround(int)
	 */
	@Override
	public void setNpcTurnAround(int var1) {
		_node.setNpcTurnAround(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.definitions.INPCDefinition#setNpcTurnRight(int)
	 */
	@Override
	public void setNpcTurnRight(int var1) {
		_node.setNpcTurnRight(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.definitions.INPCDefinition#setNpcTurnLeft(int)
	 */
	@Override
	public void setNpcTurnLeft(int var1) {
		_node.setNpcTurnLeft(var1);
	}
}
