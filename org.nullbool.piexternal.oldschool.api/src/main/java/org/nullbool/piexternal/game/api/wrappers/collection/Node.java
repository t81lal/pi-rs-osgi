/************************************************************************************
 * pi rs - a generic framework for loading Java Applets in a contained environment. *
 * Copyright (C) 2015  NullBool                                                     *
 *                                                                                  *
 * This program is free software: you can redistribute it and/or modify             *
 * it under the terms of the GNU General Public License as published by             *
 * the Free Software Foundation, either version 3 of the License, or                *
 * (at your option) any later version.                                              *
 *                                                                                  *
 * This program is distributed in the hope that it will be useful,                  *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of                   *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                    *
 * GNU General Public License for more details.                                     *
 *                                                                                  *
 * You should have received a copy of the GNU General Public License                *
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.            *
 ************************************************************************************/

/**
 * 
 */
package org.nullbool.piexternal.game.api.wrappers.collection;

import org.nullbool.piexternal.game.api.accessors.collections.INode;

/**
 * @author Bibl (don't ban me pls)
 * @created 22 Jun 2015 22:41:24
 */
public class Node<T extends INode> implements INode {

	protected final T _node;
	
	public Node(T _node) {
		this._node = _node;
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.collections.INode#getPreviousNode()
	 */
	@Override
	public Node<INode> getPrevious() {
		INode _prev = _node.getPrevious();
		if (_prev == null)
			return null;
		return new Node<INode>(_prev);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.collections.INode#getNext()
	 */
	@Override
	public Node<INode> getNext() {
		INode _next = _node.getNext();
		if (_next == null)
			return null;
		return new Node<INode>(_next);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.collections.INode#getKey()
	 */
	@Override
	public long getKey() {
		return _node.getKey();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.collections.INode#setKey(long)
	 */
	@Override
	public void setKey(long var1) {
		_node.setKey(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.collections.INode#isLinked()
	 */
	@Override
	public boolean isLinked() {
		return _node.isLinked();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.collections.INode#unlink()
	 */
	@Override
	public void unlink() {
		_node.unlink();
	}
}
