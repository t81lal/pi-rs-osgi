package org.nullbool.piexternal.game.api.accessors.world;

import org.nullbool.piexternal.game.api.accessors.entity.IRenderable;

public interface IGameObject {
   int getHash();

   int getPlane();

   int getStrictX();

   int getStrictY();

   int getLocalX();

   int getLocalY();

   int getWidth();

   int getHeight();

   int getOrientation();

   int getFlags();

   IRenderable getMarkedRenderable();
}
