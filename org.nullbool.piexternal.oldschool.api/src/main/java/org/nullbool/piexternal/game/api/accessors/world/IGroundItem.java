package org.nullbool.piexternal.game.api.accessors.world;

import org.nullbool.piexternal.game.api.accessors.entity.IRenderable;

public interface IGroundItem extends IRenderable {
   int getId();

   void setId(int var1);

   int getStackSize();

   void setStackSize(int var1);
}
