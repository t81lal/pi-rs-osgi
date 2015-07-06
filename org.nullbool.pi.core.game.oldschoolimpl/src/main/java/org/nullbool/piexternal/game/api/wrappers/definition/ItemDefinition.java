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
		return getModelBreadth();
	}

	@Override
	public int getModelHeight() {
		return getModelHeight();
	}

	@Override
	public int getModelOffset() {
		return getModelOffset();
	}

	@Override
	public int getModelSine() {
		return getModelSine();
	}

	@Override
	public int getModelWidth() {
		return getModelWidth();
	}

	@Override
	public int getModelZoom() {
		return getModelZoom();
	}

	@Override
	public String getName() {
		return getName();
	}

	@Override
	public int getNoteIndex() {
		return getNoteIndex();
	}

	@Override
	public int getNoteTemplateIndex() {
		return getNoteTemplateIndex();
	}

	@Override
	public int getRotation1() {
		return getRotation1();
	}

	@Override
	public int getRotation2() {
		return getRotation2();
	}

	@Override
	public int getStackedModelLightModifier() {
		return getStackedModelLightModifier();
	}

	@Override
	public int getStackedModelShadowModifier() {
		return getStackedModelShadowModifier();
	}

	@Override
	public int getTeamIndex() {
		return getTeamIndex();
	}

	@Override
	public int getValue() {
		return getValue();
	}

	@Override
	public String[] getWidgetActions() {
		return getWidgetActions();
	}
}