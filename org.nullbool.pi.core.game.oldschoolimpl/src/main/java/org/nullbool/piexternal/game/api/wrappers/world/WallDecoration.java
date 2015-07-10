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
	public int getUid() {
		return _deco.getUid();
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

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.world.IWallDecoration#setStrictX(int)
	 */
	@Override
	public void setStrictX(int var1) {
		_deco.setStrictX(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.world.IWallDecoration#setStrictY(int)
	 */
	@Override
	public void setStrictY(int var1) {
		_deco.setStrictY(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.world.IWallDecoration#setPlane(int)
	 */
	@Override
	public void setPlane(int var1) {
		_deco.setPlane(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.world.IWallDecoration#setHash(int)
	 */
	@Override
	public void setHash(int var1) {
		_deco.setHash(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.world.IWallDecoration#setUid(int)
	 */
	@Override
	public void setUid(int var1) {
		_deco.setUid(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.world.IWallDecoration#setOrientation1(int)
	 */
	@Override
	public void setOrientation1(int var1) {
		_deco.setOrientation1(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.world.IWallDecoration#setOrientation2(int)
	 */
	@Override
	public void setOrientation2(int var1) {
		_deco.setOrientation2(var1);		
	}
}