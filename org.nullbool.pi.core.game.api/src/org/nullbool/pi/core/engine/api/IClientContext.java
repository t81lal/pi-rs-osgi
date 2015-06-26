package org.nullbool.pi.core.engine.api;

import java.applet.Applet;

import org.nullbool.pi.core.scripting.api.IScriptingEngine;
import org.nullbool.pi.core.scripting.api.klassmodel.MasterScriptLoader;
import org.nullbool.piexternal.game.api.IGameClient;
import org.nullbool.topdank.eventbus.api.EventBus;

/**
 * @author Bibl (don't ban me pls)
 * @created 12 Jun 2015 19:31:22
 */
public abstract interface IClientContext<T extends IGameClient> {

	public abstract ThreadGroup threadGroup();
	
	public abstract EventBus eventBus();
		
	public abstract T client();
	
	public abstract Applet applet();
	
	public abstract MasterScriptLoader classloader();
	
	public abstract IScriptingEngine scriptingEngine();
	
	public abstract void init() throws Throwable;
	
	public abstract void shutdown();
	
	public abstract boolean active();
}