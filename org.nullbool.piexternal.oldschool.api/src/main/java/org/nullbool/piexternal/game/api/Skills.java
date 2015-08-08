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

package org.nullbool.piexternal.game.api;


/**
 * @author Bibl (don't ban me pls)
 * @created 6 Jul 2015 18:00:55
 */
public enum Skills {
   ATTACK(0),
   DEFENCE(1),
   STRENGTH(2),
   HITPOINTS(3),
   RANGE(4),
   PRAYER(5),
   MAGIC(6),
   COOKING(7),
   WOODCUTTING(8),
   FLETCHING(9),
   FISHING(10),
   FIREMAKING(11),
   CRAFTING(12),
   SMITHING(13),
   MINING(14),
   HERBLORE(15),
   AGILITY(16),
   THIEVING(17),
   SLAYER(18),
   FARMING(19),
   RUNECRAFT(20),
   HUNTER(21),
   CONSTRUCTION(22);

   private int index;

   private Skills(int index) {
      this.index = index;
   }

   public int getIndex() {
      return this.index;
   }
   
   public int getCurrentLevel() {
	   return OldschoolClient.client().getCurrentLevels()[index];
   }
   
   public int getRealLevel() {
	   return OldschoolClient.client().getRealLevels()[index];
   }
   
   public int getExperience() {
	   return OldschoolClient.client().getSkillsExp()[index];
   }
}
