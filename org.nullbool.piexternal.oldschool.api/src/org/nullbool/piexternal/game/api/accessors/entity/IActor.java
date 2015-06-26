package org.nullbool.piexternal.game.api.accessors.entity;

import org.nullbool.piexternal.game.api.accessors.entity.IRenderable;

public interface IActor extends IRenderable {
   int getLocalX();

   int getLocalY();

   int getAnimationId();

   int getInteractingId();

   int getHealth();

   int getMaxHealth();

   int[] getHitTypes();

   String getMessage();

   int[] getHitDamages();
}
