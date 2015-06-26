package org.nullbool.pi.core.scripting.api.event;

import org.nullbool.pi.core.scripting.api.Script;

public class ScriptStopRequestEvent extends ScriptEvent {

	public ScriptStopRequestEvent(Script script) {
		super(script);
	}
}