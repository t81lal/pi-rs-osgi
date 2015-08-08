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
package org.nullbool.piexternal.game.api.wrappers.widget;

import org.nullbool.piexternal.game.api.accessors.widget.IItemContainer;
import org.nullbool.piexternal.game.api.wrappers.collection.Node;

/**
 * @author Bibl (don't ban me pls)
 * @created 22 Jun 2015 23:27:11
 */
public class ItemContainer extends Node<IItemContainer> implements IItemContainer {

	/**
	 * @param _node
	 */
	public ItemContainer(IItemContainer _node) {
		super(_node);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IItemContainer#getQuantities()
	 */
	@Override
	public int[] getQuantities() {
		return _node.getQuantities();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IItemContainer#getItemIds()
	 */
	@Override
	public int[] getItemIds() {
		return _node.getItemIds();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IItemContainer#setQuantities(int[])
	 */
	@Override
	public void setQuantities(int[] var1) {
		_node.setQuantities(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IItemContainer#setItemIds(int[])
	 */
	@Override
	public void setItemIds(int[] var1) {
		_node.setItemIds(var1);		
	}
}
