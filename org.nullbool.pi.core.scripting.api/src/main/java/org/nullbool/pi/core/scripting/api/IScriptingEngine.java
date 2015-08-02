package org.nullbool.pi.core.scripting.api;

import java.util.List;

import org.nullbool.pi.core.scripting.api.loader.ResolvedDefinition;

/**
 * @author Bibl (don't ban me pls)
 * @created 25 Jun 2015 03:54:21
 */
public abstract interface IScriptingEngine {

	public abstract Task startTask(ResolvedDefinition taskData);
	
	public abstract void stopTask(Task task);
	
	public abstract void stopAllTasks();
	
	public abstract boolean tasksRunning();
	
	public abstract Script startScript(ResolvedDefinition scriptData);
	
	public abstract boolean isScriptRunning();
	
	public abstract String[] getActiveScriptData();
	
	public abstract void interuptActiveScript(boolean state);
	
	public abstract void interuptActiveScript();

	public abstract void stopActiveScript();
	
	public abstract Script getActiveScript();
	
	public abstract List<Task> getActiveTasks();
	
	public abstract void refresh();
}