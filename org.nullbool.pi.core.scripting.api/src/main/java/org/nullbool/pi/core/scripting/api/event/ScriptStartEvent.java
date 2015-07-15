package org.nullbool.pi.core.scripting.api.event;

import org.nullbool.pi.core.scripting.api.Script;

public class ScriptStartEvent extends ScriptEvent {

	public ScriptStartEvent(Script script) {
		super(script);
	}
}