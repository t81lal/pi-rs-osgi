package org.nullbool.pi.core.scripting.api.event;

import org.nullbool.pi.core.scripting.api.Task;

public class TaskStartEvent extends TaskEvent {

	public TaskStartEvent(Task task) {
		super(task);
	}
}