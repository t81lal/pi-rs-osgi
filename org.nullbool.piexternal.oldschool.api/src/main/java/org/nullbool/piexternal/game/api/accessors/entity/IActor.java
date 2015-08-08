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
