package org.nullbool.pi.core.engine.registryimpl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.nullbool.core.piexternal.game.api.IGameClient;
import org.nullbool.pi.core.engine.api.IClientContext;
import org.nullbool.pi.core.engine.api.IContextRegistry;

/**
 * @author Bibl (don't ban me pls)
 * @created 25 Jun 2015 03:43:52
 */
public class BasicContextRegistry implements IContextRegistry {

	private final Map<ThreadGroup, IClientContext<IGameClient>> registered = new HashMap<ThreadGroup, IClientContext<IGameClient>>();
	
	/* (non-Javadoc)
	 * @see org.nullbool.pi.core.engine.api.IContextRegistry#register(org.nullbool.pi.core.engine.api.IClientContext)
	 */
	@Override
	public synchronized void register(IClientContext<IGameClient> context) {
		registered.put(context.threadGroup(), context);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.pi.core.engine.api.IContextRegistry#unregister(java.lang.ThreadGroup)
	 */
	@Override
	public synchronized void unregister(ThreadGroup tg) {
		registered.remove(tg);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.pi.core.engine.api.IContextRegistry#retrieve(java.lang.ThreadGroup)
	 */
	@Override
	public synchronized IClientContext<IGameClient> retrieve(ThreadGroup tg) {
		return registered.get(tg);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.pi.core.engine.api.IContextRegistry#retrieveAll()
	 */
	@Override
	public Set<IClientContext<IGameClient>> retrieveAll() {
		return new HashSet<IClientContext<IGameClient>>(registered.values());
	}
}