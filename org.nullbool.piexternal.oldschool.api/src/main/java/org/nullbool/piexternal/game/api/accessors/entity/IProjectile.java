/************************************************************************************
 * pi rs - a generic framework for loading Java Applets in a contained environment. *
 * Copyright (C) 2015  NullBool                                                     *
 *                                                                                  *
 * This program is free software: you can redistribute it and/or modify             *
 * it under the terms of the GNU General Public License as published by             *
 * the Free Software Foundation, either version 3 of the License, or                *
 * (at your option) any later version.                                              *
 *                                                                                  *
 * This program is distributed in the hope that it will be useful,                  *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of                   *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                    *
 * GNU General Public License for more details.                                     *
 *                                                                                  *
 * You should have received a copy of the GNU General Public License                *
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.            *
 ************************************************************************************/

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
