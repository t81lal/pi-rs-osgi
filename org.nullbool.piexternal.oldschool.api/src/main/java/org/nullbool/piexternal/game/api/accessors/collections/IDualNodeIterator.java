package org.nullbool.piexternal.game.api.accessors.collections;

import org.nullbool.piexternal.game.api.accessors.collections.IDualNode;
import org.nullbool.piexternal.game.api.accessors.collections.IIterableDualNode;

public interface IDualNodeIterator {
   IDualNode getNext();

   IIterableDualNode getNode();

   IDualNode getCurrent();
}
