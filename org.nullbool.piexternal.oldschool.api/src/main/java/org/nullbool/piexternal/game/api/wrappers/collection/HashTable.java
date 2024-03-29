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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.nullbool.piexternal.game.api.accessors.collections.IHashTable;
import org.nullbool.piexternal.game.api.accessors.collections.INode;

/**
 * @author Bibl (don't ban me pls) <br>
 * @created 19 Apr 2015 at 17:16:51 <br>
 */
public class HashTable implements IHashTable, Iterable<INode> {

	private final IHashTable _hashTable;

	public HashTable(IHashTable _hashTable) {
		this._hashTable = _hashTable;
	}

	// TODO: generics

	@Override
	public INode[] getBuckets() {
		INode[] _buckets = _hashTable.getBuckets();
		final int length = _buckets.length;
		List<Node<INode>> buckets = new ArrayList<Node<INode>>();
		for (int i = 0; i < length; i++) {
			INode bucket = _buckets[i];
			if (bucket != null)
				buckets.add(new Node<INode>(bucket));
		}
		return buckets.toArray(new Node[buckets.size()]);
	}

	@Override
	public Node<INode> getHead() {
		INode _head = _hashTable.getHead();
		if (_head == null)
			return null;
		return new Node<INode>(_head);
	}

	@Override
	public Iterator<INode> iterator() {
		return new NodeIterator<INode>(getBuckets()) {
			@Override
			protected INode create(INode node) {
				return new Node<INode>(node);
			}
		};
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.collections.IHashTable#getFirstNode()
	 */
	@Override
	public INode getFirstNode() {
		INode _tail = _hashTable.getFirstNode();
		if (_tail == null)
			return null;
		return new Node<INode>(_tail);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.collections.IHashTable#getSize()
	 */
	@Override
	public int getSize() {
		return _hashTable.getSize();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.collections.IHashTable#getIndex()
	 */
	@Override
	public int getIndex() {
		return _hashTable.getIndex();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.collections.IHashTable#put(org.nullbool.piexternal.game.api.accessors.collections.INode, long)
	 */
	@Override
	public void put(INode var1, long var2) {
		_hashTable.put(var1, var2);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.collections.IHashTable#first()
	 */
	@Override
	public INode first() {
		return _hashTable.first();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.collections.IHashTable#next()
	 */
	@Override
	public INode next() {
		return _hashTable.next();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.collections.IHashTable#clear()
	 */
	@Override
	public void clear() {
		_hashTable.clear();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.collections.IHashTable#get(long)
	 */
	@Override
	public INode get(long hash) {
		return _hashTable.get(hash);
	}
	

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.collections.IHashTable#setSize(int)
	 */
	@Override
	public void setSize(int var1) {
		_hashTable.setSize(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.collections.IHashTable#setIndex(int)
	 */
	@Override
	public void setIndex(int var1) {
		_hashTable.setIndex(var1);
	}
}
