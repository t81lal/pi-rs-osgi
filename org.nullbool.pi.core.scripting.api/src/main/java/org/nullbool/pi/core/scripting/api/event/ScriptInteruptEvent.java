package org.nullbool.pi.core.scripting.api.event;

import org.nullbool.pi.core.scripting.api.Script;

public class ScriptInteruptEvent extends ScriptEvent {

	private final boolean state;

	public ScriptInteruptEvent(Script script, boolean state) {
		super(script);
		this.state = state;
	}

	public boolean isState() {
		return state;
	}
}