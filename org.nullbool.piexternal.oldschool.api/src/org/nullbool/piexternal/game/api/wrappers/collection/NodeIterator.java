package org.nullbool.piexternal.game.api.wrappers.collection;

import java.util.Iterator;

import org.nullbool.piexternal.game.api.accessors.collections.INode;

/**
 * @author Bibl (don't ban me pls) <br>
 * @created 20 Apr 2015 at 21:44:58 <br>
 */
public abstract class NodeIterator<T extends INode> implements Iterator<T> {

	private final T[] nodes;
	private int index;
	// These are INodes instead of T's because see next()
	private INode tail;

	public NodeIterator(T[] nodes) {
		this.nodes = nodes;
		this.index = 0;
	}

	@Override
	public boolean hasNext() {
		return (index > 0) && (nodes.length > 0);
	}

	@Override
	public T next() {
		if ((index > 0) && (nodes[index - 1] != tail)) {
			// We can just set an INode instead of creating a wrapper and saving it as a T (can we even create a generic safe wrapper?)
			INode _tail = tail;
			tail = _tail.getPrevious();
		}
		while (nodes.length > index) {
			INode current = nodes[index++].getPrevious();
			if (nodes[index - 1] != current) {
				tail = current.getPrevious();
				return create(current);
			}
		}
		return null;
	}

	protected abstract T create(INode node);
}