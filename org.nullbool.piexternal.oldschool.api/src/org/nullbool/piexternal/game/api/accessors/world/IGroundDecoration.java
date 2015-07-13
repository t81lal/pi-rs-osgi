package org.nullbool.piexternal.game.api.accessors.world;

import org.nullbool.piexternal.game.api.accessors.entity.IRenderable;

public interface IGroundDecoration {
   IRenderable getBottomRenderable();

   IRenderable getMiddleRenderable();

   IRenderable getTopRenderable();

   int getRegionX();

   void setRegionX(int var1);

   int getRegionY();

   void setRegionY(int var1);

   int getPlane();

   void setPlane(int var1);

   int getUid();

   void setUid(int var1);
}
