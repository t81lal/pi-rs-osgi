package org.nullbool.piexternal.game.api.wrappers.world;

import org.nullbool.piexternal.game.api.accessors.world.IGroundItem;
import org.nullbool.piexternal.game.api.wrappers.entity.Renderable;

/**
 * @author Bibl (don't ban me pls) <br>
 * @created 20 Apr 2015 at 21:27:12 <br>
 */
public class GroundItem extends Renderable<IGroundItem> implements IGroundItem {

	public GroundItem(IGroundItem _item) {
		super(_item);
	}

	@Override
	public int getId() {
		return _node.getId();
	}

	@Override
	public int getStackSize() {
		return _node.getStackSize();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.world.IGroundItem#setId(int)
	 */
	@Override
	public void setId(int var1) {
		_node.setId(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.world.IGroundItem#setStackSize(int)
	 */
	@Override
	public void setStackSize(int var1) {
		_node.setStackSize(var1);		
	}
}