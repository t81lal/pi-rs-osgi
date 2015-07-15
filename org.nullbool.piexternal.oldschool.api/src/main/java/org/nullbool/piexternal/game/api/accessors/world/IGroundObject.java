package org.nullbool.piexternal.game.api.accessors.world;

import org.nullbool.piexternal.game.api.accessors.entity.IRenderable;

public interface IGroundObject {
   IRenderable getMarkedRenderable();

   int getStrictX();

   void setStrictX(int var1);

   int getStrictY();

   void setStrictY(int var1);

   int getPlane();

   void setPlane(int var1);

   int getUid();

   void setUid(int var1);

   int getFlags();

   void setFlags(int var1);
}
