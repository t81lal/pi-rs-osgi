package org.nullbool.piexternal.game.api.wrappers.entity;

import org.nullbool.piexternal.game.api.accessors.entity.IRenderable;
import org.nullbool.piexternal.game.api.wrappers.collection.DualNode;

/**
 * @author Bibl (don't ban me pls) <br>
 * @created 19 Apr 2015 at 17:02:36 <br>
 */
public class Renderable<T extends IRenderable> extends DualNode<T> implements IRenderable {

	public Renderable(T _renderable) {
		super(_renderable);
	}

	@Override
	public int getModelHeight() {
		return _node.getModelHeight();
	}
}