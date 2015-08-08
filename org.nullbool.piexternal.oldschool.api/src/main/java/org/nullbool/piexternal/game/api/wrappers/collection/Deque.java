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

	/* FIXME: Wrappers don't reflect real types. */
	@Override
	public INode getHead() {
		INode _head = _deque.getHead();
		return _head;
//		if (_head == null)
//			return null;
//		return new Node<INode>(_head);
	}

	@Override
	public INode getTail() {
		INode _tail = _deque.getTail();
		return _tail;
//		if (_tail == null)
//			return null;
//		return new Node<INode>(_tail);
	}
}
