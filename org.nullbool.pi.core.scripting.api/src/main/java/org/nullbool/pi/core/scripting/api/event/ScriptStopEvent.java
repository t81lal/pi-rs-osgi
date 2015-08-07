package org.nullbool.pi.core.scripting.api.event;

import org.nullbool.pi.core.scripting.api.IScriptingEngine;
import org.nullbool.pi.core.scripting.api.Script;

/**
 * @author Bibl (don't ban me pls)
 * @created a while before 07/08/15 (today)
 */
public class ScriptStopEvent extends ScriptEvent {

	public ScriptStopEvent(IScriptingEngine engine, Script script) {
		super(engine, script);
	}
}