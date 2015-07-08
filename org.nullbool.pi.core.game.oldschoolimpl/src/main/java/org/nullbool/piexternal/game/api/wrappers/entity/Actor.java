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
}