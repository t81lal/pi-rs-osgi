package org.nullbool.piexternal.game.api.accessors.entity;

import org.nullbool.piexternal.game.api.accessors.entity.IRenderable;

public interface IActor extends IRenderable {
   int getLocalX();

   void setLocalX(int var1);

   int getLocalY();

   void setLocalY(int var1);

   int getAnimationId();

   void setAnimationId(int var1);

   int getInteractingId();

   void setInteractingId(int var1);

   int getHealth();

   void setHealth(int var1);

   int getMaxHealth();

   void setMaxHealth(int var1);

   int[] getHitTypes();

   void setHitTypes(int[] var1);

   String getMessage();

   void setMessage(String var1);

   int[] getHitDamages();

   void setHitDamages(int[] var1);
}
