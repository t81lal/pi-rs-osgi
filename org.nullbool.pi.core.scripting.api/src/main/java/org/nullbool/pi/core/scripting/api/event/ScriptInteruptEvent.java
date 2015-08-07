package org.nullbool.pi.core.scripting.api.event;

import org.nullbool.pi.core.scripting.api.IScriptingEngine;
import org.nullbool.pi.core.scripting.api.Script;

/**
 * @author Bibl (don't ban me pls)
 * @created a while before 07/08/15 (today)
 */
public class ScriptInteruptEvent extends ScriptEvent {

	private final boolean state;

	public ScriptInteruptEvent(IScriptingEngine engine, Script script, boolean state) {
		super(engine, script);
		this.state = state;
	}

	public boolean isState() {
		return state;
	}
}