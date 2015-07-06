package org.nullbool.piexternal.game.api.wrappers.entity;

import org.nullbool.piexternal.game.api.accessors.entity.IActor;

/**
 * @author Bibl (don't ban me pls) <br>
 * @created 19 Apr 2015 at 17:39:56 <br>
 */
public class Actor<T extends IActor> extends Renderable<T> implements IActor {

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

//	@Override
//	public int getHealthBarCycle() {
//		return _node.getHealthBarCycle();
//	}

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
		return _node.getInteractingId();
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
}