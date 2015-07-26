package org.nullbool.piexternal.game.api.accessors.collections;

import org.nullbool.piexternal.game.api.accessors.collections.IDualNode;

public interface IQueue {
   IDualNode getHead();

   IDualNode remove();

   IDualNode get();

   void insertTail(IDualNode var1);

   void insertHead(IDualNode var1);
}
