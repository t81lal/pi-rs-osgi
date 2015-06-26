package org.nullbool.pi.core.scripting.api.event;

import org.nullbool.pi.core.scripting.api.Script;
import org.nullbool.topdank.eventbus.api.ObjectEvent;

public class ScriptEvent extends ObjectEvent<Script> {

	public ScriptEvent(Script t) {
		super(t);
	}
}