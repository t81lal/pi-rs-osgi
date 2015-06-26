package org.nullbool.pi.core.scripting.api.event;

import org.nullbool.pi.core.scripting.api.Task;

public class TaskStopEvent extends TaskEvent {

	public TaskStopEvent(Task task) {
		super(task);
	}
}