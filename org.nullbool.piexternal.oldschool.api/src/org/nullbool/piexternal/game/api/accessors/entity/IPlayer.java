package org.nullbool.piexternal.game.api.accessors.entity;

import org.nullbool.piexternal.game.api.accessors.entity.IActor;

public interface IPlayer extends IActor {
   String getName();

   void setName(String var1);

   int getPlayerLevel();

   void setPlayerLevel(int var1);
}
