package org.nullbool.pi.core.engine.api;

import java.util.Set;

import org.nullbool.piexternal.game.api.IGameClient;

/**
 * @author Bibl (don't ban me pls)
 * @created 25 Jun 2015 03:40:53
 */
public abstract interface IContextRegistry {

	public abstract void register(IClientContext<IGameClient> context);
	
	public abstract void unregister(ThreadGroup tg);
	
	public abstract IClientContext<IGameClient> retrieve(ThreadGroup tg);
	
	public abstract Set<IClientContext<IGameClient>> retrieveAll();
}
