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

   int[] getQueueX();

   void setQueueX(int[] var1);

   int[] getQueueY();

   void setQueueY(int[] var1);

   boolean[] getQueueRun();

   void setQueueRun(boolean[] var1);

   int getQueueLength();

   void setQueueLength(int var1);

   int[] getHitCycle();

   void setHitCycle(int[] var1);

   void queuePosition(int var1, int var2, boolean var3);

   void move(int var1, boolean var2);
}
