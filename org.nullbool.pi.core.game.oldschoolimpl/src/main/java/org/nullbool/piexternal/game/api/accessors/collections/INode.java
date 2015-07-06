package org.nullbool.piexternal.game.api.accessors.collections;

public interface INode {
   INode getPrevious();

   INode getNext();

   long getKey();

   boolean isLinked();

   void unlink();
}
