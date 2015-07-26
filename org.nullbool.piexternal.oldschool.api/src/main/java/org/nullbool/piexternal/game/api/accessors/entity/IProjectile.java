package org.nullbool.piexternal.game.api.accessors.entity;

public interface IProjectile {
   int getId();

   void setId(int var1);

   int getDelay();

   void setDelay(int var1);

   int getSceneId();

   void setSceneId(int var1);

   int getTargetIndex();

   void setTargetIndex(int var1);

   int getEndHeight();

   void setEndHeight(int var1);

 //  ay getAnimation();

   int getStartY();

   void setStartY(int var1);

   int getStartX();

   void setStartX(int var1);

   boolean isMoving();

   void setMoving(boolean var1);

   double getCurrentX();

   void setCurrentX(double var1);

   int getRadius();

   void setRadius(int var1);

   double getSpeed();

   void setSpeed(double var1);

   double getCurrentZ();

   void setCurrentZ(double var1);

   int getModelHeight();

   void setModelHeight(int var1);

   int getEndCycle();

   void setEndCycle(int var1);

   double getSpeedX();

   void setSpeedX(double var1);

   double getSpeedY();

   void setSpeedY(double var1);

   double getCurrentY();

   void setCurrentY(double var1);

   double getSpeedZ();

   void setSpeedZ(double var1);

   int getSlope();

   void setSlope(int var1);

   double getHeightStep();

   void setHeightStep(double var1);

   int getRotationX();

   void setRotationX(int var1);

   int getRotationY();

   void setRotationY(int var1);

   int getFrameCycle();

   void setFrameCycle(int var1);

   int getCurrentFrame();

   void setCurrentFrame(int var1);

   void trackTarget(int var1, int var2, int var3, int var4);

   void move(int var1);
}
