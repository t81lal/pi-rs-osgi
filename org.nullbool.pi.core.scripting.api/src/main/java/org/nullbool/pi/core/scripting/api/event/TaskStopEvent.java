package org.nullbool.pi.core.scripting.api.event;

import org.nullbool.pi.core.scripting.api.IScriptingEngine;
import org.nullbool.pi.core.scripting.api.Task;

/**
 * @author Bibl (don't ban me pls)
 * @created a while before 07/08/15 (today)
 */
public class TaskStopEvent extends TaskEvent {

	public TaskStopEvent(IScriptingEngine engine, Task task) {
		super(engine, task);
	}
}