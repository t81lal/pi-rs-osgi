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

import org.nullbool.piexternal.game.api.accessors.definitions.IItemDefinition;
import org.nullbool.piexternal.game.api.wrappers.collection.DualNode;

/**
 * @author Bibl (don't ban me pls) <br>
 * @created 19 Apr 2015 at 22:01:47 <br>
 */
public class ItemDefinition extends DualNode<IItemDefinition> implements IItemDefinition {

	public ItemDefinition(IItemDefinition _itemdef) {
		super(_itemdef);
	}

	public IItemDefinition instance() {
		return _node;
	}

	@Override
	public int getDiagonalRotation() {
		return _node.getDiagonalRotation();
	}

	@Override
	public int getFemaleDialog() {
		return _node.getFemaleDialog();
	}

	@Override
	public int getFemaleDialogHat() {
		return _node.getFemaleDialogHat();
	}

	@Override
	public int getFemaleEmblem() {
		return _node.getFemaleEmblem();
	}

	@Override
	public int getFemaleEquipOffset() {
		return _node.getFemaleEquipOffset();
	}

	@Override
	public int getFemaleEquipPrimaryModel() {
		return _node.getFemaleEquipPrimaryModel();
	}

	@Override
	public int getFemaleEquipSecondaryModel() {
		return _node.getFemaleEquipSecondaryModel();
	}

	@Override
	public String[] getGroundActions() {
		return _node.getGroundActions();
	}

	@Override
	public int getMaleDialog() {
		return _node.getMaleDialog();
	}

	@Override
	public int getMaleDialogHat() {
		return _node.getMaleDialogHat();
	}

	@Override
	public int getMaleEmblem() {
		return _node.getMaleEmblem();
	}

	@Override
	public int getMaleEquipOffset() {
		return _node.getMaleEquipOffset();
	}

	@Override
	public int getMaleEquipPrimaryModel() {
		return _node.getMaleEquipPrimaryModel();
	}

	@Override
	public int getMaleEquipSecondaryModel() {
		return _node.getMaleEquipSecondaryModel();
	}

	@Override
	public int getModelBreadth() {
		return _node.getModelBreadth();
	}

	@Override
	public int getModelHeight() {
		return _node.getModelHeight();
	}

	@Override
	public int getModelOffset() {
		return _node.getModelOffset();
	}

	@Override
	public int getModelSine() {
		return _node.getModelSine();
	}

	@Override
	public int getModelWidth() {
		return _node.getModelWidth();
	}

	@Override
	public int getModelZoom() {
		return _node.getModelZoom();
	}

	@Override
	public String getName() {
		return _node.getName();
	}

	@Override
	public int getNoteIndex() {
		return _node.getNoteIndex();
	}

	@Override
	public int getNoteTemplateIndex() {
		return _node.getNoteTemplateIndex();
	}

	@Override
	public int getRotation1() {
		return _node.getRotation1();
	}

	@Override
	public int getRotation2() {
		return _node.getRotation2();
	}

	@Override
	public int getStackedModelLightModifier() {
		return _node.getStackedModelLightModifier();
	}

	@Override
	public int getStackedModelShadowModifier() {
		return _node.getStackedModelShadowModifier();
	}

	@Override
	public int getTeamIndex() {
		return _node.getTeamIndex();
	}

	@Override
	public int getValue() {
		return _node.getValue();
	}

