package org.nullbool.piexternal.game.api.wrappers.world;

import org.nullbool.piexternal.game.api.accessors.entity.IRenderable;
import org.nullbool.piexternal.game.api.accessors.world.IGameObject;

/**
 * @author Bibl (don't ban me pls) <br>
 * @created 20 Apr 2015 at 21:24:49 <br>
 */
public class GameObject implements IGameObject {

	private final IGameObject _obj;

	public GameObject(IGameObject _obj) {
		this._obj = _obj;
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
}