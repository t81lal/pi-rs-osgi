package org.nullbool.pi.core.engine.api;

import java.util.Set;

import org.nullbool.core.piexternal.game.api.IGameClient;

/**
 * @author Bibl (don't ban me pls)
 * @created 25 Jun 2015 03:40:53
 */
public interface IContextRegistry {

	public void register(IClientContext<IGameClient> context);
	
	public void unregister(ThreadGroup tg);
	
	public void registerListener(ContextListener listener);
	
	public void unregisterListener(ContextListener listener);
	
	public void unregisterAllListeners();
	
	public IClientContext<IGameClient> retrieve(ThreadGroup tg);
	
	public Set<IClientContext<IGameClient>> retrieveAll();
}
