package org.nullbool.piexternal.game.api.accessors.collections;

import org.nullbool.piexternal.game.api.accessors.collections.IIterableNode;
import org.nullbool.piexternal.game.api.accessors.collections.INode;

public interface INodeIterator {
   INode getCurrent();

   INode getNext();

   IIterableNode getNode();
}
