package org.nullbool.piexternal.game.api.accessors.world;

import org.nullbool.piexternal.game.api.accessors.entity.IRenderable;

public interface IGroundObject {
   IRenderable getMarkedRenderable();

   int getStrictX();

   int getStrictY();

   int getPlane();

   int getUID();

   int getFlags();
}
