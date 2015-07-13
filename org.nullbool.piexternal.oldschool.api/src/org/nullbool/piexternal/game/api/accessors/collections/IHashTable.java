package org.nullbool.piexternal.game.api.accessors.collections;

import org.nullbool.piexternal.game.api.accessors.collections.INode;

public interface IHashTable {
   INode[] getBuckets();

   INode getHead();

   INode getFirstNode();

   int getSize();

   void setSize(int var1);

   int getIndex();

   void setIndex(int var1);

   void put(INode var1, long var2);

   INode first();

   INode next();

   INode get(long var1);

   void clear();
}
