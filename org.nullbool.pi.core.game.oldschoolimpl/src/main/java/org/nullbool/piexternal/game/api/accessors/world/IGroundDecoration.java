package org.nullbool.piexternal.game.api.accessors.world;

import org.nullbool.piexternal.game.api.accessors.entity.IRenderable;

public interface IGroundDecoration {
   IRenderable getBottomRenderable();

   IRenderable getMiddleRenderable();

   IRenderable getTopRenderable();

   int getRegionX();

   int getRegionY();

   int getPlane();

   int getUID();
}
