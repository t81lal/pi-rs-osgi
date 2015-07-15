package org.nullbool.pi.core.scripting.api.event;

import org.nullbool.pi.core.scripting.api.IScriptingEngine;
import org.nullbool.topdank.eventbus.api.ObjectEvent;

public class ScriptEngineRefresh extends ObjectEvent<IScriptingEngine> {

	public ScriptEngineRefresh(IScriptingEngine t) {
		super(t);
	}
}