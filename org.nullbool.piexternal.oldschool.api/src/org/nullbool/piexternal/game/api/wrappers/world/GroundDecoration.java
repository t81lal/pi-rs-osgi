package org.nullbool.piexternal.game.api.wrappers.world;

import org.nullbool.piexternal.game.api.accessors.entity.IRenderable;
import org.nullbool.piexternal.game.api.accessors.world.IGroundDecoration;

/**
 * @author Bibl (don't ban me pls) <br>
 * @created 20 Apr 2015 at 21:29:13 <br>
 */
public class GroundDecoration implements IGroundDecoration {

	private final IGroundDecoration _deco;

	public GroundDecoration(IGroundDecoration _deco) {
		this._deco = _deco;
	}

	@Override
	public IRenderable getTopRenderable() {
		return _deco.getTopRenderable();
	}

	@Override
	public IRenderable getMiddleRenderable() {
		return _deco.getMiddleRenderable();
	}

	@Override
	public IRenderable getBottomRenderable() {
		return _deco.getBottomRenderable();
	}

	@Override
	public int getPlane() {
		return _deco.getPlane();
	}

	@Override
	public int getRegionX() {
		return _deco.getRegionX();
	}

	@Override
	public int getRegionY() {
		return _deco.getRegionY();
	}

	@Override
	public int getUID() {
		return _deco.getUID();
	}
}