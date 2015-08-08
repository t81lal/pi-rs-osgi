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

package org.nullbool.piexternal.game.api.accessors.definitions;

import org.nullbool.piexternal.game.api.accessors.collections.IDualNode;

public interface IObjectDefinition extends IDualNode {
   String getName();

   void setName(String var1);

   int getWidth();

   void setWidth(int var1);

   int getHeight();

   void setHeight(int var1);

   int getAnimationId();

   void setAnimationId(int var1);

   int getObjMapScene();

   void setObjMapScene(int var1);

   int getModelWidth();

   void setModelWidth(int var1);

   int getModelHeight();

   void setModelHeight(int var1);

   int getModelBreadth();

   void setModelBreadth(int var1);

   int getTranslationX();

   void setTranslationX(int var1);

   int getTranslationY();

   void setTranslationY(int var1);

   int getTranslationZ();

   void setTranslationZ(int var1);

   boolean isWalkable();

   void setWalkable(boolean var1);

   String[] getActions();

   void setActions(String[] var1);

   int getIcon();

   void setIcon(int var1);

   boolean isRotated();

   void setRotated(boolean var1);

   boolean hasCastedShadow();

   void setHasCastedShadow(boolean var1);
}
