package org.nullbool.pi.core.engine.api;

import java.applet.Applet;

import org.nullbool.core.piexternal.game.api.IGameClient;
import org.nullbool.pi.core.scripting.api.IScriptingEngine;
import org.nullbool.pi.core.scripting.api.klassmodel.HierarchalClassLoader;
import org.nullbool.topdank.eventbus.api.EventBus;

/**
 * @author Bibl (don't ban me pls)
 * @created 12 Jun 2015 19:31:22
 */
public interface IClientContext<T extends IGameClient> {

	public ThreadGroup getThreadGroup();
	
	public EventBus getEventBus();
		
	public T getClient();
	
	public Applet getApplet();
	
	public HierarchalClassLoader getContextClassLoader();
	
	public IScriptingEngine getScriptingEngine();
	
	public void init() throws Throwable;
	
	public void shutdown();
	
	public boolean active();
}