	@Override
	public String[] getWidgetActions() {
		return _node.getWidgetActions();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.definitions.IItemDefinition
	 * #setName(java.lang.String)
	 */
	@Override
	public void setName(String var1) {
		_node.setName(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.definitions.IItemDefinition
	 * #setModelZoom(int)
	 */
	@Override
	public void setModelZoom(int var1) {
		_node.setModelZoom(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.definitions.IItemDefinition
	 * #setRotation1(int)
	 */
	@Override
	public void setRotation1(int var1) {
		_node.setRotation1(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.definitions.IItemDefinition
	 * #setRotation2(int)
	 */
	@Override
	public void setRotation2(int var1) {
		_node.setRotation2(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.definitions.IItemDefinition
	 * #setDiagonalRotation(int)
	 */
	@Override
	public void setDiagonalRotation(int var1) {
		_node.setDiagonalRotation(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.definitions.IItemDefinition
	 * #setModelOffset(int)
	 */
	@Override
	public void setModelOffset(int var1) {
		_node.setModelOffset(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.definitions.IItemDefinition
	 * #setModelSine(int)
	 */
	@Override
	public void setModelSine(int var1) {
		_node.setModelSine(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.definitions.IItemDefinition
	 * #setValue(int)
	 */
	@Override
	public void setValue(int var1) {
		_node.setValue(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.definitions.IItemDefinition
	 * #setGroundActions(java.lang.String[])
	 */
	@Override
	public void setGroundActions(String[] var1) {
		_node.setGroundActions(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.definitions.IItemDefinition
	 * #setWidgetActions(java.lang.String[])
	 */
	@Override
	public void setWidgetActions(String[] var1) {
		_node.setWidgetActions(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.definitions.IItemDefinition
	 * #setMaleEquipPrimaryModel(int)
	 */
	@Override
	public void setMaleEquipPrimaryModel(int var1) {
		_node.setMaleEquipPrimaryModel(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.definitions.IItemDefinition
	 * #setMaleEquipSecondaryModel(int)
	 */
	@Override
	public void setMaleEquipSecondaryModel(int var1) {
		_node.setMaleEquipSecondaryModel(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.definitions.IItemDefinition
	 * #setFemaleEquipPrimaryModel(int)
	 */
	@Override
	public void setFemaleEquipPrimaryModel(int var1) {
		_node.setFemaleEquipPrimaryModel(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.definitions.IItemDefinition
	 * #setFemaleEquipSecondaryModel(int)
	 */
	@Override
	public void setFemaleEquipSecondaryModel(int var1) {
		_node.setFemaleEquipSecondaryModel(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.definitions.IItemDefinition
	 * #setMaleEquipOffset(int)
	 */
	@Override
	public void setMaleEquipOffset(int var1) {
		_node.setMaleEquipOffset(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.definitions.IItemDefinition
	 * #setFemaleEquipOffset(int)
	 */
	@Override
	public void setFemaleEquipOffset(int var1) {
		_node.setFemaleEquipOffset(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.definitions.IItemDefinition
	 * #setMaleEmblem(int)
	 */
	@Override
	public void setMaleEmblem(int var1) {
		_node.setMaleEmblem(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.definitions.IItemDefinition
	 * #setFemaleEmblem(int)
	 */
	@Override
	public void setFemaleEmblem(int var1) {
		_node.setFemaleEmblem(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.definitions.IItemDefinition
	 * #setMaleDialog(int)
	 */
	@Override
	public void setMaleDialog(int var1) {
		_node.setMaleDialog(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.definitions.IItemDefinition
	 * #setMaleDialogHat(int)
	 */
	@Override
	public void setMaleDialogHat(int var1) {
		_node.setMaleDialogHat(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.definitions.IItemDefinition
	 * #setFemaleDialog(int)
	 */
	@Override
	public void setFemaleDialog(int var1) {
		_node.setFemaleDialog(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.definitions.IItemDefinition
	 * #setFemaleDialogHat(int)
	 */
	@Override
	public void setFemaleDialogHat(int var1) {
		_node.setFemaleDialogHat(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.definitions.IItemDefinition
	 * #setNoteIndex(int)
	 */
	@Override
	public void setNoteIndex(int var1) {
		_node.setNoteIndex(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.definitions.IItemDefinition
	 * #setNoteTemplateIndex(int)
	 */
	@Override
	public void setNoteTemplateIndex(int var1) {
		_node.setNoteTemplateIndex(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.definitions.IItemDefinition
	 * #setModelWidth(int)
	 */
	@Override
	public void setModelWidth(int var1) {
		_node.setModelWidth(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.definitions.IItemDefinition
	 * #setModelHeight(int)
	 */
	@Override
	public void setModelHeight(int var1) {
		_node.setModelHeight(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.definitions.IItemDefinition
	 * #setModelBreadth(int)
	 */
	@Override
	public void setModelBreadth(int var1) {
		_node.setModelBreadth(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.definitions.IItemDefinition
	 * #setStackedModelLightModifier(int)
	 */
	@Override
	public void setStackedModelLightModifier(int var1) {
		_node.setStackedModelLightModifier(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.definitions.IItemDefinition
	 * #setStackedModelShadowModifier(int)
	 */
	@Override
	public void setStackedModelShadowModifier(int var1) {
		_node.setStackedModelShadowModifier(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.definitions.IItemDefinition
	 * #setTeamIndex(int)
	 */
	@Override
	public void setTeamIndex(int var1) {
		_node.setTeamIndex(var1);
	}
}
