package org.nullbool.pi.core.scripting.api.event;

import org.nullbool.pi.core.scripting.api.Task;
import org.nullbool.topdank.eventbus.api.Event;

public class TaskEvent implements Event {

	private final Task task;

	public TaskEvent(Task task) {
		this.task = task;
	}

	public Task getTask() {
		return task;
	}
}