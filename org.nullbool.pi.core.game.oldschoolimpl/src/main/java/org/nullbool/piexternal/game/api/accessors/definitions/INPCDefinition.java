package org.nullbool.piexternal.game.api.accessors.definitions;

import org.nullbool.piexternal.game.api.accessors.collections.IDualNode;

public interface INPCDefinition extends IDualNode {
   String[] getActions();

   void setActions(String[] var1);

   boolean isOnMap();

   void setOnMap(boolean var1);

   boolean isVisible();

   void setVisible(boolean var1);

   boolean isClickable();

   void setClickable(boolean var1);

   String getName();

   void setName(String var1);

   int getCombatLevel();

   void setCombatLevel(int var1);

   int getWidth();

   void setWidth(int var1);

   int getHeight();

   void setHeight(int var1);

   int getBrightness();

   void setBrightness(int var1);

   int getContrast();

   void setContrast(int var1);

   int getHeadIcon();

   void setHeadIcon(int var1);

   int getNpcDegToTurn();

   void setNpcDegToTurn(int var1);

   int getVarpId();

   void setVarpId(int var1);

   int getSettingId();

   void setSettingId(int var1);

   int getNpcBoundDim();

   void setNpcBoundDim(int var1);

   int getIdleAnimationId();

   void setIdleAnimationId(int var1);

   int getWalkAnimationId();

   void setWalkAnimationId(int var1);

   int getNpcTurnAround();

   void setNpcTurnAround(int var1);

   int getNpcTurnRight();

   void setNpcTurnRight(int var1);

   int getNpcTurnLeft();

   void setNpcTurnLeft(int var1);
}
