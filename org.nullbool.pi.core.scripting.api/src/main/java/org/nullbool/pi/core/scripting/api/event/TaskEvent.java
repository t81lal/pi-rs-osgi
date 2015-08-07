package org.nullbool.pi.core.scripting.api.event;

import org.nullbool.pi.core.scripting.api.IScriptingEngine;
import org.nullbool.pi.core.scripting.api.Task;

/**
 * @author Bibl (don't ban me pls)
 * @created a while before 07/08/15 (today)
 */
public class TaskEvent extends EngineObjectEvent<Task> {

	public TaskEvent(IScriptingEngine engine, Task task) {
		super(engine, task);
	}
}