package org.nullbool.piexternal.game.api.accessors.world;

import org.nullbool.piexternal.game.api.accessors.entity.IRenderable;

public interface IGroundItem extends IRenderable {
   int getId();

   int getStackSize();
}
