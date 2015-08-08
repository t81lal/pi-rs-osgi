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

public interface IItemDefinition extends IDualNode {
   String getName();

   void setName(String var1);

   int getModelZoom();

   void setModelZoom(int var1);

   int getRotation1();

   void setRotation1(int var1);

   int getRotation2();

   void setRotation2(int var1);

   int getDiagonalRotation();

   void setDiagonalRotation(int var1);

   int getModelOffset();

   void setModelOffset(int var1);

   int getModelSine();

   void setModelSine(int var1);

   int getValue();

   void setValue(int var1);

   String[] getGroundActions();

   void setGroundActions(String[] var1);

   String[] getWidgetActions();

   void setWidgetActions(String[] var1);

   int getMaleEquipPrimaryModel();

   void setMaleEquipPrimaryModel(int var1);

   int getMaleEquipSecondaryModel();

   void setMaleEquipSecondaryModel(int var1);

   int getFemaleEquipPrimaryModel();

   void setFemaleEquipPrimaryModel(int var1);

   int getFemaleEquipSecondaryModel();

   void setFemaleEquipSecondaryModel(int var1);

   int getMaleEquipOffset();

   void setMaleEquipOffset(int var1);

   int getFemaleEquipOffset();

   void setFemaleEquipOffset(int var1);

   int getMaleEmblem();

   void setMaleEmblem(int var1);

   int getFemaleEmblem();

   void setFemaleEmblem(int var1);

   int getMaleDialog();

   void setMaleDialog(int var1);

   int getMaleDialogHat();

   void setMaleDialogHat(int var1);

   int getFemaleDialog();

   void setFemaleDialog(int var1);

   int getFemaleDialogHat();

   void setFemaleDialogHat(int var1);

   int getNoteIndex();

   void setNoteIndex(int var1);

   int getNoteTemplateIndex();

   void setNoteTemplateIndex(int var1);

   int getModelWidth();

   void setModelWidth(int var1);

   int getModelHeight();

   void setModelHeight(int var1);

   int getModelBreadth();

   void setModelBreadth(int var1);

   int getStackedModelLightModifier();

   void setStackedModelLightModifier(int var1);

   int getStackedModelShadowModifier();

   void setStackedModelShadowModifier(int var1);

   int getTeamIndex();

   void setTeamIndex(int var1);
}
