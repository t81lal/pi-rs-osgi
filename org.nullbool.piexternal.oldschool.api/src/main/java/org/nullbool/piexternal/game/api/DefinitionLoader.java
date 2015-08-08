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

import java.util.HashMap;
import java.util.Map;

import org.nullbool.core.piexternal.game.api.IGameClient;
import org.nullbool.pi.core.engine.api.IClientContext;
import org.nullbool.piexternal.game.api.accessors.definitions.IItemDefinition;
import org.nullbool.piexternal.game.api.accessors.definitions.IObjectDefinition;
import org.nullbool.piexternal.game.api.wrappers.definition.ItemDefinition;
import org.nullbool.piexternal.game.api.wrappers.definition.ObjectDefinition;

public class DefinitionLoader implements Runnable {
	
	private static final Map<ThreadGroup, DefinitionLoader> INSTANCES = new HashMap<ThreadGroup, DefinitionLoader>();
	private final ItemDefinition[] itemDefs = new ItemDefinition[30000];
	private final ObjectDefinition[] objectDefs = new ObjectDefinition[30000];

	DefinitionLoader() {
	}
	
	public static DefinitionLoader get() {
		ThreadGroup tg = Thread.currentThread().getThreadGroup();
		if(INSTANCES.containsKey(tg)) {
			return INSTANCES.get(tg);
		}
		
		IClientContext<IGameClient> cxt = OldschoolClient.current();
		if(tg != cxt.getThreadGroup()) {
			throw new IllegalStateException("ThreadGroups don't match?");
		}
		
		DefinitionLoader loader = new DefinitionLoader();
		INSTANCES.put(tg, loader);
		
		return loader;
	}
	
	@Override
	public void run() {
		try {
			waitTillGameIsLoaded();
			load();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void load() {
		for (int i = 0; i < objectDefs.length; i++) {
			IItemDefinition itemDef = OldschoolClient.loadItemDefinition(i);
			IObjectDefinition objDef = OldschoolClient.loadObjDefinition(i);
			itemDefs[i] = itemDef != null ? new ItemDefinition(itemDef) : null;
			objectDefs[i] = objDef != null ? new ObjectDefinition(objDef) : null;
		}
	}

	private void waitTillGameIsLoaded() {
		while (OldschoolClient.getGameState() != 10) {
			Utilities.sleep(50L);
		}
	}

	public ItemDefinition[] getItemsDefinitions() {
		return itemDefs;
	}

	public ObjectDefinition[] getObjectDefinitions() {
		return objectDefs;
	}

	public ItemDefinition getItemdefinition(int id) {
		return id >= 0 && id <= 30000 ? itemDefs[id] : null;
	}

	public ObjectDefinition getObjectdefinition(int id) {
		return id >= 0 && id <= 30000 ? objectDefs[id] : null;
	}
}
