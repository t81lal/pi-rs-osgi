package org.nullbool.piexternal.game.api.accessors.world;

import org.nullbool.piexternal.game.api.accessors.entity.IRenderable;

public interface IWallDecoration {
   IRenderable getMarkerRenderable1();

   IRenderable getMarkerRenderable2();

   int getStrictX();

   void setStrictX(int var1);

   int getStrictY();

   void setStrictY(int var1);

   int getPlane();

   void setPlane(int var1);

   int getHash();

   void setHash(int var1);

   int getUid();

   void setUid(int var1);

   int getOrientation1();

   void setOrientation1(int var1);

   int getOrientation2();

   void setOrientation2(int var1);
}
