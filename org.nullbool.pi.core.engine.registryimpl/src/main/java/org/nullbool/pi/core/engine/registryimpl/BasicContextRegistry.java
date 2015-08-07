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