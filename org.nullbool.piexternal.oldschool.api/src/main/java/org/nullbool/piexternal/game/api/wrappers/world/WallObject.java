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
	public int getUid() {
		return _obj.getUid();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.world.IWallObject#setStrictX(int)
	 */
	@Override
	public void setStrictX(int var1) {
		_obj.setStrictX(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.world.IWallObject#setStrictY(int)
	 */
	@Override
	public void setStrictY(int var1) {
		_obj.setStrictY(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.world.IWallObject#setPlane(int)
	 */
	@Override
	public void setPlane(int var1) {
		_obj.setPlane(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.world.IWallObject#setUid(int)
	 */
	@Override
	public void setUid(int var1) {
		_obj.setUid(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.world.IWallObject#setFlags(int)
	 */
	@Override
	public void setFlags(int var1) {
		_obj.setFlags(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.world.IWallObject#setOrientation1(int)
	 */
	@Override
	public void setOrientation1(int var1) {
		_obj.setOrientation1(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.world.IWallObject#setOrientation2(int)
	 */
	@Override
	public void setOrientation2(int var1) {
		_obj.setOrientation2(var1);		
	}
}