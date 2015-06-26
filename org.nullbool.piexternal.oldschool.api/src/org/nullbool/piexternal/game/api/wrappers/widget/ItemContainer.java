/**
 * 
 */
package org.nullbool.piexternal.game.api.wrappers.widget;

import org.nullbool.piexternal.game.api.accessors.widgets.IItemContainer;
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

}