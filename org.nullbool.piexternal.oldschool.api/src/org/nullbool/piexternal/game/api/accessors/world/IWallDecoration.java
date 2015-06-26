package org.nullbool.piexternal.game.api.accessors.world;

import org.nullbool.piexternal.game.api.accessors.entity.IRenderable;

public interface IWallDecoration {
   IRenderable getMarkerRenderable1();

   IRenderable getMarkerRenderable2();

   int getStrictX();

   int getStrictY();

   int getPlane();

   int getHash();

   int getUID();

   int getOrientation1();

   int getOrientation2();
}
