package org.nullbool.pi.core.scripting.api.event;

import org.nullbool.pi.core.scripting.api.IScriptingEngine;
import org.nullbool.topdank.eventbus.api.ObjectEvent;

/**
 * @author Bibl (don't ban me pls)
 * @created a while before 07/08/15 (today)
 */
public class ScriptEngineRefresh extends ObjectEvent<IScriptingEngine> {

	public ScriptEngineRefresh(IScriptingEngine t) {
		super(t);
	}
}