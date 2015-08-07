package org.nullbool.pi.core.scripting.api.event;

import org.nullbool.pi.core.scripting.api.IScriptingEngine;
import org.nullbool.topdank.eventbus.api.Event;

/**
 * @author Bibl (don't ban me pls)
 * @created 7 Aug 2015 00:55:16
 */
public class EngineObjectEvent<T> implements Event {

	private final IScriptingEngine engine;
	private final T obj;
	
	public EngineObjectEvent(IScriptingEngine engine, T t) {
		this.engine = engine;
		this.obj = t;
	}

	public IScriptingEngine getEngine() {
		return engine;
	}

	public T get() {
		return obj;
	}
}