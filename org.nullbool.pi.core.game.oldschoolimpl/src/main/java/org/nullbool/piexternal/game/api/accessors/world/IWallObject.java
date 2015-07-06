package org.nullbool.piexternal.game.api.accessors.world;

import org.nullbool.piexternal.game.api.accessors.entity.IRenderable;

public interface IWallObject {
   IRenderable getMarkedRenderable1();

   IRenderable getMarkedRenderable2();

   int getStrictX();

   int getStrictY();

   int getPlane();

   int getUID();

   int getFlags();

   int getOrientation1();

   int getOrientation2();
}
