package org.nullbool.piexternal.game.api.wrappers.world;

import org.nullbool.piexternal.game.api.OldschoolClient;
import org.nullbool.piexternal.game.api.accessors.entity.IRenderable;
import org.nullbool.piexternal.game.api.accessors.world.IGroundObject;
import org.nullbool.piexternal.game.api.meta.RSTile;
import org.nullbool.piexternal.game.api.wrappers.definition.ObjectDefinition;

/**
 * @author Bibl (don't ban me pls) <br>
 * @created 20 Apr 2015 at 21:58:14 <br>
 */
public class GroundObject implements IGroundObject {

	private final IGroundObject _obj;
	private final ObjectDefinition objectDefinition;
	
	public GroundObject(IGroundObject _obj) {
		this._obj = _obj;
		this.objectDefinition = new ObjectDefinition(OldschoolClient.loadObjDefinition(getId()));
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

	@Override
	public int getId() {
		return this.getUid()  >> 14 & 32767;
	}

	@Override
	public String getName() {
		return objectDefinition.instance() != null ? objectDefinition.getName() : "";
	}

	@Override
	public String[] getActions() {
		return objectDefinition.instance() != null ? objectDefinition.getActions() : new String[0];
	}
	
	public int getTileX() {
		return getStrictX() + OldschoolClient.getBaseX();
	}

	public int getTileY() {
		return getStrictY()  + OldschoolClient.getBaseY();
	}

	@Override
	public RSTile getPosition() {
		return new RSTile(getTileX(), getTileY(), getPlane());
	}
}