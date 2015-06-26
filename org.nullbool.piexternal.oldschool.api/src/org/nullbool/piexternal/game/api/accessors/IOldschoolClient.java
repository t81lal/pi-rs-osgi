package org.nullbool.piexternal.game.api.accessors;

import java.awt.Canvas;
import org.nullbool.piexternal.game.api.IGameClient;
import org.nullbool.piexternal.game.api.accessors.IWrappedException;
import org.nullbool.piexternal.game.api.accessors.collections.IDeque;
import org.nullbool.piexternal.game.api.accessors.collections.IHashTable;
import org.nullbool.piexternal.game.api.accessors.definitions.IItemDefinition;
import org.nullbool.piexternal.game.api.accessors.definitions.IObjectDefinition;
import org.nullbool.piexternal.game.api.accessors.entity.INPC;
import org.nullbool.piexternal.game.api.accessors.entity.IPlayer;
import org.nullbool.piexternal.game.api.accessors.widgets.IWidget;
import org.nullbool.piexternal.game.api.accessors.world.IRegion;

public interface IOldschoolClient extends IGameClient {
   INPC[] getNPCs();

   IPlayer[] getPlayers();

   IRegion getRegion();

   Canvas getCanvas();

   IPlayer getLocalPlayer();

   IHashTable getWidgetNodes();

   String[] getMenuActions();

   boolean isSpellSelected();

   int getSelectionState();

   String[] getMenuOptions();

   int getLoopCycle();

   int getCurrentWorld();

   int getGameState();

   int[] getCurrentLevels();

   int[] getRealLevels();

   int[] getSkillsExp();

   int getSelectedItem();

   boolean isMenuOpen();

   int getMenuX();

   int getMenuY();

   int getMenuWidth();

   int getMenuSize();

   int getMenuHeight();

   IDeque[][][] getGroundItems();

   byte[][][] getTileSettings();

   int[][][] getTileHeights();

   int getMapScale();

   int getMapOffset();

   int getMapAngle();

   int getPlane();

   int getCameraX();

   int getCameraY();

   int getCameraZ();

   int getCameraYaw();

   int getCameraPitch();

   int getBaseX();

   int getBaseY();

   IWidget[][] getWidgets();

   int[] getClientSettings();

   int[] getWidgetsSettings();

   int getHoveredRegionTileX();

   int getHoveredRegionTileY();

   IHashTable getItemTables();

   IObjectDefinition loadObjDefinition(int var1);

   IItemDefinition loadItemDefinition(int var1);

   IWrappedException reportException(Throwable var1, String var2);

   void processAction(int var1, int var2, int var3, int var4, String var5, String var6, int var7, int var8);
}
