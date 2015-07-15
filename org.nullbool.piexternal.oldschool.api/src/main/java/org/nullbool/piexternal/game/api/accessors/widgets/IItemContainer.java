package org.nullbool.piexternal.game.api.accessors.widgets;

import org.nullbool.piexternal.game.api.accessors.collections.INode;

public interface IItemContainer extends INode {
   int[] getQuantities();

   void setQuantities(int[] var1);

   int[] getItemIds();

   void setItemIds(int[] var1);
}
