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
