package org.nullbool.piexternal.game.api.accessors.collections;

import org.nullbool.piexternal.game.api.accessors.collections.INode;

public interface IIterableNode {
   INode getNode();

   INode next(INode var1);

   void insertBefore(INode var1);

   INode first();
}
