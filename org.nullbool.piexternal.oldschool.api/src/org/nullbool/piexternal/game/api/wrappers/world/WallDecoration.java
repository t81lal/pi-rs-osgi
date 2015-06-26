package org.nullbool.piexternal.game.api.wrappers.world;

import org.nullbool.piexternal.game.api.accessors.entity.IRenderable;
import org.nullbool.piexternal.game.api.accessors.world.IWallDecoration;

/**
 * @author Bibl (don't ban me pls) <br>
 * @created 20 Apr 2015 at 21:32:12 <br>
 */
public class WallDecoration implements IWallDecoration {

	private final IWallDecoration _deco;

	public WallDecoration(IWallDecoration _deco) {
		this._deco = _deco;
	}

	@Override
	public int getPlane() {
		return _deco.getPlane();
	}

	@Override
	public int getStrictX() {
		return _deco.getStrictX();
	}

	@Override
	public int getStrictY() {
		return _deco.getStrictY();
	}

	@Override
	public int getOrientation1() {
		return _deco.getOrientation1();
	}

	@Override
	public int getOrientation2() {
		return _deco.getOrientation2();
	}

	@Override
	public int getUID() {
		return _deco.getUID();
	}

	@Override
	public int getHash() {
		return _deco.getHash();
	}

	@Override
	public IRenderable getMarkerRenderable1() {
		return _deco.getMarkerRenderable1();
	}

	@Override
	public IRenderable getMarkerRenderable2() {
		return _deco.getMarkerRenderable2();
	}
}