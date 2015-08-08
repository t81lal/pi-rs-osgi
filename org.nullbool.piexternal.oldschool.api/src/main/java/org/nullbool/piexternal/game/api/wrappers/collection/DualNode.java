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
