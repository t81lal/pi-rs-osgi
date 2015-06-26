package org.nullbool.piexternal.game.api.wrappers.world;

import org.nullbool.piexternal.game.api.accessors.entity.IRenderable;
import org.nullbool.piexternal.game.api.accessors.world.IWallObject;

/**
 * @author Bibl (don't ban me pls) <br>
 * @created 20 Apr 2015 at 21:30:51 <br>
 */
public class WallObject implements IWallObject {

	private final IWallObject _obj;

	public WallObject(IWallObject _obj) {
		this._obj = _obj;
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
	public int getUID() {
		return _obj.getUID();
	}
}