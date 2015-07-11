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
	public int getUid() {
		return _deco.getUid();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.world.IGroundDecoration#setRegionX(int)
	 */
	@Override
	public void setRegionX(int var1) {
		_deco.setRegionX(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.world.IGroundDecoration#setRegionY(int)
	 */
	@Override
	public void setRegionY(int var1) {
		_deco.setRegionY(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.world.IGroundDecoration#setPlane(int)
	 */
	@Override
	public void setPlane(int var1) {
		_deco.setPlane(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.world.IGroundDecoration#setUid(int)
	 */
	@Override
	public void setUid(int var1) {
		_deco.setUid(var1);		
	}
}