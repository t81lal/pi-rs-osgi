package org.nullbool.piexternal.game.api.accessors.collections;

import org.nullbool.piexternal.game.api.accessors.collections.IDualNode;

public interface IIterableDualNode {
   IDualNode getNode();

   void unlinkAll();

   void insertBefore(IDualNode var1);
}
