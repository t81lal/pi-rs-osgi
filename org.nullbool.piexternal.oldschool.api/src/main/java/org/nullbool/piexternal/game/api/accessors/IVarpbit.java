package org.nullbool.piexternal.game.api.accessors;

import org.nullbool.piexternal.game.api.accessors.collections.IDualNode;

public interface IVarpbit extends IDualNode {
   int getConfigId();

   void setConfigId(int var1);

   int getInitialBit();

   void setInitialBit(int var1);

   int getEndBit();

   void setEndBit(int var1);
}
