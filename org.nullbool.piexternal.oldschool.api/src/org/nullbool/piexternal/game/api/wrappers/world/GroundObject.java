package org.nullbool.piexternal.game.api.wrappers.world;

import org.nullbool.piexternal.game.api.accessors.entity.IRenderable;
import org.nullbool.piexternal.game.api.accessors.world.IGroundObject;

/**
 * @author Bibl (don't ban me pls) <br>
 * @created 20 Apr 2015 at 21:58:14 <br>
 */
public class GroundObject implements IGroundObject {

	private final IGroundObject _obj;

	public GroundObject(IGroundObject _obj) {
		this._obj = _obj;
	}

	@Override
	public int getFlags() {
		return _obj.getFlags();
	}

	@Override
	public IRenderable getMarkedRenderable() {
		return _obj.getMarkedRenderable();
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
	public int getUID() {
		return _obj.getUID();
	}
}