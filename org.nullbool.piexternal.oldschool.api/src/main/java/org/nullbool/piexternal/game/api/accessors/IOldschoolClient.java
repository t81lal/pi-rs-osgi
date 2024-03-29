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

package org.nullbool.piexternal.game.api.accessors;

import java.awt.Canvas;

import org.nullbool.core.piexternal.game.api.IGameClient;
import org.nullbool.piexternal.game.api.accessors.IWrappedException;
import org.nullbool.piexternal.game.api.accessors.collections.IDeque;
import org.nullbool.piexternal.game.api.accessors.collections.IHashTable;
import org.nullbool.piexternal.game.api.accessors.definitions.IItemDefinition;
import org.nullbool.piexternal.game.api.accessors.definitions.IObjectDefinition;
import org.nullbool.piexternal.game.api.accessors.entity.INPC;
import org.nullbool.piexternal.game.api.accessors.entity.IPlayer;
import org.nullbool.piexternal.game.api.accessors.widget.IWidget;
import org.nullbool.piexternal.game.api.accessors.world.IRegion;
import org.nullbool.piexternal.game.api.accessors.world.IWorld;
import org.nullbool.piexternal.game.api.accessors.world.IWorldListDownloader;

public interface IOldschoolClient extends IGameClient {
   INPC[] getNpcs();

   IPlayer[] getPlayers();

   IRegion getRegion();

   Canvas getCanvas();

   void setCanvas(Canvas var1);

   IPlayer getLocalPlayer();

   IHashTable getWidgetNodes();

   String[] getMenuActions();

   void setMenuActions(String[] var1);

   boolean isSpellSelected();

   void setSpellSelected(boolean var1);

   int getSelectionState();

   void setSelectionState(int var1);

   String[] getMenuOptions();

   void setMenuOptions(String[] var1);

   int getLoopCycle();

   void setLoopCycle(int var1);

   int getCurrentWorld();

   void setCurrentWorld(int var1);

   int getGameState();

   void setGameState(int var1);

   int[] getCurrentLevels();

   void setCurrentLevels(int[] var1);

   int[] getRealLevels();

   void setRealLevels(int[] var1);

   int[] getSkillsExp();

   void setSkillsExp(int[] var1);

   int getSelectedItem();

   void setSelectedItem(int var1);

   boolean isMenuOpen();

   void setMenuOpen(boolean var1);

   int getMenuX();

   void setMenuX(int var1);

   int getMenuY();

   void setMenuY(int var1);

   int getMenuWidth();

   void setMenuWidth(int var1);

   int getMenuSize();

   void setMenuSize(int var1);

   int getMenuHeight();

   void setMenuHeight(int var1);

   IDeque[][][] getGroundItems();

   byte[][][] getTileSettings();

   void setTileSettings(byte[][][] var1);

   int[][][] getTileHeights();

   void setTileHeights(int[][][] var1);

   int getMapScale();

   void setMapScale(int var1);

   int getMapOffset();

   void setMapOffset(int var1);

   int getMapAngle();

   void setMapAngle(int var1);

   int getPlane();

   void setPlane(int var1);

   int getCameraX();

   void setCameraX(int var1);

   int getCameraY();

   void setCameraY(int var1);

   int getCameraZ();

   void setCameraZ(int var1);

   int getCameraYaw();

   void setCameraYaw(int var1);

   int getCameraPitch();

   void setCameraPitch(int var1);

   int getBaseX();

   void setBaseX(int var1);

   int getBaseY();

   void setBaseY(int var1);

   IWidget[][] getWidgets();

   int[] getClientSettings();

   void setClientSettings(int[] var1);

   int[] getWidgetsSettings();

   void setWidgetsSettings(int[] var1);

   int getHoveredRegionTileX();

   void setHoveredRegionTileX(int var1);

   int getHoveredRegionTileY();

   void setHoveredRegionTileY(int var1);

   IHashTable getItemTables();

   String getUsername();

   void setUsername(String var1);

   String getPassword();

   void setPassword(String var1);

   IWorld[] getWorlds();

   int getWorldCount();

   void setWorldCount(int var1);

   IWorldListDownloader getWorldListDownloader();

   IObjectDefinition loadObjDefinition(int var1);

   IItemDefinition loadItemDefinition(int var1);

   IWrappedException reportException(Throwable var1, String var2);

   void processAction(int var1, int var2, int var3, int var4, String var5, String var6, int var7, int var8);

   boolean loadWorlds();
}
