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
}