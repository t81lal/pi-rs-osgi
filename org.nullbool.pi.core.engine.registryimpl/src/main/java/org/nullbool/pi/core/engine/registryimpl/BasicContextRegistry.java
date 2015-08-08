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

package org.nullbool.pi.core.engine.registryimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.nullbool.core.piexternal.game.api.IGameClient;
import org.nullbool.pi.core.engine.api.ContextListener;
import org.nullbool.pi.core.engine.api.IClientContext;
import org.nullbool.pi.core.engine.api.IContextRegistry;

/**
 * @author Bibl (don't ban me pls)
 * @created 25 Jun 2015 03:43:52
 */
public class BasicContextRegistry implements IContextRegistry {

	private final Map<ThreadGroup, IClientContext<IGameClient>> registered = new HashMap<ThreadGroup, IClientContext<IGameClient>>();
	private final List<ContextListener> listeners = new ArrayList<ContextListener>();
	
	@Override
	public synchronized void register(IClientContext<IGameClient> context) {
		registered.put(context.getThreadGroup(), context);
		
		synchronized (listeners) {
			for(ContextListener listener : listeners) {
				listener.registered(context);
			}
		}
	}

	@Override
	public synchronized void unregister(ThreadGroup tg) {
		IClientContext<IGameClient> cxt = registered.remove(tg);
		
		synchronized (listeners) {
			for(ContextListener listener : listeners) {
				listener.unregistered(cxt);
			}	
		}
	}

	@Override
	public synchronized IClientContext<IGameClient> retrieve(ThreadGroup tg) {
		return registered.get(tg);
	}

	@Override
	public Set<IClientContext<IGameClient>> retrieveAll() {
		return new HashSet<IClientContext<IGameClient>>(registered.values());
	}

	@Override
	public void registerListener(ContextListener listener) {
		synchronized (listeners) {
			listeners.add(listener);		
		}
	}

	@Override
	public void unregisterListener(ContextListener listener) {
		synchronized (listeners) {
			listeners.remove(listener);
		}
	}

	@Override
	public void unregisterAllListeners() {
		synchronized (listeners) {
			listeners.clear();
		}
	}
}
