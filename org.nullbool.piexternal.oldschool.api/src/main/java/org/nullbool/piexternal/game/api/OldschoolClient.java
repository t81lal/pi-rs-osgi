/**
 * 
 */
package org.nullbool.piexternal.game.api;

import java.awt.Canvas;
import java.util.HashSet;
import java.util.Set;

import org.nullbool.core.piexternal.game.api.IGameClient;
import org.nullbool.pi.core.engine.api.IClientContext;
import org.nullbool.pi.core.engine.api.IContextRegistry;
import org.nullbool.piexternal.game.api.accessors.IOldschoolClient;
import org.nullbool.piexternal.game.api.accessors.IWrappedException;
import org.nullbool.piexternal.game.api.accessors.collections.IDeque;
import org.nullbool.piexternal.game.api.accessors.collections.IHashTable;
import org.nullbool.piexternal.game.api.accessors.definitions.IItemDefinition;
import org.nullbool.piexternal.game.api.accessors.definitions.IObjectDefinition;
import org.nullbool.piexternal.game.api.accessors.entity.INPC;
import org.nullbool.piexternal.game.api.accessors.entity.IPlayer;
import org.nullbool.piexternal.game.api.accessors.widget.IWidget;
import org.nullbool.piexternal.game.api.accessors.world.IRegion;
import org.nullbool.piexternal.game.api.wrappers.WrappedException;
import org.nullbool.piexternal.game.api.wrappers.collection.HashTable;
import org.nullbool.piexternal.game.api.wrappers.definition.ItemDefinition;
import org.nullbool.piexternal.game.api.wrappers.definition.ObjectDefinition;
import org.nullbool.piexternal.game.api.wrappers.entity.NPC;
import org.nullbool.piexternal.game.api.wrappers.entity.Player;
import org.nullbool.piexternal.game.api.wrappers.world.Region;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * @author Bibl (don't ban me pls)
 * @created 22 Jun 2015 23:29:43
 */
public class OldschoolClient {

	// FIXME: Possible memory leak/dangling service, 
	// find a better (but fast) solution.
	private static IContextRegistry registry;
	
	private static void init() {
		BundleContext context = Activator.getContext();
		// System.out.println("Context: " + context);
		// System.out.println("Instance hash: " + Activator.class.hashCode());
		ServiceReference<IContextRegistry> cxtRefSvcRef = context.getServiceReference(IContextRegistry.class);
		registry = context.getService(cxtRefSvcRef);
		context.ungetService(cxtRefSvcRef);
	}
	
	public static IOldschoolClient client() {
		if(registry == null)
			init();
		
		ThreadGroup tg = Thread.currentThread().getThreadGroup();
		IClientContext<IGameClient> cxt = registry.retrieve(tg);
		return (IOldschoolClient) cxt.getClient();
	}
	
	static IClientContext<IGameClient> current() {
		ThreadGroup tg = Thread.currentThread().getThreadGroup();
		IClientContext<IGameClient> cxt = registry.retrieve(tg);
		return cxt;
	}
	
	public static NPC[] getNpcs() {
		Set<NPC> npcs = new HashSet<NPC>();
		for(INPC npc : client().getNpcs()) {
			if(npc != null) {
				npcs.add(new NPC(npc));
			}
		}
		return npcs.toArray(new NPC[0]);
	}
	
	public static Player[] getPlayers() {
		Set<Player> players = new HashSet<Player>();
		for(IPlayer npc : client().getPlayers()) {
			if(npc != null) {
				players.add(new Player(npc));
			}
		}
		return players.toArray(new Player[0]);
	}

	public static Region getRegion() {
		IRegion _region = client().getRegion();
		if(_region != null)
			return new Region(_region);
		return null;
	}

	public static Canvas getCanvas() {
		return client().getCanvas();
	}

	public static Player getLocalPlayer() {
		IPlayer _player = client().getLocalPlayer();
		if(_player != null)
			return new Player(_player);
		return null;
	}

	public static IHashTable getWidgetNodes() {
		IHashTable _ht = client().getWidgetNodes();
		if(_ht != null)
			return new HashTable(_ht);
		return null;
	}

	public static String[] getMenuActions() {
		return client().getMenuActions();
	}

	public static boolean isSpellSelected() {
		return client().isSpellSelected();
	}

	public static int getSelectionState() {
		return client().getSelectionState();
	}
	
	public static String[] getMenuOptions() {
		return client().getMenuOptions();
	}

	public static int getLoopCycle() {
		return client().getLoopCycle();
	}

	public static int getCurrentWorld() {
		return client().getCurrentWorld();
	}

	public static int getGameState() {
		return client().getGameState();
	}

	public static int[] getCurrentLevels() {
		return client().getCurrentLevels();
	}

	public static int[] getRealLevels() {
		return client().getRealLevels();
	}

	public static int[] getSkillsExp() {
		return client().getSkillsExp();
	}

	public static int getSelectedItem() {
		return client().getSelectedItem();
	}

	public static boolean isMenuOpen() {
		return client().isMenuOpen();
	}

	public static int getMenuX() {
		return client().getMenuX();
	}

	public static int getMenuY() {
		return client().getMenuY();
	}

	public static int getMenuWidth() {
		return client().getMenuWidth();
	}

	public static int getMenuSize() {
		return client().getMenuSize();
	}

	public static int getMenuHeight() {
		return client().getMenuHeight();
	}

	public static IDeque[][][] getGroundItems() {
		return client().getGroundItems();
	}

	public static byte[][][] getTileSettings() {
		return client().getTileSettings();
	}

	public static int[][][] getTileHeights() {
		return client().getTileHeights();
	}
	
	public static int getMapScale() {
		return client().getMapScale();
	}

	public static int getMapOffset() {
		return client().getMapOffset();
	}

	public static int getMapAngle() {
		return client().getMapAngle();
	}

	public static int getPlane() {
		return client().getPlane();
	}

	public static int getCameraX() {
		return client().getCameraX();
	}
	
	public static int getCameraY() {
		return client().getCameraY();
	}

	public static int getCameraZ() {
		return client().getCameraZ();
	}

	public static int getCameraYaw() {
		return client().getCameraYaw();
	}

	public static int getCameraPitch() {
		return client().getCameraPitch();
	}

	public static int getBaseX() {
		return client().getBaseX();
	}

	public static int getBaseY() {
		return client().getBaseY();
	}

	public static IWidget[][] getWidgets() {
		return client().getWidgets();
	}

	public static int[] getClientSettings() {
		return client().getClientSettings();
	}

	public static int[] getWidgetsSettings() {
		return client().getWidgetsSettings();
	}

	public static int getHoveredRegionTileX() {
		return client().getHoveredRegionTileX();
	}

	public static int getHoveredRegionTileY() {
		return client().getHoveredRegionTileY();
	}
	
	public static IHashTable getItemTables() {
		IHashTable _ht = client().getItemTables();
		if(_ht != null)
			return new HashTable(_ht);
		return null;
	}

	public static IObjectDefinition loadObjDefinition(int var1) {
		IObjectDefinition _objDef = client().loadObjDefinition(var1);
		if(_objDef != null)
			return new ObjectDefinition(_objDef);
		return null;
	}

	public static IItemDefinition loadItemDefinition(int var1) {
		IItemDefinition _itemDef = client().loadItemDefinition(var1);
		if(_itemDef != null)
			return new ItemDefinition(_itemDef);
		return null;
	}
	
	public static IWrappedException reportException(Throwable var1, String var2) {
		IWrappedException _we = client().reportException(var1, var2);
		if(_we != null)
			return new WrappedException(_we);
		return null;
	}

	public static void processAction(int var1, int var2, int var3, int var4,
			String var5, String var6, int var7, int var8) {
		client().processAction(var1, var2, var3, var4, var5, var6, var7, var8);
	}
}
