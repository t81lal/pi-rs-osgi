package org.nullbool.piexternal.game.api.accessors.collections;

import org.nullbool.piexternal.game.api.accessors.collections.INode;

public interface IHashTable {
   INode[] getBuckets();

   INode getHead();

   INode getFirst();

   int getSize();

   int getIndex();

   void put(INode var1, long var2);

   INode first();

   INode next();

   void clear();

   INode get(long var1);
}
