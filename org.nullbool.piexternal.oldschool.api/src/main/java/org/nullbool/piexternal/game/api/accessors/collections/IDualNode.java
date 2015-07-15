package org.nullbool.piexternal.game.api.accessors.collections;

import org.nullbool.piexternal.game.api.accessors.collections.INode;

public interface IDualNode extends INode {
   IDualNode getNextDualNode();

   IDualNode getPreviousDualNode();
}
