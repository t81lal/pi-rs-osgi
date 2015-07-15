package org.nullbool.piexternal.game.api.wrappers.collection;

import org.nullbool.piexternal.game.api.accessors.collections.IDualNode;

/**
 * @author Bibl (don't ban me pls) <br>
 * @created 19 Apr 2015 at 16:54:48 <br>
 */
public class DualNode<T extends IDualNode> extends Node<T> implements IDualNode {

	public DualNode(T _dualNode) {
		super(_dualNode);
	}

	@Override
	public DualNode<IDualNode> getNextDualNode() {
		IDualNode _next = _node.getNextDualNode();
		if (_next == null)
			return null;
		return new DualNode<IDualNode>(_next);
	}

	@Override
	public DualNode<IDualNode> getPreviousDualNode() {
		IDualNode _next = _node.getPreviousDualNode();
		if (_next == null)
			return null;
		return new DualNode<IDualNode>(_next);
	}
}