package org.nullbool.piexternal.game.api.accessors.collections;

public interface INode {
   INode getPrevious();

   INode getNext();

   long getKey();

   void setKey(long var1);

   boolean isLinked();

   void unlink();
}