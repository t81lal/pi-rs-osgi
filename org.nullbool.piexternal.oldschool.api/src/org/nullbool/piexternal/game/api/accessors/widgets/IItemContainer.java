package org.nullbool.piexternal.game.api.accessors.widgets;

import org.nullbool.piexternal.game.api.accessors.collections.INode;

public interface IItemContainer extends INode {
   int[] getQuantities();

   int[] getItemIds();
}
