package org.nullbool.piexternal.game.api.wrappers.collection;

import org.nullbool.piexternal.game.api.accessors.collections.IDeque;
import org.nullbool.piexternal.game.api.accessors.collections.INode;

/**
 * @author Bibl (don't ban me pls) <br>
 * @created 19 Apr 2015 at 21:59:47 <br>
 */
public class Deque implements IDeque {

	private final IDeque _deque;

	public Deque(IDeque _deque) {
		this._deque = _deque;
	}

	@Override
	public INode getHead() {
		INode _head = _deque.getHead();
		if (_head == null)
			return null;
		return new Node<INode>(_head);
	}

	@Override
	public INode getTail() {
		INode _tail = _deque.getTail();
		if (_tail == null)
			return null;
		return new Node<INode>(_tail);
	}
}