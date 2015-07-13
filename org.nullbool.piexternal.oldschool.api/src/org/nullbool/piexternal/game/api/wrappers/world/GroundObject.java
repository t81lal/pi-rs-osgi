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
	public int getUid() {
		return _obj.getUid();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.world.IGroundObject#setStrictX(int)
	 */
	@Override
	public void setStrictX(int var1) {
		_obj.setStrictX(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.world.IGroundObject#setStrictY(int)
	 */
	@Override
	public void setStrictY(int var1) {
		_obj.setStrictY(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.world.IGroundObject#setPlane(int)
	 */
	@Override
	public void setPlane(int var1) {
		_obj.setPlane(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.world.IGroundObject#setUid(int)
	 */
	@Override
	public void setUid(int var1) {
		_obj.setUid(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.world.IGroundObject#setFlags(int)
	 */
	@Override
	public void setFlags(int var1) {
		_obj.setFlags(var1);		
	}
}